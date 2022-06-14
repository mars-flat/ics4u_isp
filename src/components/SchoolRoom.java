package components;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Entity;
import utilities.Player;
import utilities.Tools;

import java.io.File;
import java.util.List;

/**
 * School room container.
 *
 * @since 4.1, 6/7/2022
 * @author Shane Chen
 */
public class SchoolRoom extends ScreenComponent {

    /*
     * The background image of the school room.
     */
    private File background;

    /*
     * The name of the room.
     */
    private String roomName;

    /*
     * The Player entity instance.
     */
    private Player player;

    /**
     * List of the other entites (i.e., clipping).
     */
    private List<Entity> otherEntities;

    /**
     * List of interactable entities.
     */
    private List<RoomChangeEntity> roomChangers;

    /**
     * Parent instance, {@link LevelThreeComponents}
     */
    private LevelThreeComponents controller;

    /**
     * Change request handler's implemented method.
     */
    private ChangeRequest roomEnteredChangeRequest;

    /**
     * Spawn coordinates.
     */
    private int sx;
    private int sy;

    /**
     * Instantiates this class.
     *
     * @param background
     * The room that is displayed.
     * @param roomName
     * The name of the room.
     * @param otherEntities
     * A list of the other entities, such as clipping.
     * @param roomChangers
     * A list of the interactable room changer objects.
     * @param player
     * The player.
     * @param sx
     * The spawn x-coordinate.
     * @param sy
     * The spawn y-coordinate.
     * @param controller
     * {@link LevelThreeComponents} instance.
     * @param roomEnteredChangeRequest
     * The implemented method for the change request.
     *
     */
    public SchoolRoom(File background,
                      String roomName,
                      List<Entity> otherEntities,
                      List<RoomChangeEntity> roomChangers,
                      Player player, int sx, int sy, // ensure sx and sy are not in any room changers bounds
                      LevelThreeComponents controller,
                      ChangeRequest roomEnteredChangeRequest) {
        super();
        this.background = background;
        this.roomName = roomName;
        this.otherEntities = otherEntities;
        this.roomChangers = roomChangers;
        this.player = player;
        this.sx = sx;
        this.sy = sy;
        this.roomEnteredChangeRequest = roomEnteredChangeRequest;

        this.controller = controller;
        addComponents();
    }

    /**
     * Checks each room change entity for intersection with the player.
     * If the player is in a room change entity during this method call,
     * call the change request method of that object.
     */
    public void checkForRoomChange() {
        for (RoomChangeEntity r : roomChangers) {
            if (r.getDisabled()) continue;
            if (player.intersects(r.getBoundsInLocal())) {
                r.onChangeRoomRequest();
                break;
            }
        }
    }

    /**
     * Checks each room change entity for intersection with the player.
     * If the player is in a room change entity, display the interaction message.
     * @return
     * Whether the player is intersecting any room change entity.
     */
    public int checkInBounds() {
        for (RoomChangeEntity r : roomChangers) {
            if (r.getDisabled()) continue;
            if (player.intersects(r.getBoundsInLocal())) {
                return r.getRoomChangeNumber();
            }
        }
        return -1;
    }

    /**
     * What happens when the player enters the room.
     */
    public void onRoomEntered() {
        changeBackground(background);
        player.setX(sx);
        player.setY(sy);
        getChildren().add(player);
        getChildren().add(player.getCharacter());
        roomEnteredChangeRequest.onChangeRequest();
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {
        for (Entity e : otherEntities) {
            this.getChildren().add(e);
        }
        for (RoomChangeEntity r : roomChangers) {
            this.getChildren().add(r);
        }

        Text roomNameText = new Text(50, 80, roomName);
        roomNameText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 26));
        roomNameText.setFill(Color.WHITE);
        this.getChildren().add(roomNameText);
    }

    /**
     * Returns the list of all the other entities.
     * @return
     * A list of all the other entities.
     */
    public List<Entity> getOtherEntities() {
        return otherEntities;
    }

    /**
     * Gets all the room change entities.
     * @return
     * All the room changers.
     */
    public List<RoomChangeEntity> getRoomChangers() { return roomChangers; }

    /**
     * Sets the spawn x-coordinate.
     * @param newSx
     * The new x-coordinate.
     */
    public void setSpawnX(int newSx) {
        sx = newSx;
    }

    /**
     * Sets the spawn y-coordinate.
     * @param newSy
     * The new y-coordinate.
     */
    public void setSpawnY(int newSy) {
        sy = newSy;
    }

    /**
     * Set what happens when a player enters the room, independent
     * of the default actions.
     * @param newRequest
     * The new implemented method.
     */
    public void setRoomEnteredChangeRequest(ChangeRequest newRequest) {
        roomEnteredChangeRequest = newRequest;
    }
}
