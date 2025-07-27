package info3projekt;

/* Abstract class representing a room in the game
 * This class defines the structure and behavior of a room,
 * including its connections to other rooms, the puzzle it contains,
 * and any items present in the room.
 * It also provides methods to interact with the room and its contents.
 */
/* here could be the items and the puzzel be remove and manged with the gamemanger class */
public abstract class Room {
    String roomText;
    int northNextTo;
    int eastNextTo;
    int southNextTo;
    int westNextTo;
    String roomName;
    Puzzel puzzel;
    GameManager gameManager;
    Items item;

    /* Getter for Room Name */
    public String getRoomName() {
        return roomName;
    }

    /* Getter for Room Text */
    public String getRoomInfo() {
        return roomText;
    }

    /* Getter for Next Room Connections */
    public int[] getNextTo() {
        return new int[] { northNextTo, eastNextTo, southNextTo, westNextTo };
    }

    /* Getter for North Next to current Room */
    public int getNorthNextTo() {
        return northNextTo;
    }

    /* Getter for East Next to current Room */
    public int getEastNextTo() {
        return eastNextTo;
    }

    /* Getter for South Next to current Room */
    public int getSouthNextTo() {
        return southNextTo;
    }

    /* Getter for West Next to current Room */
    public int getWestNextTo() {
        return westNextTo;
    }

    /* Setter for Next Room Connections */
    /* when no room should be used -1 */
    public void setNextTo(int north, int east, int south, int west) {
        this.northNextTo = north;
        this.eastNextTo = east;
        this.southNextTo = south;
        this.westNextTo = west;
    }

    /* Setter for Room Name */
    public void setRoomText(String text) {
        this.roomText = text;
    }

    /* Check if the room has an item */
    public boolean hasItem() {
        if (item != null && item.itemEffect != -1) {
            return true;
        }
        return false;
    }

    /* Setter for Items */
    public void setItems(Items item) {
        this.item = item;
    }

    /* Setter for Puzzle */
    public void setPuzzel(Puzzel puzzel) {
        this.puzzel = puzzel;
        puzzel.setGameManager(gameManager);
    }

    /* Getter for Puzzle */
    public Puzzel getPuzzel() {
        return puzzel;
    }

    /* checks if the player could move to a specific direction */
    public boolean isItPossebelToMoveTo(int direction) {
        switch (direction) {
            case 0: // north
                return northNextTo != -1;
            case 1: // east
                return eastNextTo != -1;
            case 2: // south
                return southNextTo != -1;
            case 3: // west
                return westNextTo != -1;
            default:
                return false; // Invalid direction
        }
    }

    /* sets the game manager */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
