package info3projekt.test;


import info3projekt.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class PlayerTest {

    private Player player;
    private Level level;
    private GameManager gameManager;

    // beforeEach open before each test to create the player and a level for test purposes
    @BeforeEach
    void setUp() {
        // since for testing the player we need a level for anzHints and this needs also
        // the gameManager
        gameManager = new GameManager();
        level = new Level(3, 5, null, null, "Test Level", gameManager);
        player = new Player("TestPlayer", level);
    }

    @Test
    void testPlayerCreation() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(3, player.getAnzHints());
    }

    @Test
    void testGetName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    void testHealthGetterSetter() {
        assertEquals(3, player.getHealth());

        player.setHealth(5);
        assertEquals(5, player.getHealth());

        player.setHealth(1);
        assertEquals(1, player.getHealth());
    }

    @Test
    void testGetAnzHints() {
        assertEquals(3, player.getAnzHints());
    }

    @Test
    void testInitialCurrentRoom() {
        assertEquals(0, player.getCurrentRoom());
    }

    @Test
    void testAddToList() {
        Items item = new Items(1, gameManager, "TestItem");
        player.addToList(item);
        assertEquals(1, player.getItemes().size());
        assertEquals("TestItem", player.getItemes().get(0).getItemName());
    }
}
