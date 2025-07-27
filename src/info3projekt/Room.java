package info3projekt;

public abstract class Room {
    // the get Rooms needs to change i should not throws execptions because if -1 is
    // like no room to this direktions in case of lineare level or so

    String roomText;
    int northNextTo;
    int eastNextTo;
    int southNextTo;
    int westNextTo;
    String roomName;
    Puzzel puzzel;
    GameManager gameManager;
    Items item;

    public String getRoomName() {
        return roomName;
    }

    public String getRoomInfo() {
        return roomText;
    }

    public int[] getNextTo() {
        return new int[] { northNextTo, eastNextTo, southNextTo, westNextTo };
    }

    public int getNorthNextTo() {
        return northNextTo;
    }

    public int getEastNextTo() {
        return eastNextTo;
    }

    public int getSouthNextTo() {
        return southNextTo;
    }

    public int getWestNextTo() {
        return westNextTo;
    }

    public void setNextTo(int north, int east, int south, int west) {
        this.northNextTo = north;
        this.eastNextTo = east;
        this.southNextTo = south;
        this.westNextTo = west;
    }

    public void setRoomText(String text) {
        this.roomText = text;
    }

    public boolean hasItem() {
        if (item != null && item.itemEffect != -1) {
            return true;
        }
        return false;
    }

    public void setItems(Items item) {
        this.item = item;
    }

    public void setPuzzel(Puzzel puzzel) {
        this.puzzel = puzzel;
        puzzel.setGameManager(gameManager);
    }

    public Puzzel getPuzzel() {
        return puzzel;
    }

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

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
