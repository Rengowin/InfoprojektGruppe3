package info3projekt.test;
import info3projekt.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class LevelTest {

    private Level level;
    private GameManager gameManager;
    private Player player;
    private Items[] item;
    private Puzzel[] puzzel;

    // beforeEach open before each test to create the player and a level for test purposes
    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
        level = new Level(3, 10, null, null, "Test Level", gameManager);
        Player player = new Player("TestPlayer", level);
        gameManager.setPlayer(player);
        Items[] items = new Items[10];
        Puzzel[] puzzel = new Puzzel[10];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Items(i, gameManager, "Item" + (i + 1));
            puzzel[i] = new NormalPuzzel(i+1);
        }
        level.setPuzzel(puzzel);
        level.setItems(items);
        this.player = player;
    }

    @Test
    void testLevelCreation() {
        assertEquals("Test Level", level.getLevelName());
        assertEquals(10, level.getAnzRooms());
    }

    @Test
    void testSetRoomNames() {
        String[] roomNames = {"Room1", "Room2", "Room3", "Room4", "Room5", "Room6", "Room7", "Room8", "Room9", "Room10"};
        String[] roomTexts = {"Text1", "Text2", "Text3", "Text4", "Text5", "Text6", "Text7", "Text8", "Text9", "Text10"};
        level.setRoomNames(roomNames);
        level.setRoomTexts(roomTexts);
        level.createRooms(1);
        assertEquals("Room1", level.getRoomName());
    }

    @Test
    void testSetRoomTexts() {
        String[] roomTexts = {"Text1", "Text2", "Text3", "Text4", "Text5", "Text6", "Text7", "Text8", "Text9", "Text10"};
        String[] roomNames = {"Room1", "Room2", "Room3", "Room4", "Room5", "Room6", "Room7", "Room8", "Room9", "Room10"};
        level.setRoomNames(roomNames);
        level.setRoomTexts(roomTexts);
        level.createRooms(1);
        player.setCurrentRoom(2); // Set player to the first room
        assertEquals("Text3", level.getRoomInfo());
    }
}
