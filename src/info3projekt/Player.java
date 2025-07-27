package info3projekt;

import java.util.ArrayList;

/*
 * This class represents a player in the game.
 * It contains the player's name, health, number of hints,
 * items they possess, and the current room they are in.
 */
public class Player {
    private String name;
    private int health;
    private int anzHint;
    private ArrayList<Items> itemes = new ArrayList<>();
    private int currentRoom = 0;

    /* Constructor for Player */
    public Player(String name, Level level) {
        this.name = name;
        this.health = 3;
        this.anzHint = level.getAnzHints();
        currentRoom = 0;
    }

    /* Getter for the player's name */
    public String getName() {
        return name;
    }

    /* Getter for the player's HP */
    public int getHealth() {
        return health;
    }

    /* Getter for number of hints */
    public int getAnzHints() {
        return anzHint;
    }

    /* Getter for the current room */
    public int getCurrentRoom() {
        return currentRoom;
    }

    /* Setter for the player's HP */
    public void setHealth(int health) {
        this.health = health;
    }

    /* Method to add an item to the player's inventory */
    public void addToList(Items item) {
        itemes.add(item);
    }

    /* Getter for the player's inventory */
    public ArrayList<Items> getItemes() {
        return itemes;
    }

    /* Setter for the current room */
    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

}
