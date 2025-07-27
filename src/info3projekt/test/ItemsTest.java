package info3projekt.test;
import info3projekt.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class ItemsTest {

    private Items item;
    private GameManager gameManager;

    // beforeEach open before each test to create the player and a level for test purposes
    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
    }

    @Test
    void testItemCreation() {
        item = new Items(0, gameManager, "TestItem");
        assertEquals("TestItem", item.getItemName());
    }

    @Test
    void testItemNames() {
        Items item1 = new Items(1, gameManager, "HintItem");
        Items item2 = new Items(2, gameManager, "HealthItem");
        Items item3 = new Items(3, gameManager, "RerollItem");

        assertEquals("HintItem", item1.getItemName());
        assertEquals("HealthItem", item2.getItemName());
        assertEquals("RerollItem", item3.getItemName());
    }

    @Test
    void testUseSomeitems() {
        item = new Items(-1, gameManager, "NoItem");
        Object result = item.useItem();
        assertEquals("No item.", result);
        Items item2 = new Items(2, gameManager, "HealthItem");
        //create a player to test the item use
        Player player = new Player("TestPlayer", new Level(3, 10, null, null, "Test Level", gameManager));
        gameManager.setPlayer(player);
        item2.useItem();
        assertEquals(4, player.getHealth());
    }
}
