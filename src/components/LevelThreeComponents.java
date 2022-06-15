package components;

import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import scenes.LevelThreeScreen;
import utilities.Constants;
import utilities.Entity;
import utilities.Player;
import utilities.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Components for the third level of the game.
 *
 * @since 4.0, 5/6/2022
 * @author Shane Chen
 */
public class LevelThreeComponents extends ScreenComponent {

    /**
     * Constants denoting asset counts for dialogue, rooms, and minigames
     */
    public static final int TOTAL_DIALOGUE = 7;
    public static final int TOTAL_ROOMS = 16;
    public static final int TOTAL_MINIGAMES = 5;

    /**
     * Stopwatch instance.
     */
    private Stopwatch stopwatch;

    /**
     * Anxiety bar component instance.
     */
    private AnxietyBar anxietyBar;

    /**
     * Dialogue containers.
     */
    private String[] dialogue;
    private DialoguePopup[] levelThreeDialogue;

    /**
     * Active popup (i.e., dialogue, agenda), or null to indicate that there is no current popup.
     */
    private Popup activePopup;

    /**
     * School room container.
     */
    private SchoolRoom[] schoolRooms;

    /**
     * The current room. Should not be null.
     */
    private SchoolRoom currentRoom;

    /**
     * Minigame container.
     */
    private Minigame[] minigames;

    /**
     * The active minigame, or null to indicate that there is no active minigame.
     */
    private Minigame activeMinigame;

    /**
     * The player. Controls are handled in {@link LevelThreeScreen}.
     */
    private Player player;

    /**
     * Interaction text when the player walks in the bounds of an interaction (i.e., {@link RoomChangeEntity}) entity.
     */
    private Text interactionText;

    /**
     * Room change entity text.
     */
    private String[] rceText;

    /**
     * Whether a room has been found.
     */
    private boolean[] roomFound;

    /**
     * Whether a minigame has been interacted. Minigames may only be interacted once.
     */
    private boolean[] minigameInteracted;

    /**
     * The agenda component.
     */
    private Popup agenda;

    /**
     * Checks within the agenda to indicate whether a minigame has been played or not.
     */
    private ImageView[] checks;

    /**
     * The agenda icon that can be clicked to open the agenda.
     */
    private ImageView agendaIcon;

    /**
     * Ending screen.
     */
    private Popup ending;

    /**
     * Game over screen.
     */
    private Popup gameOver;

    /**
     * Instantiates this class and its components.
     */
    public LevelThreeComponents() {
        super();

        levelThreeDialogue = new DialoguePopup[TOTAL_DIALOGUE];
        schoolRooms = new SchoolRoom[TOTAL_ROOMS];
        minigames = new Minigame[TOTAL_MINIGAMES];
        roomFound = new boolean[TOTAL_ROOMS+1];
        minigameInteracted = new boolean[TOTAL_MINIGAMES+1];
        stopwatch = new Stopwatch();

        addComponents();

        this.setOnMouseClicked(event -> {
            System.out.println(event.getX() + " " + event.getY());
        });
    }

    /**
     * Sets up the dialogue for this level.
     */
    private void setupDialogue() {
        dialogue = new String[] {
                "Ugh, school. And there's so much on the agenda today that I must do before I leave...",
                "Oh, how I wish I could leave this dreaded place. But I can't until my tasks are complete.",
                "Woah, the library! Maybe I can find the book I need for English class here.",
                "I'm hungry, maybe I should buy something here.",
                "I's lunchtime... maybe I should try talking with others instead of eating in the bathroom again.",
                "Wow. Math. My favourite subject. I love math. Yay.",
                "This door is locked."
        };
        for (int i = 0; i < TOTAL_DIALOGUE; ++i) {
            levelThreeDialogue[i] = new DialoguePopup(
                    new ImageView(Tools.getImage(Constants.YOUNGER_SIBLING, 240, 280, true, true)),
                    "Younger Sibling", dialogue[i], () -> this.setActivePopup(null)
            );
        }
    }

