package info3projekt;

import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private int anzHint;
    private ArrayList<Items> itemes = new ArrayList<>();
    private int currentRoom = 0;

    public Player(String name, Level level) {
        this.name = name;
        this.health = 3;
        this.anzHint = level.getAnzHints();
        currentRoom = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAnzHints() {
        return anzHint;
    }

    public int getCurrentRoom() {
        // currentRoom is of type int (primitive), so it cannot be null.
        // If you want to check for an uninitialized state, consider using a special
        // value like -1.
        if (currentRoom == -1) {
            System.out.println("Current room is not set. problem :D.");
        }
        return currentRoom;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addToList(Items item) {
        itemes.add(item);
    }

    public ArrayList<Items> getItemes() {
        return itemes;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

}
