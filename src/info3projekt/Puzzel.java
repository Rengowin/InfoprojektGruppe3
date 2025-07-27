package info3projekt;

import java.util.Scanner;

public abstract class Puzzel {
    String puzzelInfo;
    int puzzelTyp;
    GameManager gameManager;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    abstract void whatPuzzel();

    abstract boolean solve(Scanner scanner);

    public String getPuzzelInfo() {
        return puzzelInfo;
    }

    public int getPuzzelTyp() {
        return puzzelTyp;
    }

    public void setPuzzelInfo(String puzzelInfo) {
        this.puzzelInfo = puzzelInfo;
    }

    public void setPuzzelTyp(int puzzelTyp) {
        this.puzzelTyp = puzzelTyp;
    }

    public void cheat() {
        puzzelTyp = -2;
    }

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

    abstract Object getSolution();
}
