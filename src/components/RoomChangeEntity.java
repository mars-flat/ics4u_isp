package components;

import javafx.scene.paint.Color;
import utilities.Entity;

public class RoomChangeEntity extends Entity {

    private ChangeRequest roomChangeHandler;

    public RoomChangeEntity(int x, int y, int width, int height, ChangeRequest roomChangeHandler) {
        super(x, y, width, height, Color.GRAY); //TODO: set to transparent
        this.roomChangeHandler = roomChangeHandler;
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
}
