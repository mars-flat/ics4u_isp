package components;

import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import utilities.Constants;
import utilities.Entity;
import utilities.Player;
import utilities.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Components for the first level of the game.
 *
 * @since 1.3, 5/19/2022
 * Modification on 5/26/2022
 *
 * @author Shane Chen
 */
public class LevelOneComponents extends ScreenComponent {

    /**
     * found[i] denotes whether the i-th journal has been found.
     */
    private final boolean[] found;

    /**
     * This is true if all three journals have been found.
     */
    boolean questComplete;

    /**
     * The current active dialogue. May be {@code null} to indicate that there is no dialogue.
     */
    private Popup activePopup;

    /**
     * The journal popups which may be displayed.
     */
    private Popup[] journalPopups;

    /**
     * The dialogue popups which may be displayed.
     */
    private DialoguePopup[] levelOneDialogue;

    /**
     * The journals displayed around the room for the user to find.
     */
    private Entity[] journals;

    /**
     * The player.
     */
    private Player player;

    /**
     * All the other entities within the room, such as clipping.
     * place all entities on even coordinates for proper collision detection.
     */
    private List<Entity> otherEntities;

    /**
     * The number of journals currently found.
     */
    private int journalsFound;

    /**
     * The journal icons, denoting whether a journal has been found or not. Clickable if found to open.
     */
    private ImageView[] journalIcons;

    /**
     * A guide to help the player begin the journal hunt.
     */
    private Circle journalHighlight;

    /**
     * Creates an instance of this class.
     */
    public LevelOneComponents() {
        super();
        journalsFound = 0;
        found = new boolean[3];
        questComplete = false;
        addComponents();
    }

