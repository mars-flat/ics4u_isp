package components;

import javafx.scene.paint.Color;
import utilities.Entity;

public class RoomChangeEntity extends Entity {

    private ChangeRequest roomChangeHandler;
    private final int roomChangeNumber;
    private boolean disabled;

    public RoomChangeEntity(int x, int y, int width, int height, int roomChangeNumber, ChangeRequest roomChangeHandler) {
        super(x, y, width, height, Color.LIGHTBLUE); //TODO: set to transparent
        this.roomChangeHandler = roomChangeHandler;
        this.roomChangeNumber = roomChangeNumber;
        disabled = false;
    }

    public int getRoomChangeNumber() {
        return roomChangeNumber;
    }

    public void onChangeRoomRequest() {
        roomChangeHandler.onChangeRequest();
    }

    public void setOnChangeRequest(ChangeRequest dialogueChangeRequest) {
        roomChangeHandler = dialogueChangeRequest;
    }

    public ChangeRequest getOnChangeRequest() {
        return roomChangeHandler;
    }

    public void disable() {
        disabled = true;
    }

    public void enable() {
        disabled = false;
    }

    public boolean getDisabled() {
        return disabled;
    }
}
