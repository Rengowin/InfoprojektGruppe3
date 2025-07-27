package info3projekt;

import java.util.Scanner;

/* this class represents a puzzle in the game 
 * It is an abstract class that defines the structure for different types of puzzles.
 * Each puzzle has a type, information, and methods to solve it.
 * It also includes methods for handling incorrect answers and cheating.
*/
public abstract class Puzzel {
    String puzzelInfo;
    int puzzelTyp;
    GameManager gameManager;

    /* Sets the GameManager for this puzzle */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /* sets the puzzel information to the typ as well as the answers */
    abstract void whatPuzzel();

    /* Solves the puzzle based on user input */
    abstract boolean solve(Scanner scanner);

    /* Returns the puzzle information */
    public String getPuzzelInfo() {
        return puzzelInfo;
    }

    /* Returns the puzzle type */
    public int getPuzzelTyp() {
        return puzzelTyp;
    }

    /* Sets the puzzle information */
    public void setPuzzelInfo(String puzzelInfo) {
        this.puzzelInfo = puzzelInfo;
    }

    /* Sets the puzzle type */
    public void setPuzzelTyp(int puzzelTyp) {
        this.puzzelTyp = puzzelTyp;
    }

    /*
     * sets the puzzle type to -2 when cheated and should only work for normal
     * puzzels :D
     * also could then be an abstract method or only be in normal puzzels to prevent
     * it but right now its prevented by the command class
     */
    public void cheat() {
        puzzelTyp = -2;
    }

    /* */
    public void wrongAnz() {
        // should set the players health -1 until he has 0
        // then defeat
        gameManager.getPlayer().setHealth(gameManager.getPlayer().getHealth() - 1);
        System.out.println("Wrong answer! Health left: " + gameManager.getPlayer().getHealth());
        if (gameManager.getPlayer().getHealth() <= 0) {
            System.out.println("You have no health left. Game over!");
            System.exit(0);
        }
    }

    /* Returns the solution of the puzzle */
    abstract public Object getSolution();
}
