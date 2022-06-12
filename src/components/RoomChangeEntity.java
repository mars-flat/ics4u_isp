package components;

import javafx.scene.image.ImageView;
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
    private ImageView indicator;

    public RoomChangeEntity(int x, int y, int width, int height, int roomChangeNumber, ChangeRequest roomChangeHandler) {
        super(x, y, width, height, Color.TRANSPARENT); //TODO: set to transparent
        this.roomChangeHandler = roomChangeHandler;
        this.roomChangeNumber = roomChangeNumber;
        indicator = new ImageView(Tools.getImage(Constants.INDICATOR, 20, 20, true, true));
        indicator.setX(x+((double)width/2));
        indicator.setY(y+((double)height/2));
        indicator.visibleProperty().bind(this.visibleProperty());
        disabled = false;
    }

    public ImageView getIndicator() {
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
        this.setVisible(false);
    }

    public void enable() {
        disabled = false;
        this.setVisible(true);
    }

    public boolean getDisabled() {
        return disabled;
    }
}
