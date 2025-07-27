package info3projekt;

import java.util.Scanner;

public class FinalPuzzel extends Puzzel {

    private String[] puzzelInfos = new String[3];
    private Object[] solutions = new Object[3]; // Can be String or Double

    public FinalPuzzel(int puzzelTyp) {
        this.puzzelTyp = puzzelTyp;
        whatPuzzel();
    }

    // Boss has 3 puzzles that need to be solved and puzzelTyp is used for the
    // differnt levels of the game
    @Override
    void whatPuzzel() {
        switch (puzzelTyp) {
            case 1:
                puzzelInfos[0] = "Type A";
                solutions[0] = "A";
                puzzelInfos[1] = "Type B";
                solutions[1] = "B";
                puzzelInfos[2] = "Type C";
                solutions[2] = "C";
                break;
            case 2:
                puzzelInfos[0] = "Type A";
                solutions[0] = "A";
                puzzelInfos[1] = "Type B";
                solutions[1] = "B";
                puzzelInfos[2] = "Type C";
                solutions[2] = "C";
                break;
            default:
                break;
        }
    }

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

    @Override
    Object getSolution() {
        // Return the solutions for the puzzles
        return "no way to get the solution from the FinalPuzzel :D";
    }
}