    /**
     * Set up the dialogue and journal popups.
     */
    private void setupPopups() {

        // the dialogue to be displayed in {@link DialoguePopup} popups.
        String[] dialogue = {
                "I wonder if my sibling has the book I need.",
                "Hey, what's this?",
                "Oh my. Are there any more of these?",
                "I think I know what's going on here...",
                "Social Anxiety.",
        };

        // instantiate the dialogue popups
        levelOneDialogue = new DialoguePopup[5];
        for (int i = 0; i < 5; ++i) {
            levelOneDialogue[i] = new DialoguePopup(
                    new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 240, 280, true, true)),
                    "Older Sibling", dialogue[i], () -> this.setActivePopup(null)
            );
        }

        // set the default change request actions for certain dialogues
        levelOneDialogue[0].setOnChangeRequest(() -> {
            this.setActivePopup(null);
            StrokeTransition st1 = new StrokeTransition(Duration.millis(1000), journalHighlight, Color.YELLOW, Color.TRANSPARENT);
            StrokeTransition st2 = new StrokeTransition(Duration.millis(1000), journalHighlight, Color.TRANSPARENT, Color.YELLOW);
            SequentialTransition sq = new SequentialTransition(journalHighlight, st1, st2);
            sq.setCycleCount(Timeline.INDEFINITE);
            sq.play();
        });
        levelOneDialogue[3].setOnChangeRequest(() -> this.setActivePopup(levelOneDialogue[4]));

        String[] journalEntries = {
                "Why must talking be so hard. Why must socializing be so hard. Why must life be so hard. So, long story short, today some people in my class decided to go to a mall afterschool. They asked me if I wanted to go, but of course, me being my shut-in self, awkwardly rejected their invitation so now I'm sad and alone in my room writing in my sad journal. They definitely think I'm super weird and don't want to be friends with me now. Maybe not going was the best choice after all. Fewer chances to embarrass myself.",
                "Despite my best attempt to avoid any form of eye contact and putting on the \"I hate it here\" face, the teacher called on me in class today... that was so scary, I literally almost passed out. I didn't really feel like talking, but again when did I ever feel like talking. Anyway, she asked me about, wait. I don't even remember the question anymore. I definitely messed up the answer though.",
                "It's the first day of school today, also known as the worst day of the year. My morning started off great: couldn't find matching socks, was late to school, aaaand had to walk into the classroom with everyone staring. Spent majority of the rest of class hanging out in the bathroom, because at least toilets won't judge you for your mix-matching socks. (praying that the teacher don't think I have constipation or something)"
        };

        journalPopups = new Popup[3];
        for (int i = 0; i < 3; ++i) {
            journalPopups[i] = new Popup(() -> this.setActivePopup(null));
            ImageView journalPage = new ImageView(Tools.getImage(Constants.JOURNAL_BOX, 960, 720, true, true));
            Text journalText = new Text(355, 180, journalEntries[i]);
            journalText.setFont(Tools.getCustomFont(Constants.FONT_FILE, 16));
            journalText.setWrappingWidth(280);
            Rectangle darken = new Rectangle(0, 0, 960, 720);
            darken.setFill(Color.color(0, 0, 0, 0.75));
            Text continueText = new Text(625, 650, "SPACE to continue");
            continueText.setFont(Tools.getCustomFont(Constants.FONT_FILE, 22));
            continueText.setFill(Color.WHITE);
            this.getChildren().add(continueText);
            journalPopups[i].getChildren().addAll(darken, journalPage, journalText, continueText);
        }
    }

    /**
     * Setup the entities that interact with the player (i.e., clipping).
     */
    private void setupEntities() {
        otherEntities = new ArrayList<>();

        List<Entity> borderClipping = new ArrayList<>();
        borderClipping.add(new Entity(0, 0, 960, 280, Color.TRANSPARENT));
        borderClipping.add(new Entity(0, 0, 144, 720, Color.TRANSPARENT));
        borderClipping.add(new Entity(808, 0, 152, 720, Color.TRANSPARENT));
        borderClipping.add(new Entity(0, 624, 708, 96, Color.TRANSPARENT));
        borderClipping.add(new Entity(708, 680, 100, 80, Color.TRANSPARENT));

        List<Entity> furnitureClipping = new ArrayList<>();
        furnitureClipping.add(new Entity(360, 250, 80, 80, Color.TRANSPARENT));
        furnitureClipping.add(new Entity(724, 340, 86, 198, Color.TRANSPARENT));
        furnitureClipping.add(new Entity(170, 240, 180, 250, Color.TRANSPARENT));
        furnitureClipping.add(new Entity(660, 430, 40, 64, Color.TRANSPARENT));
        furnitureClipping.add(new Entity(660, 394, 4, 30, Color.TRANSPARENT));

        otherEntities.addAll(borderClipping);
        otherEntities.addAll(furnitureClipping);

        this.getChildren().addAll(otherEntities);
    }

    /**
     * Play the journal found animation. Slides a journal page image to it's corresponding icon location.
     * @param which
     * Which journal.
     *
     * @param fx
     * The ending x location of the journal page.
     *
     * @param fy
     * The ending y location of the journal page.
     */
    private void playJournalFindAnimation(int which, int fx, int fy) {
        Line animationPath = new Line(430, 310, fx, fy);
        ImageView animJournal = new ImageView(Tools.getImage(Constants.JOURNAL_ICON_2, 100, 100, true, true));
        animJournal.setPickOnBounds(false);
        animJournal.setMouseTransparent(true);
        this.getChildren().add(animJournal);
        PathTransition animation = new PathTransition(Duration.millis(1000), animationPath, animJournal);
        animation.setOnFinished(event -> {
            journalIcons[which].setImage(Tools.getImage(Constants.JOURNAL_ICON_2, 100, 100, true, true));
            this.getChildren().remove(animJournal);
        });
        animation.play();
    }

    /**
     * What happens when a journal has been clicked. This may open the journal in a popup.
     *
     * @param which
     * Which journal.
     *
     * @param overrideVicinity
     * Whether to override the vicinity requirement.
     */
    private void handleJournalClick(int which, boolean overrideVicinity) {
        if (overrideVicinity || player.inVicinity(journals[which], 70)) {
            for (ImageView img : journalIcons) img.setVisible(true);
            journalHighlight.setVisible(false);
            if (!found[which]) {
                journalsFound++;
                playJournalFindAnimation(which, 60, 100 + 75 * which);
            }
            if (journalsFound == 1 && !found[which]) {

                /*
                if this is the first journal found,
                display message 1
                then display the journal
                then display message 2
                 */
                levelOneDialogue[1].setOnChangeRequest(() -> this.setActivePopup(journalPopups[which]));
                journalPopups[which].setOnChangeRequest(() -> this.setActivePopup(levelOneDialogue[2]));
                this.setActivePopup(levelOneDialogue[1]);
            } else if (journalsFound == 3) {

                /*
                if this is the third journal found,
                display message 3
                 */
                if (!questComplete)
                    journalPopups[which].setOnChangeRequest(() -> this.setActivePopup(levelOneDialogue[3]));
                else journalPopups[which].setOnChangeRequest(() -> this.setActivePopup(null));
                this.setActivePopup(journalPopups[which]);
                questComplete = true;
            } else {

                /*
                if this is not the first or third journal found,
                just display the journal
                 */
                journalPopups[which].setOnChangeRequest(() -> this.setActivePopup(null));
                this.setActivePopup(journalPopups[which]);
            }
            found[which] = true;
        }
    }

    /**
     * Set up the actual journals in the room, as well as the icons on the side.
     */
    private void setupJournals() {
        journals = new Entity[3];
        journals[0] = new Entity(300, 300, 25, 25, Color.TRANSPARENT, true);
        journals[0].setOnMouseClicked(event -> handleJournalClick(0, false));

        journals[1] = new Entity(365, 515, 25, 25, Color.TRANSPARENT, true);
        journals[1].setOnMouseClicked(event -> handleJournalClick(1, false));

        journals[2] = new Entity(720, 175, 25, 25, Color.TRANSPARENT, true);
        journals[2].setOnMouseClicked(event -> handleJournalClick(2, false));

        for (int i = 0; i < 3; ++i) {
            int finalI = i;
            journals[i].setOnMouseEntered(event -> {
                if (player.inVicinity(journals[finalI], 70)) setCursor(Cursor.HAND);
            });
            journals[i].setOnMouseExited(event -> setCursor(Cursor.DEFAULT));
        }

        this.getChildren().addAll(journals);

        journalIcons = new ImageView[3];
        for (int i = 0; i < 3; ++i) {
            journalIcons[i] = new ImageView(Tools.getImage(Constants.JOURNAL_ICON_1,
                    100, 100, true, true));
            journalIcons[i].setX(5);
            journalIcons[i].setY(50 + 75 * i);
            journalIcons[i].setVisible(false);
            int finalI = i;
            journalIcons[i].setOnMouseClicked(event -> {
                if (found[finalI]) handleJournalClick(finalI, true);
            });
        }
        this.getChildren().addAll(journalIcons);

        journalHighlight = new Circle(737, 192, 18, Color.TRANSPARENT);
        journalHighlight.setStrokeWidth(2.0);
        journalHighlight.setPickOnBounds(false);
        journalHighlight.setMouseTransparent(true);
        this.getChildren().add(journalHighlight);
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {

        setupPopups();

        PauseTransition pt = new PauseTransition(Duration.millis(500));
        pt.setOnFinished(event -> setActivePopup(levelOneDialogue[0]));
        pt.play();

        setupEntities();
        setupJournals();

        player = new Player(740, 600, 40, 40);
        this.getChildren().add(player);
        this.getChildren().add(player.getCharacter());

    }

    /**
     * Get the current active dialogue.
     *
     * @return The current active dialogue, or {@code null} if there is no dialogue.
     */
    public Popup getActivePopup() {
        return activePopup;
    }

    public void setActivePopup(Popup newPopup) {
        if (activePopup != null) this.getChildren().remove(activePopup);
        activePopup = newPopup;
        if (activePopup != null) this.getChildren().add(activePopup);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getOtherEntities() {
        return otherEntities;
    }
}
