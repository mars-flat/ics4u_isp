package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

    public static final int TOTAL_DIALOGUE = 3;
    public static final int TOTAL_ROOMS = 16;
    public static final int TOTAL_MINIGAMES = 5;

    private String[] dialogue;
    private DialoguePopup[] levelThreeDialogue;
    private Popup activePopup;

    private SchoolRoom[] schoolRooms;
    private SchoolRoom currentRoom;

    private Minigame[] minigames;
    private Minigame activeMinigame;

    private Player player;
    private Text interactionText;

    private String[] rceText;
    private boolean[] roomFound;
    private boolean[] minigameInteracted;

    public LevelThreeComponents() {
        super();

        levelThreeDialogue = new DialoguePopup[TOTAL_DIALOGUE];
        schoolRooms = new SchoolRoom[TOTAL_ROOMS];
        minigames = new Minigame[TOTAL_MINIGAMES];
        roomFound = new boolean[TOTAL_ROOMS+1];
        minigameInteracted = new boolean[TOTAL_MINIGAMES+1];

        addComponents();
    }

    private void setupDialogue() {
        dialogue = new String[] {
                "Ugh, school. And there's so much on the agenda today that I must do before I leave...",
                "Oh, how I wish I could leave this dreaded place. But I can't.",
                "Woah, the library! Maybe I can find the book I need for English class here."
        };
        for (int i = 0; i < TOTAL_DIALOGUE; ++i) {
            levelThreeDialogue[i] = new DialoguePopup(
                    new ImageView(Tools.getImage(Constants.YOUNGER_SIBLING, 240, 280, true, true)),
                    "Younger Sibling", dialogue[i], () -> this.setActivePopup(null)
            );
        }
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
         2 -> washroom 1
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
        rceText = new String[] {
                "E to go to Front foyer",
                "E to go to Office",
                "E to go to Washroom 1",
                "E to go to Hallway 1",
                "E to go to Library",
                "E to go to Cafeteria",
                "E to go to Shop",
                "E to go to Storage room",
                "E to go to Hallway 2",
                "E to go to Classroom 1",
                "E to go to Gym",
                "E to go to Hallway 3",
                "E to go to Classroom 2",
                "E to go to Hallway 4",
                "E to go to Classroom 3",
                "E to go to Washroom 2",
                "E to interact"
        };

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
                oe.add(new Entity(ex, ey, ew, eh, Color.TRANSPARENT)); // TODO: make transparent Color.color(0.1,0.1,0.1,0.5)
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
                rce.add(new RoomChangeEntity(x, y, w, h, nxt, () -> {
                    if (nxt != 16) {
                        setCurrentRoom(schoolRooms[nxt]);
                        roomFound[nxt] = true;
                    }
                }));
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
                    this,
                    () -> {}
                    );
        }

        schoolRooms[0].getRoomChangers().get(0).setOnChangeRequest(() -> setActivePopup(levelThreeDialogue[1]));

        RoomChangeEntity libMinigameLauncher = new RoomChangeEntity(470, 330, 50, 50, 16, () -> {});
        libMinigameLauncher.setOnChangeRequest(() -> {
            setActiveMinigame(minigames[0]);
            libMinigameLauncher.disable();
        });
        schoolRooms[4].getRoomChangers().add(libMinigameLauncher);
        schoolRooms[4].getChildren().add(libMinigameLauncher);
        schoolRooms[4].setRoomEnteredChangeRequest(() -> {
            if (!roomFound[4]) setActivePopup(levelThreeDialogue[2]);
        });

        roomFound[0] = true;
        setCurrentRoom(schoolRooms[0]);
    }

    private void setupMinigames() {
        minigames[0] = new LibraryMinigame(new ImageView(Tools.getImage(Constants.LIBRARIAN, 960, 720, true, true)), this);
    }

    @Override
    public void addComponents() {
        player = new Player(120, 550, 40, 60, 10, 20, 0, false);

        setupDialogue();
        setupRooms();
        setupMinigames();

        interactionText = new Text(360, 700, "SPACE to interact");
        interactionText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 22));
        interactionText.setFill(Color.WHITE);
        interactionText.setTextAlignment(TextAlignment.CENTER);
        interactionText.setVisible(false);
        this.getChildren().add(interactionText);

        setActivePopup(levelThreeDialogue[0]);
    }

    public void checkInBounds() {
        if (currentRoom != null && currentRoom.checkInBounds() != -1 && activeMinigame == null) {
            int id = currentRoom.checkInBounds();
            this.getChildren().remove(interactionText);
            interactionText.setText(roomFound[id] ? rceText[id] : rceText[16]);
            this.getChildren().add(interactionText);
            interactionText.setVisible(true);
        } else {
            interactionText.setVisible(false);
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
        this.getChildren().remove(currentRoom);
        currentRoom = newRoom;
        this.getChildren().add(newRoom);
        currentRoom.onRoomEntered();
    }

    public Minigame getActiveMinigame() {
        return activeMinigame;
    }

    public void setActiveMinigame(Minigame newMinigame) {
        if (activeMinigame != null) this.getChildren().remove(activeMinigame);
        activeMinigame = newMinigame;
        if (activeMinigame != null) {
            this.getChildren().add(activeMinigame);
            activeMinigame.onLaunch();
        }
    }

    public List<Entity> getOtherEntities() {
        return currentRoom.getOtherEntities();
    }

    public Player getPlayer() {
        return player;
    }
}
