package components;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import utilities.Constants;
import utilities.Entity;
import utilities.Tools;

/**
 * Entity that, when the player intersects with it, can interact with it and/or change rooms.
 * The action upon the interaction is given through a {@link ChangeRequest}.
 *
 * @since 4.0, 6/6/2022
 * @author Shane Chen
 */
public class RoomChangeEntity extends Entity {

    /**
     * Change request, called upon interaction.
     */
    private ChangeRequest roomChangeHandler;

    /**
     * The index of the room to change to, or 16 if the entity does not cause a room change.
     */
    private final int roomChangeNumber;

    /**
     * Whether this object is disabled.
     */
    private boolean disabled;

    /**
     * The indicator to show the location of this entity for the player.
     */
    private ImageView indicator;

    /**
     * Instantiates this class.
     *
     * @param x
     * The x-coordinate of this entity.
     * @param y
     * The y-coordinate of this entity.
     * @param width
     * The width of this entity.
     * @param height
     * The height of this entity.
     * @param roomChangeNumber
     * The index of the room to change to, or 16 if the entity does not cause a room change.
     * @param roomChangeHandler
     * Change request, called upon interaction.
     */
    public RoomChangeEntity(int x, int y, int width, int height, int roomChangeNumber, ChangeRequest roomChangeHandler) {
        super(x, y, width, height, Color.TRANSPARENT);
        this.roomChangeHandler = roomChangeHandler;
        this.roomChangeNumber = roomChangeNumber;
        indicator = new ImageView(Tools.getImage(Constants.INDICATOR, 20, 20, true, true));
        indicator.setX(x+((double)width/2));
        indicator.setY(y+((double)height/2));
        indicator.visibleProperty().bind(this.visibleProperty());
        disabled = false;
    }

    /**
     * Gets the indicator.
     * @return
     * The indicator image.
     */
    public ImageView getIndicator() {
        return indicator;
    }

    /**
     * Gets the room change number.
     * @return
     * The room change number.
     */
    public int getRoomChangeNumber() {
        return roomChangeNumber;
    }

    /**
     * Calls the room change handler's implemented method.
     */
    public void onChangeRoomRequest() {
        roomChangeHandler.onChangeRequest();
    }

    /**
     * Set the room change handler's method.
     * @param changeRequest
     * the new implemented method.
     */
    public void setOnChangeRequest(ChangeRequest changeRequest) {
        roomChangeHandler = changeRequest;
    }

    /**
     * Gets the room change handler's implemented method.
     * @return
     * The room change handler's implemented method.
     */
    public ChangeRequest getOnChangeRequest() {
        return roomChangeHandler;
    }

    /**
     * Disables this instance.
     */
    public void disable() {
        disabled = true;
        this.setVisible(false);
    }

    /**
     * Enables this instance.
     */
    public void enable() {
        disabled = false;
        this.setVisible(true);
    }

    /**
     * Returns whether this instance is disabled.
     * @return
     * Whether this instance is disabled.
     */
    public boolean getDisabled() {
        return disabled;
    }
}
