package info3projekt;

/* This class handles the commands that the player can use in the game.
 * It interacts with the GameManager to perform actions like moving, looking around,
 * picking up items, using items, and solving puzzles.
 */
public class Commands {
    private GameManager gameManager;

    /* constructor */
    public Commands(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /* This method prints out the available commands for the player */
    public void getCommands() {
        System.out.println(
                "you can use the following commands: move, lookaround, see items, pickup, use, where to move, help, solve, exit");

    }

    /*
     * This method processes the player's command input and opens the corresponding
     * method
     * 
     * @param command The command input by the player.
     */
    public void getCommands(String command) {
        switch (command.toLowerCase()) {
            case "lookaround":
                lookAround();
                break;
            case "move":
                movePrompt();
                break;
            case "solve":
                solve();
                break;
            case "pickup":
                pickup();
                break;
            case "use":
                use();
                break;
            case "where to move":
                whereToMove();
                break;
            case "see items":
                seeItems();
                break;
            case "help":
                getCommands();
                break;
            case "cheat":
                /*
                 * This cheat will set puzzel typ change to -3 so that it counts as solved and
                 * is for only
                 * debugging or for the presentation :D or for testing for the teacher
                 */
                int currentRoom = gameManager.getPlayer().getCurrentRoom();
                Puzzel[] puzzel = gameManager.getPuzzel();
                puzzel[currentRoom].setPuzzelTyp(-3);
                currentRoom = currentRoom + 1; // Adjust for 1-based room numbering in output
                System.out.println("Cheat activated: Puzzel in room " + currentRoom + " is now solved.");
                break;

            case "north":
            case "move north":
                move("north");
                break;
            case "east":
            case "move east":
                move("east");
                break;
            case "south":
            case "move south":
                move("south");
                break;
            case "west":
            case "move west":
                move("west");
                break;
            case "exit":
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command. Please try again.");
                getCommands();
                break;
        }
    }

    /*
     * This method allows the player to look around in the current room.
     * and prints the room name and description, puzzle and if they is an items it
     * also prints it out..
     */
    public void lookAround() {
        int currentRoom = gameManager.getPlayer().getCurrentRoom();
        System.out.println("Room: " + gameManager.getLevel().getRoomInfo(gameManager.getPlayer()));
        System.out.println("Room description: " + gameManager.getLevel().getRoomInfo(gameManager.getPlayer()));
        Items item = gameManager.getItems()[currentRoom];
        if (item != null && item.itemEffect != -1) {
            System.out.println("You see an item in this room: " + item.getItemName());
        }
        Puzzel puzzel = gameManager.getPuzzel()[currentRoom];
        if (puzzel != null && puzzel.puzzelTyp != -1) {
            System.out.println("You see a puzzel in this room you need to solve it");
        } else {
            System.out.println("There is no puzzel in this room.");
        }
    }

    /*
     * This method prints out the items in the player's inventory
     * if the inventory is empty, it informs the player with a message..
     */

    public void seeItems() {
        Player player = gameManager.getPlayer();
        java.util.List<Items> inventory = player.getItemes();
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("You have no items in your inventory.");
        } else {
            System.out.println("Your inventory contains:");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i).getItemName());
            }
        }
    }

    /*
     * This method allows the player to pick up an item from the current room.
     * It checks if there is an item in the room and adds it to the player's
     * inventory.
     * If there is no item, it informs the player.
     */
    public void pickup() {
        int currentRoom = gameManager.getPlayer().getCurrentRoom();
        Items item = gameManager.getItems()[currentRoom];
        if (item != null && item.itemEffect != -1) {
            System.out.println("You picked up: " + item.getItemName());
            gameManager.getPlayer().addToList(item);
            // @override the item in the room with a new item that has no effect and its set
            // to no item
            gameManager.getItems()[currentRoom] = new Items(-1, gameManager, "No more Item");
        } else {
            System.out.println("There is no item to pick up in this room.");
        }
    }

    /*
     * this methodes allows the player to use an item from their inventory
     * if they is no item in the inventory it informs the player
     * then it checks if the item is used or not
     * then it checks if the item can be used on the puzzle
     */
    public void use() {
        if (gameManager.getPlayer().getItemes().isEmpty()) {
            System.out.println("You have no items to use.");
        } else {
            System.out.println("Which item do you want to use? Please specify by number where 1 is: ");
            seeItems();
            int itemIndex = gameManager.getScanner().nextInt() - 1; // Convert to zero-based index
            java.util.List<Items> inventory = gameManager.getPlayer().getItemes();
            if (itemIndex >= 0 && itemIndex < inventory.size()) {
                Items item = inventory.get(itemIndex);
                System.out.println("You used: " + item.getItemName());
                Object result = item.useItem();
                System.out.println(result);
                Puzzel puzzel = gameManager.getPuzzel()[gameManager.getPlayer().getCurrentRoom()];
                // checks if the puzzel is solved and if the item is a hint or reroll
                if (puzzel.puzzelTyp < 0 && (item.itemEffect == 1 || item.itemEffect == 3)) {
                    System.out.println("it does nothing in this room and is not used!!");
                } else {
                    System.out.println("You used the item: " + item.getItemName());
                    item.isUsed = true;
                }
            } else {
                System.out.println("Invalid item selection. Please try again.");
            }

            gameManager.getScanner().nextLine();
        }
    }

    /* this methodes allows the player to see where they can move */
    public void whereToMove() {
        int currentRoom = gameManager.getPlayer().getCurrentRoom();
        Room room = gameManager.getLevel().getRoom(currentRoom);
        System.out.println("You can move to the following directions:");
        if (room.isItPossebelToMoveTo(0)) {
            System.out.println("North");
        }
        if (room.isItPossebelToMoveTo(1)) {
            System.out.println("East");
        }
        if (room.isItPossebelToMoveTo(2)) {
            System.out.println("South");
        }
        if (room.isItPossebelToMoveTo(3)) {
            System.out.println("West");
        }
    }

    /*
     * This method prompts the player to choose a direction to move
     * if the player does not enter a direction directly
     */
    public void movePrompt() {
        System.out.println("Where do you want to move? (North, East, South, West)");
        // Consume any leftover newline from previous input to avoid skipping input
        // if (gameManager.getScanner().hasNextLine()) {
        // gameManager.getScanner().nextLine();
        // }
        String direction = gameManager.getScanner().nextLine().toLowerCase();
        move(direction);
    }

    /*
     * This method moves the player in the specified direction if possible.
     * It checks if there is a room in the specified direction and updates the
     * player's current room accordingly.
     * If there is no room in that direction, it informs the player and gives
     * informations where they can move to.
     * If the player is in a room with a puzzle that needs to be solved,
     * it prevents the player from moving until the puzzle is solved.
     * 
     * @param direction The direction to move in (north, east, south, west, move
     * north, move east, move south, move west).
     */
    public void move(String direction) {
        int currentRoom = gameManager.getPlayer().getCurrentRoom();
        Room room = gameManager.getLevel().getRoom(currentRoom);
        Puzzel puzzel = gameManager.getPuzzel()[currentRoom];
        if (puzzel != null && puzzel.puzzelTyp > 0) {
            System.out.println("You cannot move, you have to solve the puzzle first.");
            return;
        }
        switch (direction) {
            case "north":
            case "move north":
                if (room.isItPossebelToMoveTo(0)) {
                    gameManager.getPlayer().setCurrentRoom(room.getNorthNextTo());
                    System.out.println("You moved to the north. And are now in room: "
                            + gameManager.getLevel().getRoomName());
                } else {
                    System.out.println("There is no room to the north.");
                    whereToMove();
                }
                break;
            case "east":
            case "move east":
                if (room.isItPossebelToMoveTo(1)) {
                    gameManager.getPlayer().setCurrentRoom(room.getEastNextTo());
                    System.out.println("You moved to the east. And are now in room: "
                            + gameManager.getLevel().getRoomName());
                } else {
                    System.out.println("There is no room to the east.");
                    whereToMove();
                }
                break;
            case "south":
            case "move south":
                if (room.isItPossebelToMoveTo(2)) {
                    gameManager.getPlayer().setCurrentRoom(room.getSouthNextTo());
                    System.out.println("You moved to the south. And are now in room: "
                            + gameManager.getLevel().getRoomName());
                } else {
                    System.out.println("There is no room to the south.");
                    whereToMove();
                }
                break;
            case "west":
            case "move west":
                if (room.isItPossebelToMoveTo(3)) {
                    gameManager.getPlayer().setCurrentRoom(room.getWestNextTo());
                    System.out.println("You moved to the west. And are now in room: "
                            + gameManager.getLevel().getRoomName());
                } else {
                    System.out.println("There is no room to the west.");
                    whereToMove();
                }
                break;
            default:
                System.out.println("Invalid direction. Please try again.");

        }
    }

    /*
     * this method is called when the player wants to solve a puzzle
     * and opends the solve method of the puzzle in the current room
     */
    public void solve() {
        int currentRoom = gameManager.getPlayer().getCurrentRoom();
        Puzzel puzzel = gameManager.getPuzzel()[currentRoom];
        if (puzzel == null) {
            System.out.println("There is no puzzle object in this room.");
        } else if (puzzel.puzzelTyp > 0) {
            puzzel.solve(gameManager.getScanner());
        } else {
            System.out.println("There is no puzzle to solve in this room.");
        }
        gameManager.getScanner().nextLine();
    }
}