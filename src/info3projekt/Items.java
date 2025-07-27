package info3projekt;

/**
 * The Items class represents items in the game that can have various effects.
 * Each item has an effect type, a name, and can be used by the player.
 */
public class Items {
    String itemName;
    int itemEffect;
    GameManager gameManager;
    boolean isUsed; // Indicates if the item has been used

    /**
     * Constructor for the Items class.
     *
     * @param itemEffect  The effect of the item (0: no effect, 1: puzzle hint,
     *                    2: health increase, 3: reroll puzzle, -1: no item).
     * @param gameManager The GameManager instance to access game states and other
     *                    parts of the game.
     * @param itemName    The name of the item.
     */
    public Items(int itemEffect, GameManager gameManager, String itemName) {
        this.itemEffect = itemEffect;
        this.gameManager = gameManager;
        this.itemName = itemName;
        this.isUsed = false;
    }

    /* Returns the name of the item. */
    public String getItemName() {
        return itemName;
    }

    /*
     * Returns the effect of the item.
     * 0: no effect, 1: puzzle hint, 2: health increase,
     * 3: reroll puzzle, -1: no item
     */
    /*
     * every item effect could be its own class or every item and then be called
     * with the parent class item
     */
    public Object useItem() {
        if (isUsed) {
            return "you already used this item.";
        }
        switch (itemEffect) {
            case 0:
                return null;
            case 1:
                Object solution = gameManager.getPuzzel()[gameManager.getPlayer().getCurrentRoom()].getSolution();
                // Check for the special final room message
                if (solution instanceof String && ((String) solution).contains("no way to get the solution")) {
                    return "hehe for this Room they is no way to get the solution, you have to slove the puzzles :D";
                } else if (solution instanceof String) {
                    return "This item gives you a hint for the puzzle. Or better said, it tells you the password: "
                            + solution;
                } else if (solution instanceof Double) {
                    return "This item gives you a hint for the puzzle. The solution is: " + solution;
                }
                break;
            case 2:
                gameManager.getPlayer().setHealth(gameManager.getPlayer().getHealth() + 1);
                return "This item increases your health by 1. Your current health is now: "
                        + gameManager.getPlayer().getHealth();
            case 3:
                Object puzzel = gameManager.getPuzzel()[gameManager.getPlayer().getCurrentRoom()];
                if (puzzel instanceof NormalPuzzel) {
                    ((NormalPuzzel) puzzel).rerollPuzzel();
                    return "This item rerolls the puzzle. The new puzzle is: "
                            + ((NormalPuzzel) puzzel).getPuzzle();
                } else {
                    return "This item cannot reroll the final puzzle";
                }
            case -1:
                return "No item.";
            default:
                return "Unknown item effect.";
        }
        return null;
    }

}