    /**
     * Sets up the rooms for this level. Reads from {@code roominfo.txt}.
     * Also sets up messages for interaction, minigame launchers,
     * and dialogue when entering rooms.
     *
     * make sure the txt is in this order:
     * [room url]
     * [room name]
     * [number of other entities: N]
     * [N lines: x y w h, space-separated]
     * [number of room change entities: M]
     * [M lines: x y w h i: room index, space separated]
     * [player x]
     * [player y]
     *
     * schoolRoom[i] should be:
     * 0 -> front foyer
     * 1 -> office
     * 2 -> washroom 1
     * 3 -> hallway 1
     * 4 -> library
     * 5 -> cafeteria
     * 6 -> shop
     * 7 -> storage room
     * 8 -> hallway 2
     * 9 -> classroom 1
     * 10 -> gym
     * 11 -> hallway 3
     * 12 -> classroom 2
     * 13 -> hallway 4
     * 14 -> classroom 3
     * 15 -> washroom 2
     */
    private void setupRooms() {
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
                int gx = Integer.parseInt(mdat[5]);
                int gy = Integer.parseInt(mdat[6]);

                rce.add(new RoomChangeEntity(x, y, gx, gy, w, h, nxt, () -> {
                    if (nxt != 16) {
                        setCurrentRoom(schoolRooms[nxt], gx, gy);
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

        // dialogue on room enter
        schoolRooms[0].getRoomChangers().get(0).setOnChangeRequest(() -> {
            if (tasksComplete()) {
                stopwatch.stop();
                setActivePopup(ending);
            }
            else setActivePopup(levelThreeDialogue[1]);
        });
        schoolRooms[13].getRoomChangers().get(0).setOnChangeRequest(() -> {
            if (tasksComplete()) {
                stopwatch.stop();
                setActivePopup(ending);
            }
            else setActivePopup(levelThreeDialogue[1]);
        });
        schoolRooms[3].getRoomChangers().get(0).setOnChangeRequest(() -> {
            setActivePopup(levelThreeDialogue[6]);
        });
        schoolRooms[4].setRoomEnteredChangeRequest(() -> {
            if (!roomFound[4]) setActivePopup(levelThreeDialogue[2]);
        });
        schoolRooms[5].setRoomEnteredChangeRequest(() -> {
            if (!roomFound[5]) setActivePopup(levelThreeDialogue[4]);
        });
        schoolRooms[6].setRoomEnteredChangeRequest(() -> {
            if (!roomFound[6]) setActivePopup(levelThreeDialogue[3]);
        });
        schoolRooms[9].setRoomEnteredChangeRequest(() -> {
            if (!roomFound[9]) {
                setActiveMinigame(minigames[3]);
                setMinigameDone(3, null);
            }
        });
        schoolRooms[12].setRoomEnteredChangeRequest(() -> {
            if (!roomFound[12]) {
                setActivePopup(levelThreeDialogue[5]);
            }
        });
        levelThreeDialogue[5].setOnChangeRequest(() -> {
            setActiveMinigame(minigames[0]);
            setMinigameDone(0, null);
        });

        // minigame 1
        RoomChangeEntity friendMinigameLauncher = new RoomChangeEntity(600, 400, 0, 0, 50, 50, 16, () -> {});
        friendMinigameLauncher.setOnChangeRequest(() -> {
            setActiveMinigame(minigames[1]);
            setMinigameDone(1, friendMinigameLauncher);
        });
        schoolRooms[5].getRoomChangers().add(friendMinigameLauncher);
        schoolRooms[5].getChildren().add(friendMinigameLauncher);

        // minigame 2
        RoomChangeEntity shopMinigameLauncher = new RoomChangeEntity(532, 362, 0, 0, 50, 40, 16, () -> {});
        shopMinigameLauncher.setOnChangeRequest(() -> {
            setActiveMinigame(minigames[2]);
            setMinigameDone(2, shopMinigameLauncher);
        });
        schoolRooms[6].getRoomChangers().add(shopMinigameLauncher);
        schoolRooms[6].getChildren().add(shopMinigameLauncher);


        // minigame 4
        RoomChangeEntity libMinigameLauncher = new RoomChangeEntity(470, 330, 0, 0, 50, 50, 16, () -> {});
        libMinigameLauncher.setOnChangeRequest(() -> {
            setActiveMinigame(minigames[4]);
            setMinigameDone(4, libMinigameLauncher);
        });
        schoolRooms[4].getRoomChangers().add(libMinigameLauncher);
        schoolRooms[4].getChildren().add(libMinigameLauncher);

        roomFound[0] = roomFound[3] = roomFound[8] = roomFound[11] = roomFound[13] = true;
        setCurrentRoom(schoolRooms[0], 330, 290);
    }

    /**
     * Sets whether the minigame has been played or interacted with.
     * @param minigameIdx
     * The index of the minigame.
     * @param toDisable
     * The RoomChangeEntity to disable, if applicable (null otherwise). Some minigames are launched
     * as the player enters the room.
     */
    public void setMinigameDone(int minigameIdx, RoomChangeEntity toDisable) {
        minigameInteracted[minigameIdx] = true;
        checks[minigameIdx].setVisible(true);
        if (toDisable != null) toDisable.disable();
    }

    /**
     * Instantiates minigames and stores it in their container.
     */
    private void setupMinigames() {
        minigames[0] = new MathMinigame(new ImageView(Tools.getImage(new File(Constants.DATA_PATH + "rooms\\classroom2.png"),
                960, 720, true, true)), this);
        minigames[1] = new FriendMinigame(new ImageView(Tools.getImage(new File(Constants.DATA_PATH + "rooms\\cafeteria.png"),
                960, 720, true, true)), this);
        minigames[2] = new ShopMinigame(new ImageView(Tools.getImage(Constants.CASHIER, 960, 720, true, true)), this);
        minigames[3] = new PresentationMinigame(new ImageView(Tools.getImage(Constants.TEACHER_BACKGROUND_1, 960, 720, true, true)), this);
        minigames[4] = new LibraryMinigame(new ImageView(Tools.getImage(Constants.LIBRARIAN_BACKGROUND, 960, 720, true, true)), this);
    }

    /**
     * Sets up the agenda component.
     */
    private void setupAgenda() {
        agenda = new Popup(() -> this.setActivePopup(null));
        ImageView journalPage = new ImageView(Tools.getImage(Constants.JOURNAL_BOX, 960, 720, true, true));

        String[] tasks = new String[] {
            "math class in classroom 2 :(",
            "make a friend at lunch???",
            "buy lunch from the store",
            "do a presentation at classroom 1 T_T",
            "find the book for English at the library"
        };
        ImageView page = new ImageView(Tools.getImage(Constants.JOURNAL_BOX, 960, 720, true, true));

        Rectangle darken = new Rectangle(0, 0, 960, 720);
        darken.setFill(Color.color(0, 0, 0, 0.75));

        agenda.getChildren().addAll(darken, page);

        checks = new ImageView[TOTAL_MINIGAMES];

        int cx = 330, cy = 200;
        for (int i = 0; i < TOTAL_MINIGAMES; ++i) {
            ImageView checkbox = new ImageView(Tools.getImage(Constants.CHECKBOX, 30, 30, true, true));
            ImageView checkmark = new ImageView(Tools.getImage(Constants.CHECKMARK, 30, 30, true, true));
            checks[i] = checkmark;
            checks[i].setVisible(false);
            checkmark.xProperty().bind(checkbox.xProperty());
            checkmark.yProperty().bind(checkbox.yProperty());
            checkbox.setX(cx+30);
            checkbox.setY(cy+5);

            Text taskText = new Text(cx + 30, cy, tasks[i]);
            taskText.setFont(Tools.getCustomFont(Constants.HANDWRITING_FONT, 20));
            agenda.getChildren().addAll(checkbox, checkmark, taskText);
            cy += 50;
        }

        agendaIcon = new ImageView(Tools.getImage(Constants.JOURNAL_ICON_2, 100, 100, true, true));
        agendaIcon.setX(820);
        agendaIcon.setY(100);
        agendaIcon.setOnMouseClicked(event -> {
            setActivePopup(agenda);
        });
        agendaIcon.setOnMouseEntered(event -> {
            setCursor(Cursor.HAND);
        });
        agendaIcon.setOnMouseExited(event -> setCursor(Cursor.DEFAULT));

        Text continueText = new Text(625, 650, "SPACE to close");
        continueText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 22));
        continueText.setFill(Color.WHITE);
        agenda.getChildren().add(continueText);

        currentRoom.getChildren().add(agendaIcon);
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {
        player = new Player(120, 550, 40, 60, 10, 20, 0, false);

        setupDialogue();
        setupRooms();
        setupMinigames();
        setupAgenda();

        interactionText = new Text(360, 700, "SPACE to interact");
        interactionText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 22));
        interactionText.setFill(Color.WHITE);
        interactionText.setTextAlignment(TextAlignment.CENTER);
        interactionText.setVisible(false);
        this.getChildren().add(interactionText);

        anxietyBar = new AnxietyBar();
        currentRoom.getChildren().add(anxietyBar);

        setActivePopup(levelThreeDialogue[0]);

        //level ending popup
        ending = new Popup(() -> ((LevelThreeScreen) this.getScene()).nextScene());
        Rectangle bg = new Rectangle(0, 0, 960, 720);
        bg.setFill(Color.BLACK);

        Text complete = new Text(250, 320, "Level 3 Completed!!!");
        complete.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        complete.setFill(Color.WHITE);

        Text time = new Text(250, 400, "Your time is: ");
        time.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        time.setFill(Color.WHITE);

        Text time2 = new Text(600, 400, "");
        time2.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        time2.setFill(Color.WHITE);
        time2.textProperty().bind(stopwatch.getDisplay().textProperty());

        Text ret = new Text(110, 460, "SPACE to return to level select.");
        ret.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        ret.setFill(Color.WHITE);

        ending.getChildren().addAll(bg, complete, time, time2, ret);

        // game over popup
        gameOver = new Popup(() -> ((LevelThreeScreen) this.getScene()).nextScene());
        Rectangle gameOverBg = new Rectangle(0, 0, 960, 720);
        bg.setFill(Color.BLACK);

        Text gameOverText = new Text(250, 320, "Game Over...");
        gameOverText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        gameOverText.setFill(Color.WHITE);

        Text returnText = new Text(110, 450, "SPACE to return to level select.");
        returnText.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        returnText.setFill(Color.WHITE);

        gameOver.getChildren().addAll(gameOverBg, gameOverText, returnText);
    }

    /**
     * Determine whether the player is in the bounds of an interaction entity.
     */
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

    /**
     * Checks for a room change. This is done every tick by {@link LevelThreeScreen}.
     */
    public void checkForRoomChange() {
        if (currentRoom != null) currentRoom.checkForRoomChange();
    }

    /**
     * Gets the active popup.
     * @return
     * The active popup.
     */
    public Popup getActivePopup() {
        return activePopup;
    }

    /**
     * Sets the active popup.
     * @param newPopup
     * The popup to set to.
     */
    public void setActivePopup(Popup newPopup) {
        if (activePopup != null) this.getChildren().remove(activePopup);
        activePopup = newPopup;
        if (activePopup != null) this.getChildren().add(activePopup);
    }

    /**
     * Gets the current room.
     * @return
     * The currentRoom.
     */
    public SchoolRoom getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Gets the anxiety bar.
     * @return
     * The anxiety bar component.
     */
    public AnxietyBar getAnxietyBar() {
        return anxietyBar;
    }

    /**
     * Sets the current room, and updates all components.
     *
     * @param newRoom
     * The room to set to.
     */
    public void setCurrentRoom(SchoolRoom newRoom, int gx, int gy) {
        this.getChildren().remove(currentRoom);
        currentRoom = newRoom;
        currentRoom.setSpawnX(gx);
        currentRoom.setSpawnY(gy);
        this.getChildren().add(newRoom);
        currentRoom.onRoomEntered();
        if (agendaIcon != null) currentRoom.getChildren().add(agendaIcon);
        if (anxietyBar != null) currentRoom.getChildren().add(anxietyBar);
        if (stopwatch != null) currentRoom.getChildren().add(stopwatch);
        for (RoomChangeEntity rce : currentRoom.getRoomChangers()) if (!currentRoom.getChildren().contains(rce.getIndicator()))
            currentRoom.getChildren().add(rce.getIndicator());
    }

    /**
     * Gets the active minigame.
     * @return
     * The active minigame.
     */
    public Minigame getActiveMinigame() {
        return activeMinigame;
    }

    /**
     * Sets the active minigame.
     * @param newMinigame
     * The minigame to set to.
     */
    public void setActiveMinigame(Minigame newMinigame) {
        if (activeMinigame != null) this.getChildren().remove(activeMinigame);
        activeMinigame = newMinigame;
        if (activeMinigame != null) {
            this.getChildren().add(activeMinigame);
            activeMinigame.onLaunch();
        }
    }

    /**
     * Gets the list of all the other entities (i.e., clipping).
     * @return
     * All the other entities.
     */
    public List<Entity> getOtherEntities() {
        return currentRoom.getOtherEntities();
    }

    /**
     * Gets the player.
     * @return
     * The player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns whether all the minigames have been played  or interacted.
     * @return
     * Whether all the minigames have been played or interacted.
     */
    public boolean tasksComplete() {
        for (int i = 0; i < TOTAL_MINIGAMES; ++i) {
            if (!minigameInteracted[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the anxiety is at maximum, and if so, the player has lost and the
     * game over screen is displayed.
     */
    public void checkGameOver() {
        if (anxietyBar.panic()) {
            setActivePopup(gameOver);
        }
    }

    /**
     * Gets the stopwatch.
     * @return
     * The stopwatch.
     */
    public Stopwatch getStopwatch() {
        return stopwatch;
    }
}
