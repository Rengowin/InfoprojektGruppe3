package info3projekt;

/*This class represents both of your levels
 * it contains Objects like from diffent classes like Items, Puzzel, Rooms
 * but it also contains room names and texts
 * it is also responsible for creating the rooms and setting it up with next to neighbors
 */
public class Level {

    private int anzHints;
    private int[] anzRooms;
    private Items[] items;
    private Room[] rooms;
    private Puzzel[] puzzel;
    private String[] roomNames;
    private String[] roomTexts;
    private GameManager gameManager;
    private String levelName;

    /* Setter for Room Names */
    public void setRoomNames(String[] roomNames) {
        this.roomNames = roomNames;
    }

    /* Setter for Room Texts */
    public void setRoomTexts(String[] roomTexts) {
        this.roomTexts = roomTexts;
    }

    /* Getter for Level Name */
    public String getLevelName() {
        return levelName;
    }

    /* Setter for Level Name */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /* Constructor for Level */
    public Level(int allowedHint, int totalRooms, Items[] items, Puzzel[] puzzel, String levelName,
            GameManager gameManager) {
        this.anzHints = allowedHint;
        this.items = items;
        this.levelName = levelName;
        this.puzzel = puzzel;
        this.anzRooms = new int[totalRooms];
        this.gameManager = gameManager;
    }

    /* getter for number of rooms */
    public int getAnzRooms() {
        return anzRooms.length;
    }

    /* getter for the room texts */
    public String getRoomInfo() {
        return rooms[gameManager.getPlayer().getCurrentRoom()].getRoomInfo();
    }

    /* getter for the room names */
    public String getRoomName() {
        return rooms[gameManager.getPlayer().getCurrentRoom()].getRoomName();
    }

    /* getter for the number of hints */
    public int getAnzHints() {
        return anzHints;
    }

    /* setter for the Puzzels */
    public void setPuzzel(Puzzel[] puzzel) {
        this.puzzel = puzzel;
    }

    /* setter for the Items */
    public void setItems(Items[] items) {
        this.items = items;
    }

    /*
     * Room Creation
     * This method creates the Level base on withch level is selected in the
     * GameManager
     * It sets up the rooms with their names, texts, items, and puzzles.
     * It also sets the neighbors for each room based on the level design.
     * 
     * @param witchLevel The level type to create (1 or 2).
     * 1 for the first level design, 2 for the second level design.
     */
    /*
     * also kinder a bad example :D for clean code,
     * what could be better? like in gamemanager said make the switch case a
     * separate class and then call it here with the level number
     */
    public void createRooms(int witchLevel) {
        rooms = new Room[anzRooms.length];
        switch (witchLevel) {
            case 1:
                // 10 rooms that are in a line, and Room names are in a array
                for (int i = 0; i < rooms.length; i++) {
                    if (i == 0) {
                        rooms[i] = new NormalRoom(roomNames[i], roomTexts[i]);
                        rooms[i].setNextTo(-1, -1, 1, -1); // has only an south neighbor
                    } else if (i == rooms.length - 1) {
                        rooms[i] = new FinalRoom(roomNames[i], roomTexts[i]);
                        rooms[i].setNextTo(i - 1, -1, -1, -1); // has only a north neighbor
                    } else {
                        rooms[i] = new NormalRoom(roomNames[i], roomTexts[i]);
                        rooms[i].setNextTo(i - 1, -1, i + 1, -1); // middle rooms have both neighbors
                    }
                    rooms[i].setItems(items[i]);
                    rooms[i].setPuzzel(puzzel[i]);
                    rooms[i].setGameManager(gameManager);
                    puzzel[i].setGameManager(gameManager);
                }
                break;
            case 2:
                // 10 Rooms where the frist 3 are in a line and then a group of 3 rooms split t
                // o the north and then rest to the south where also the final room will be
                rooms[0] = new NormalRoom(roomNames[0], roomTexts[0]);
                rooms[0].setNextTo(-1, 1, -1, -1); // has only an east neighbor
                rooms[1] = new NormalRoom(roomNames[1], roomTexts[1]);
                rooms[1].setNextTo(-1, 2, -1, 0); // has an est and a west neighbor
                rooms[2] = new NormalRoom(roomNames[2], roomTexts[2]);
                rooms[2].setNextTo(-1, 3, -1, 1); // has an est and a west neighbor
                rooms[3] = new NormalRoom(roomNames[3], roomTexts[3]);
                rooms[3].setNextTo(4, -1, 7, 2); // has the north neighbors and a south neighbors
                rooms[4] = new NormalRoom(roomNames[4], roomTexts[4]);
                rooms[4].setNextTo(5, -1, 3, -1); // has the north neighbors and a south neighbors
                rooms[5] = new NormalRoom(roomNames[5], roomTexts[5]);
                rooms[5].setNextTo(6, -1, 4, -1); // has the north neighbors and a south neighbors
                rooms[6] = new NormalRoom(roomNames[6], roomTexts[6]);
                rooms[6].setNextTo(-1, -1, 5, -1); // has only a south neighbor
                // these rooms now go to the south
                rooms[7] = new NormalRoom(roomNames[7], roomTexts[7]);
                rooms[7].setNextTo(3, -1, 8, -1); // has a north neighbor and a south neighbor
                rooms[8] = new NormalRoom(roomNames[8], roomTexts[8]);
                rooms[8].setNextTo(7, -1, 9, -1); // has a north neighbor and a south neighbor
                rooms[9] = new FinalRoom(roomNames[9], roomTexts[9]);
                rooms[9].setNextTo(8, -1, -1, -1); // has only a north neighbor
                for (int i = 0; i < rooms.length; i++) {
                    rooms[i].setItems(items[i]);
                    rooms[i].setPuzzel(puzzel[i]);
                    rooms[i].setGameManager(gameManager);
                    puzzel[i].setGameManager(gameManager);
                }
                break;
            default:
                System.out.println("Unknown level type. Please check the level configuration.");
                break;
        }

    }

    /* getter for the room at the index */
    public Room getRoom(int roomIndex) {
        if (rooms == null) {
            System.out.println("Rooms are not initialized.");
            return null;
        }
        if (roomIndex >= 0 && roomIndex < rooms.length) {
            return rooms[roomIndex];
        } else {
            System.out.println("Invalid room index: " + roomIndex);
            return null;
        }
    }

    /* getter for the room info based on the player */
    public String getRoomInfo(Player player) {
        int currentRoomIndex = player.getCurrentRoom();
        if (rooms == null || currentRoomIndex < 0 || currentRoomIndex >= rooms.length) {
            return "Ungültiger Raumindex oder keine Räume initialisiert.";
        }
        if (currentRoomIndex >= 0 && currentRoomIndex < rooms.length) {
            return rooms[currentRoomIndex].getRoomInfo();
        } else {
            return "You are not in a valid room.";
        }
    }
}
