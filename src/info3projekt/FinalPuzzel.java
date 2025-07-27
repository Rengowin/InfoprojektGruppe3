package info3projekt;

import java.util.Scanner;
/* This class represents the final puzzle in the game and extends the Puzzel class. 
 * it contains the puzzels that the player must solve to win
 * and the solutions to those puzzles.
*/

public class FinalPuzzel extends Puzzel {

    private String[] puzzelInfos = new String[3];
    private Object[] solutions = new Object[3]; // Can be String or Double

    /* Constructor for the FinalPuzzel class */
    public FinalPuzzel(int puzzelTyp) {
        this.puzzelTyp = puzzelTyp;
        whatPuzzel();
    }

    /*
     * This method sets the puzzle information and solutions based on the puzzle
     * type it could have more then 3 puzzles, but for now it has only 3 puzzels
     * and the solutions are right now only Strings, but could be all other types
     * from oject
     * like Double, Integer, etc.
     */
    /*
     * bad code example for clean code :D
     * since we could again create subclasses for the different puzzels
     */
    @Override
    void whatPuzzel() {
        switch (puzzelTyp) {
            case 1:
                puzzelInfos[0] = "What comes once in a minute, twice in a moment, but never in a thousand years? Hint: 3 Strings";
                solutions[0] = "The letter M";
                puzzelInfos[1] = "I belong to you, but others use me more. What am I?";
                solutions[1] = gameManager.getPlayer().getName();
                puzzelInfos[2] = "The more you take away, the bigger I get. What am I?";
                solutions[2] = "hole";
                break;
            case 2:
                puzzelInfos[0] = "What comes down but never goes up?";
                solutions[0] = "Rain";
                puzzelInfos[1] = "What begins with T, ends with T, and has T inside it?";
                solutions[1] = "teapot";
                puzzelInfos[2] = "What has to be broken before you can use it?";
                solutions[2] = "egg";
                break;
            default:
                break;
        }
    }

    /*
     * This method is called to solve the puzzles
     * It takes a Scanner object as input to read the player's answers.
     * you need to solve all in one go or restart the solving process
     */
    @Override
    boolean solve(Scanner scanner) {
        for (int i = 0; i < puzzelInfos.length; i++) {
            System.out.println(puzzelInfos[i]);
            System.out.println("Pls give the solution to the puzzel:");
            Object solution = solutions[i];
            if (solution instanceof Double) {
                if (!scanner.hasNextDouble()) {
                    System.out.println("Wrong!");
                    scanner.next(); // consume invalid input
                    wrongAnz();
                    return false;
                }
                double answer = scanner.nextDouble();
                if (answer != (Double) solution) {
                    System.out.println("Wrong!");
                    wrongAnz();
                    scanner.next(); // consume invalid input
                    return false;
                }
            } else if (solution instanceof String) {
                String answer = scanner.next().toLowerCase();
                if (!answer.equals(((String) solution).toLowerCase())) {
                    System.out.println("Wrong!");
                    wrongAnz();
                    scanner.next(); // consume invalid input
                    return false;
                }
            }
            System.out.println("Correct!");
            System.out.println("You Won!!! with " + gameManager.getPlayer().getHealth() + " health left.");
            System.out.println("You can now leave the game with 'exit', and play again the other possible levels.");
        }
        return true;
    }

    /* to prevent getting the solution directly from an item */
    @Override
    public Object getSolution() {
        // Return the solutions for the puzzles
        return "no way to get the solution from the FinalPuzzel :D";
    }
}
