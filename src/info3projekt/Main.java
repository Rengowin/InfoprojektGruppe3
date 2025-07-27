package info3projekt;
//we should programm in english like raeume to room and so on

import java.util.Scanner;

/*
 * Main class for the game loop.
 * and to start the game.
 */
public class Main {

    public static void main(String[] args) {
        gameloop();
    }

    /**
     * The main game loop.
     * It initializes the game and processes user input.
     */
    static void gameloop() {
        Scanner scanner = new Scanner(System.in);
        GameManager gameManager = new GameManager();
        gameManager.initialisGame();
        gameManager.classCommandsGet().getCommands();
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            gameManager.processInput(input);
        }
    }
}