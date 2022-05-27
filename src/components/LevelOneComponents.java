package components;

import javafx.animation.PauseTransition;
import javafx.scene.paint.Color;
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
 * @author Shane Chen
 */
public class LevelOneComponents extends ScreenComponent {

    /**
     * The current active dialogue. May be {@code null} to indicate that there is no dialogue.
     */
    private DialogPane activeDialogue;

    private DialogPane[] journalDialogues;

    private DialogPane[] levelOneDialogue;

    private Entity[] journals;

    private Player player;

    // place all entities on even coordinates
    private List<Entity> otherEntities;

    private Text journalsFoundText;

    private int journalsFound;
    boolean questComplete;
    private boolean[] found;

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

    private void setupDialogue() {
        levelOneDialogue = new DialogPane[5];

        levelOneDialogue[0] = new DialogPane(
                "I wonder if my sibling has the book I need.",
                () -> this.setActiveDialogue(null)
        );

        levelOneDialogue[1] = new DialogPane(
                "Hey, what's this?",
                () -> this.setActiveDialogue(null)
        );

        levelOneDialogue[2] = new DialogPane(
                "Oh my. Are there any more of these?",
                () -> this.setActiveDialogue(null)
        );

        levelOneDialogue[3] = new DialogPane(
                "I think I know what's going on here...",
                () -> this.setActiveDialogue(levelOneDialogue[4])
        );

        levelOneDialogue[4] = new DialogPane(
                "Social Anxiety.",
                () -> this.setActiveDialogue(null)
        );

        journalDialogues = new DialogPane[3];
        journalDialogues[0] = new DialogPane(

                "MONDAY SEPTEMBER XX, XXXX",
                () -> this.setActiveDialogue(null)
        );
        journalDialogues[1] = new DialogPane(
                "TUESDAY SEPTEMBER XX, XXXX",
                () -> this.setActiveDialogue(null)
        );
        journalDialogues[2] = new DialogPane(
                "WEDNESDAY SEPTEMBER XX, XXXX",
                () -> this.setActiveDialogue(null)
        );

    }

    private void setupEntities() {
        otherEntities = new ArrayList<>();

        List<Entity> borderClipping = new ArrayList<>();
        borderClipping.add(new Entity(0,0,960,280,Color.TRANSPARENT));
        borderClipping.add(new Entity(0,0,144,720,Color.TRANSPARENT));
        borderClipping.add(new Entity(808,0,152,720,Color.TRANSPARENT));
        borderClipping.add(new Entity(0,624,708,96,Color.TRANSPARENT));
        borderClipping.add(new Entity(708,680,100,80,Color.TRANSPARENT));

        List<Entity> furnitureClipping = new ArrayList<>();
        furnitureClipping.add(new Entity(360,250,80,80,Color.TRANSPARENT));
        furnitureClipping.add(new Entity(724,340,86,198,Color.TRANSPARENT));
        furnitureClipping.add(new Entity(170,240,180,250,Color.TRANSPARENT));
        furnitureClipping.add(new Entity(660,430,40,64,Color.TRANSPARENT));
        furnitureClipping.add(new Entity(660,394,4,30,Color.TRANSPARENT));

        otherEntities.addAll(borderClipping);
        otherEntities.addAll(furnitureClipping);

        this.getChildren().addAll(otherEntities);
    }

    private void handleJournalClick(int which) {
        if (player.inVicinity(journals[which], 70)) {
            if (!found[which]) {
                journalsFound++;
                journalsFoundText.setText(journalsFound + "/3 journal entries found");
                if (journalsFound == 3) journalsFoundText.setFill(Color.GREEN);
            }
            if (journalsFound == 1 && !found[which]) {

                /*
                if this is the first journal found,
                display message 1
                then display the journal
                then display message 2
                 */
                levelOneDialogue[1].setOnChangeRequest(() -> this.setActiveDialogue(journalDialogues[which]));
                journalDialogues[which].setOnChangeRequest(() -> this.setActiveDialogue(levelOneDialogue[2]));
                this.setActiveDialogue(levelOneDialogue[1]);
            }
            else if (journalsFound == 3) {

                /*
                if this is the third journal found,
                display message 3
                 */
                if (!questComplete) journalDialogues[which].setOnChangeRequest(() -> this.setActiveDialogue(levelOneDialogue[3]));
                else journalDialogues[which].setOnChangeRequest(() -> this.setActiveDialogue(null));
                this.setActiveDialogue(journalDialogues[which]);
                questComplete = true;
            }
            else {

                /*
                if this is not the first or third journal found,
                just display the journal
                 */
                journalDialogues[which].setOnChangeRequest(() -> this.setActiveDialogue(null));
                this.setActiveDialogue(journalDialogues[which]);
            }
            found[which] = true;
        }
    }

    private void setupJournals() {
        journals = new Entity[3];
        journals[0] = new Entity(300, 275, 10, 50, Color.RED, true);
        journals[0].setOnMouseClicked(event -> handleJournalClick(0));


        journals[1] = new Entity(400, 475, 10, 50, Color.ORANGE, true);
        journals[1].setOnMouseClicked(event -> handleJournalClick(1));

        journals[2] = new Entity(700, 175, 10, 50, Color.YELLOW, true);
        journals[2].setOnMouseClicked(event -> handleJournalClick(2));

        this.getChildren().addAll(journals);
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {
        journalsFoundText = new Text(50, 50, "");
        journalsFoundText.setFont(Tools.getCustomFont(Constants.FONT_FILE, 48));
        journalsFoundText.setFill(Color.WHITE);
        this.getChildren().add(journalsFoundText);

        setupDialogue();

        PauseTransition pt = new PauseTransition(Duration.millis(500));
        pt.setOnFinished(event -> setActiveDialogue(levelOneDialogue[0]));
        pt.play();

        setupEntities();
        setupJournals();

        player = new Player(740, 600, 40, 40);
        this.getChildren().add(player);
        this.getChildren().add(player.getCharacter());

    }

    public void setActiveDialogue(DialogPane newDialogue) {
        if (activeDialogue != null) this.getChildren().remove(activeDialogue);
        activeDialogue = newDialogue;
        if (activeDialogue != null) this.getChildren().add(activeDialogue);
    }

    /**
     * Get the current active dialogue.
     * @return
     * The current active dialogue, or {@code null} if there is no dialogue.
     */
    public DialogPane getActiveDialogue() {
        return activeDialogue;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getOtherEntities() {
        return otherEntities;
    }
}
