package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Entity;
import utilities.Tools;

public class RoomChangeEntity extends Entity {

    private ChangeRequest roomChangeHandler;
    private final int roomChangeNumber;
    private boolean disabled;
    private Text indicator;

    public RoomChangeEntity(int x, int y, int width, int height, int roomChangeNumber, ChangeRequest roomChangeHandler) {
        super(x, y, width, height, Color.TRANSPARENT); //TODO: set to transparent
        this.roomChangeHandler = roomChangeHandler;
        this.roomChangeNumber = roomChangeNumber;
        indicator = new Text("!");
        indicator.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 16));
        indicator.setFill(Color.YELLOW);
        indicator.setX(x+(width/2));
        indicator.setY(y+(height/2));
        disabled = false;
    }

    public Text getIndicator() {
        return indicator;
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
