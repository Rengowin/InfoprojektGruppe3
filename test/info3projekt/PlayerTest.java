package info3projekt;

public class PlayerTest {

    private Player player;
    private Level level;
    private GameManager gameManager;

    // beforeEach
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
    @DisplayName("Player health should be correctly set and retrieved")
    void testHealthGetterSetter() {
        assertEquals(3, player.getHealth());

        player.setHealth(5);
        assertEquals(5, player.getHealth());

        player.setHealth(1);
        assertEquals(1, player.getHealth());

        player.setHealth(0);
        assertEquals(0, player.getHealth());
    }

    @Test
    @DisplayName("Player hints should be correctly retrieved")
    void testGetAnzHints() {
        assertEquals(3, player.getAnzHints());
    }

    @Test
    @DisplayName("Current room should start at 0")
    void testInitialCurrentRoom() {
        assertEquals(0, player.getCurrentRoom());
    }

    @Test
    @DisplayName("Player should handle negative health values")
    void testNegativeHealth() {
        player.setHealth(-1);
        assertEquals(-1, player.getHealth());
    }

    @Test
    @DisplayName("Player should handle very high health values")
    void testHighHealth() {
        player.setHealth(100);
        assertEquals(100, player.getHealth());
    }

    @Test
    @DisplayName("Player with null name should still work")
    void testNullName() {
        Player nullNamePlayer = new Player(null, level);
        assertNull(nullNamePlayer.getName());
    }

    @Test
    @DisplayName("Player with empty name should work")
    void testEmptyName() {
        Player emptyNamePlayer = new Player("", level);
        assertEquals("", emptyNamePlayer.getName());
    }
}
