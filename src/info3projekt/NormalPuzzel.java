package info3projekt;

import java.util.Scanner;

/* Normal puzzle class for puzzles,extends the abstract class Puzzel.
 * Contains methods to generate and solve puzzles.
 */
public class NormalPuzzel extends Puzzel {
    private double solutionMath = 0;
    private int a, b;

    /* Constructor for NormalPuzzel */
    public NormalPuzzel(int puzzelTyp) {
        this.puzzelTyp = puzzelTyp;
        whatPuzzel();
    }

    /*
     * Method to generate random math problems based on the type of math operation
     */
    // 1 = addition, 2 = subtraction, 3 = multiplication
    public double randomMathNummbers(int whatMath) {
        a = (int) (Math.random() * 10) + 1; // Random number between 1 and 10
        b = (int) (Math.random() * 10) + 1; // Random number between 1 and 10
        switch (whatMath) {
            case 1:
                puzzelInfo = "what is " + a + " + " + b + "?";
                solutionMath = a + b;
                break;
            case 2:
                puzzelInfo = "what is " + a + " - " + b + "?";
                solutionMath = a - b;
                break;
            case 3:
                puzzelInfo = "what is " + a + " * " + b + "?";
                solutionMath = a * b;
                break;
        }
        return solutionMath;
    }

    /*
     * Method to determine the type of puzzle
     * This method sets the puzzle information based on the puzzle type.
     * and sets the solution for the puzzle. if the puzzel is a math random one it
     * also creates the math problem.
     */
    @Override
    public void whatPuzzel() {
        switch (puzzelTyp) {
            case 1:
                puzzelInfo = "What is the largest number in German up to 100 which name does not contain the letter 'u'?";
                solutionMath = 80;
                break;
            case 2:
                puzzelInfo = "What is the cross sum (digit sum) of 49?";
                solutionMath = 13;
                break;
            case 3:
                puzzelInfo = "Which two-digit prime number remains a prime number when its digits are reversed?";
                solutionMath = 11;
                break;
            case 4:
                puzzelInfo = "Which prime number is the only even one?";
                solutionMath = 2;
                break;
            case 5:
                puzzelInfo = "How many seconds are in half an hour?";
                solutionMath = 1800;
                break;
            case 6:
                puzzelInfo = "If you have 2 euros and double the amount each year, how much will you have after 4 years?";
                solutionMath = 32;
                break;
            case 7:
                puzzelInfo = "What is the smallest positive whole number that, when multiplied by 1.5, results in a whole number and is also divisible by 4?";
                solutionMath = 4;
                break;
            case 8:
                puzzelInfo = "What is the smallest positive number that leaves a remainder of 1 when divided by 2, a remainder of 2 when divided by 3, and a remainder of 3 when divided by 4?";
                solutionMath = 11;
                break;
            case 9:
                puzzelInfo = "What is the smallest positive number divisible by 5, but not divisible by 2, 3, or 4?";
                solutionMath = 5;
                break;
            case 10:
                puzzelInfo = "What is the smallest positive number divisible by both 7 and 3, but not by 2?";
                solutionMath = 21;
                break;
            case 11:
                randomMathNummbers(1);
                break;
            case 12:
                randomMathNummbers(2);
                break;
            case 13:
                randomMathNummbers(3);
                break;
            case -1:
                // if the room is empty/ no puzzel
                puzzelInfo = "here is no puzzel to slove";
                break;
            case -2:
                // if the player has cheated the puzzel
                puzzelInfo = "You have cheated the puzzel, you can now pass it without solving it.";
                break;
            case -3:
                // if the player has solved the puzzel
                puzzelInfo = "No more puzzel here, you can move on.";
            default:
                puzzelTyp = -1; // Set to -1 if the puzzel type is unknown
                puzzelInfo = "unknown puzzle type.";
                break;
        }
    }

    /*
     * Method to solve the puzzle
     * This method prompts the user for input, checks if the input is a valid
     * number,
     * and compares it to the solution. If correct, it marks the puzzle as solved.
     * If the input is incorrect, it calls the wrongAnz method to handle the
     * failure.
     * 
     * @param scanner Scanner object to read user input
     * 
     * @return true if the puzzle is solved correctly, false otherwise
     */
    @Override
    public boolean solve(Scanner scanner) {
        System.out.println(puzzelInfo);
        while (!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
        // Read the user input as a double
        double userSolution = scanner.nextDouble();
        if (userSolution == solutionMath) {
            System.out.println("Correct!");
            puzzelTyp = -3; // Mark as solved
            puzzelInfo = "No more puzzel here, you can move on.";
            return true;
        } else {
            System.out.println("Wrong!");
            wrongAnz();
            return false;
        }
    }

    /*
     * Method to reroll the puzzle
     * This method generates a new random puzzle type and calls whatPuzzel to set
     * the new puzzle information.
     * but with bad luck it can reroll to the same puzzle type.
     */
    public void rerollPuzzel() {
        double randomValue = Math.random() * 13 + 1;
        puzzelTyp = (int) randomValue;
        whatPuzzel();
    }

    /* Method to get the puzzle information */
    public String getPuzzle() {
        return puzzelInfo;
    }

    /* Method to get the solution of the puzzle */
    @Override
    Object getSolution() {
        return solutionMath;
    }

}
