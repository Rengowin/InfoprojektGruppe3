package info3projekt;

/**
 * NormalRoom class represents a normal room in the game.
 * It extends the Room class and initializes the room with a name and text.
 * since the room class has most of the methods we need
 */
public class NormalRoom extends Room {

    public NormalRoom(String roomName, String roomText) {
        this.roomName = roomName;
        this.roomText = roomText;
    }

}
