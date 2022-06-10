package components;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Entity;
import utilities.Player;
import utilities.Tools;

import java.io.File;
import java.util.List;

public class SchoolRoom extends ScreenComponent {

    private File background;

    private String roomName;

    private Player player;

    private List<Entity> otherEntities;

    private List<RoomChangeEntity> roomChangers;

    private LevelThreeComponents controller;

    private ChangeRequest roomEnteredChangeRequest;

    private int sx;
    private int sy;

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

    public void checkForRoomChange() {
        for (RoomChangeEntity r : roomChangers) {
            if (r.getDisabled()) continue;
            if (player.intersects(r.getBoundsInLocal())) {
                r.onChangeRoomRequest();
                break;
            }
        }
    }

    public int checkInBounds() {
        for (RoomChangeEntity r : roomChangers) {
            if (r.getDisabled()) continue;
            if (player.intersects(r.getBoundsInLocal())) {
                return r.getRoomChangeNumber();
            }
        }
        return -1;
    }

    public void onRoomEntered() {
        changeBackground(background);
        player.setX(sx);
        player.setY(sy);
        getChildren().add(player);
        getChildren().add(player.getCharacter());
        roomEnteredChangeRequest.onChangeRequest();
    }

    @Override
    public void addComponents() {
        for (Entity e : otherEntities) {
            this.getChildren().add(e);
        }
        for (RoomChangeEntity r : roomChangers) {
            this.getChildren().add(r);
        }

        Text roomNameText = new Text(50, 100, roomName);
        roomNameText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 26));
        roomNameText.setFill(Color.WHITE);
        this.getChildren().add(roomNameText);
    }

    public List<Entity> getOtherEntities() {
        return otherEntities;
    }

    public List<RoomChangeEntity> getRoomChangers() { return roomChangers; }

    public void setSpawnX(int newSx) {
        sx = newSx;
    }

    public void setSpawnY(int newSy) {
        sy = newSy;
    }

    public void setRoomEnteredChangeRequest(ChangeRequest newRequest) {
        roomEnteredChangeRequest = newRequest;
    }
}
