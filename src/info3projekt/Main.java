package info3projekt;
//we should programm in english like raeume to room and so on

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        gameloop();
    }

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