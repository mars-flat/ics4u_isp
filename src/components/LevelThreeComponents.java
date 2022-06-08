package components;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Entity;
import utilities.Player;
import utilities.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelThreeComponents extends ScreenComponent {

    public static final int TOTAL_DIALOGUE = 20;
    public static final int TOTAL_ROOMS = 3;
    public static final int TOTAL_MINIGAMES = 5;

    private DialoguePopup[] levelThreeDialogue;
    private Popup activePopup;

    private SchoolRoom[] schoolRooms;
    private SchoolRoom currentRoom;

    private Minigame[] minigames;
    private Minigame activeMinigame;

    private Player player;
    private Text interactionText;

    public LevelThreeComponents() {
        super();

        levelThreeDialogue = new DialoguePopup[TOTAL_DIALOGUE];
        schoolRooms = new SchoolRoom[TOTAL_ROOMS];
        minigames = new Minigame[TOTAL_MINIGAMES];

        addComponents();
    }

    private void setupDialogue() {

    }

    private void setupRooms() {

        /*
         make sure the txt is in this order
         [room url]
         [room name]
         [number of other entities: N]
         [N lines: x y w h, space-separated]
         [number of room change entities: M]
         [M lines: x y w h i: room index, space separated]
         [player x]
         [player y]

         schoolRoom[i]:
         0 -> front foyer
         1 -> office
         2 -> washroom
         3 -> hallway 1
         4 -> library
         5 -> cafeteria
         6 -> shop
         7 -> storage room
         8 -> hallway 2
         9 -> classroom 1
         10 -> gym
         11 -> hallway 3
         12 -> classroom 2
         13 -> hallway 4
         14 -> classroom 3
         15 -> washroom 2
         */

        Scanner fr = null;
        try {
            fr = new Scanner(new File(Constants.DATA_PATH + "roominfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < TOTAL_ROOMS; ++i) {
            String url = Constants.DATA_PATH + fr.nextLine();
            String name = fr.nextLine();

            List<Entity> oe = new ArrayList<>();
            int n = Integer.parseInt(fr.nextLine());

            for (int j = 0; j < n; ++j) {
                String[] ndat = fr.nextLine().split(" ");
                int ex = Integer.parseInt(ndat[0]);
                int ey = Integer.parseInt(ndat[1]);
                int ew = Integer.parseInt(ndat[2]);
                int eh = Integer.parseInt(ndat[3]);
                oe.add(new Entity(ex, ey, ew, eh, Color.GRAY)); // TODO: make transparent
            }

            List<RoomChangeEntity> rce = new ArrayList<>();
            int m = Integer.parseInt(fr.nextLine());

            for (int j = 0; j < m; ++j) {
                String[] mdat = fr.nextLine().split(" ");
                int x = Integer.parseInt(mdat[0]);
                int y = Integer.parseInt(mdat[1]);
                int w = Integer.parseInt(mdat[2]);
                int h = Integer.parseInt(mdat[3]);
                int nxt = Integer.parseInt(mdat[4]);
                rce.add(new RoomChangeEntity(x, y, w, h, () -> setCurrentRoom(schoolRooms[nxt])));
            }

            int px = Integer.parseInt(fr.nextLine());
            int py = Integer.parseInt(fr.nextLine());

            schoolRooms[i] = new SchoolRoom(
                    new File(url),
                    name,
                    oe,
                    rce,
                    player,
                    px,
                    py,
                    this
                    );
        }

        setCurrentRoom(schoolRooms[0]);
    }

    private void setupMinigames() {

    }

    @Override
    public void addComponents() {
        player = new Player(120, 550, 40, 40, 4, false);

        setupDialogue();
        setupRooms();
        setupMinigames();

        interactionText = new Text(500, 500, "SPACE to interact");
        interactionText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 22));
        interactionText.setFill(Color.WHITE);
        interactionText.setVisible(false);
        this.getChildren().add(interactionText);
    }

    public void checkInBounds() {
        if (currentRoom != null) {
            interactionText.setVisible(currentRoom.checkInBounds());
        }
    }

    public void checkForRoomChange() {
        if (currentRoom != null) currentRoom.checkForRoomChange();
    }

    public Popup getActivePopup() {
        return activePopup;
    }

    public void setActivePopup(Popup newPopup) {
        if (activePopup != null) this.getChildren().remove(activePopup);
        activePopup = newPopup;
        if (activePopup != null) this.getChildren().add(activePopup);
    }

    public SchoolRoom getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(SchoolRoom newRoom) {
        System.out.println(newRoom);
        this.getChildren().remove(currentRoom);
        currentRoom = newRoom;

        this.getChildren().add(newRoom);
        changeBackground(currentRoom.getBackgroundImage());
        player.setX(currentRoom.getSpawnX());
        player.setY(currentRoom.getSpawnY());
        currentRoom.getChildren().add(player);
        currentRoom.getChildren().add(player.getCharacter());
    }

    public Minigame getActiveMinigame() {
        return activeMinigame;
    }

    public void setActiveMinigame(Minigame newMinigame) {
        if (newMinigame != null) this.getChildren().remove(newMinigame);
        activeMinigame = newMinigame;
        if (activeMinigame != null) this.getChildren().add(activeMinigame);
    }

    public List<Entity> getOtherEntities() {
        return currentRoom.getOtherEntities();
    }

    public Player getPlayer() {
        return player;
    }
}
