package info3projekt;

import java.util.Scanner;

/*
 * The GameManager class manages the game state, including player, level,
 * puzzles, and items.
 * It initializes the game, processes player commands, and handles game logic.
 * It also provides methods to access game components like puzzles and items.
 * It should also be responsible that all classes are connected and working together.
 */

public class GameManager {
    private Scanner scanner;
    private Player player;
    private Level level;
    private Puzzel[] puzzel; // to give later all the puzzels to room/commands
    private Items[] items; // to give later all the items to room/commands
    private Commands commands = new Commands(this);
    private int[] itemEffects;
    // itemNames and descriptions could be removed and be put into the Items class
    // since we conclouded that we dont want to give ever item a unique name and
    // description, item descriptions also got removed because of this
    private String[] itemNames;
    private String[] roomNames;
    private String[] roomTexts;

    /* Getter for Commands */
    public Commands classCommandsGet() {
        return commands;
    }

    /* Getter for Room Names */
    public String[] getRoomNames() {
        return roomNames;
    }

    /* Getter for Room Texts */
    public String[] getRoomTexts() {
        return roomTexts;
    }

    /* Getter for Scanner */
    public Scanner getScanner() {
        return scanner;
    }

    /* Getter for Player */
    public Player getPlayer() {
        return player;
    }

    /* Getter for Level */
    public Level getLevel() {
        return level;
    }

    /* Getter for Puzzles */
    public Puzzel[] getPuzzel() {
        return puzzel;
    }

    /* Getter for Items */
    public Items[] getItems() {
        return items;
    }

    /* Constructor */
    public GameManager() {
        scanner = new Scanner(System.in);
    }

    /*
     * Initialize the game
     * This method sets up the game by initializing the player, level, puzzles, and
     * items.
     * It prompts the player for their name and the level they want to play.
     * It also creates the level and populates it with rooms, puzzles, and items.
     */
    /*
     * what could be clean coded? creating the Player, move the level creation into
     * level and make it own subclass. also the loop for creating the puzzels for a
     * level could be its own method
     * and a bad clean code example :D
     */
    public void initialisGame() {
        boolean wrongAnz = false;
        System.out.println("Welcome to the game! Please enter your name:");
        String playerName = scanner.nextLine();
        if (playerName == null) {
            playerName = "Player";
        }
        System.out.println("Hello " + playerName + "! Let's start your adventure.");
        while (wrongAnz == false) {
            System.out.println("what level do you want to play? (1-2)");
            int levelChoice = scanner.nextInt();
            switch (levelChoice) {
                case 1:
                    puzzel = new Puzzel[10];
                    items = new Items[10];
                    level = new Level(3, 10, items, puzzel, "HTW WH building C", this);
                    itemEffects = new int[] { 1, 2, 3, -1, 1, 2, 3, -1, 1, 2 };
                    int[] puzzelValues = { 1, 11, 13, 4, -1, 11, 7, 8, 12, 1 };
                    String[] itemNames = { "BuzzelBreaker", "HP", "Reroll", "noItem", "BuzzelBreaker",
                            "HP", "Reroll", "noItem", "BuzzelBreaker", "HP" };
                    String[] roomNames = {
                            "Steam Locomotive",
                            "Coal Tender",
                            "Baggage Car",
                            "First Class",
                            "Dining Car",
                            "Second Class",
                            "Third Class",
                            "Mail Car",
                            "Brake Van",
                            "Observation Car"
                    };
                    String[] roomTexts = {
                            "At the front, with a chimney. Pulls the entire train.",
                            "Directly behind the locomotive. Transports water and coal for the steam locomotive.",
                            "For luggage, bicycles, sometimes animals or mail.",
                            "Individual compartments, padded seats, carpet, wood paneling.",
                            "Table settings, kitchen, waiters with trays.",
                            "Simpler compartments, usually with 6 seats.",
                            "Wooden seats or benches, open seating, no compartments.",
                            "Mail sorting during travel.",
                            "With sleeping berths or beds, used in night trains.",
                            "Luxury compartment, e.g., for wealthy travelers, often private.",
                            "Used for manual braking and as a control post at the end of the train.",
                            "Glass car at the end of the train for panoramic views during travel."
                    };
                    // loop to create puzzles and items
                    for (int i = 0; i < puzzel.length; i++) {
                        puzzel[i] = (i == puzzel.length - 1)
                                ? new FinalPuzzel(puzzelValues[i])
                                : new NormalPuzzel(puzzelValues[i]);
                        items[i] = new Items(itemEffects[i], this, itemNames[i]);
                    }
                    wrongAnz = true;
                    break;
                case 2:
                    roomNames = new String[] {
                            "Entrance HTW B",
                            "Porter",
                            "Green Space",
                            "Building C",
                            "C: Floor 0",
                            "C: Floor 3 Room: 340",
                            "C: Floor 5 Room: 579",
                            "GreenBear",
                            "Building G",
                            "Cafeteria"
                    };
                    roomTexts = new String[] {
                            "You are standing at the entrance to Building B.",
                            "You walk past the porter.",
                            "The HTW green space where students play volleyball.",
                            "Do you go to the lecture (north) or head to the cafeteria (south)?",
                            "You enter the building and head to an elevator.",
                            "You attend the Info lecture by Mr. Zhang.",
                            "You attend the Info lab session.",
                            "You head towards the cafeteria and pass the HTW Motorsport Team (greenBear).",
                            "You enter Building G.",
                            "You enter the cafeteria."
                    };
                    itemNames = new String[] { "BuzzelBreaker", "HP", "Reroll", "noItem", "BuzzelBreaker",
                            "HP", "Reroll", "noItem", "BuzzelBreaker", "HP" };
                    puzzel = new Puzzel[10];
                    items = new Items[10];
                    level = new Level(3, 10, items, puzzel, "HTW WH street entrance + Building C and H", this);
                    puzzelValues = new int[] { 6, 3, 11, 12, 5, -1, 9, 3, -1, 2 };
                    itemEffects = new int[] { 1, 2, 3, -1, 1, 2, 3, -1, 1, 2 };
                    for (int i = 0; i < puzzel.length; i++) {
                        puzzel[i] = (i == puzzel.length - 1)
                                ? new FinalPuzzel(puzzelValues[i])
                                : new NormalPuzzel(puzzelValues[i]);
                        items[i] = new Items(itemEffects[i], this, itemNames[i]);
                    }
                    wrongAnz = true;
                    break;
                default:
                    System.out.println("Invalid level choice. Please choose 1 or 2.");
            }
            if (wrongAnz == false) {
                System.out.println("Please try again.");
            } else {
                level.setItems(items);
                level.setPuzzel(puzzel);
                level.setRoomNames(roomNames);
                level.setRoomTexts(roomTexts);
                level.createRooms(levelChoice);
                commands = new Commands(this);
            }
        }
        player = new Player(playerName, level);

        System.out.println("You have " + player.getAnzHints() + " hints available.");
        System.out.println("You start with " + player.getHealth() + " health points too solve the level.");

    }

    /*
     * Process player input
     * This method processes the player's input command and executes the
     * corresponding action.
     * It uses the Commands class to handle the command logic.
     */
    public void processInput(String input) {
        input = input.toLowerCase().trim();
        commands.getCommands(input);
    }
}
