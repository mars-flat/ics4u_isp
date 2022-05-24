package components;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.Entity;
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

    private DialogPane[] levelOneDialogue;

    private Entity player;

    // place all entities on even coordinates
    private List<Entity> otherEntities;

    /**
     * Creates an instance of this class.
     */
    public LevelOneComponents() {
        super();
        addComponents();
    }

    private void setupDialogue() {
        levelOneDialogue = new DialogPane[5];

        levelOneDialogue[0] = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960, 720, true, true)),
                "I wonder if my sibling has the book I need.",
                () -> this.setActiveDialogue(levelOneDialogue[1])
        );

        levelOneDialogue[1] = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960, 720, true, true)),
                "Hey, what's this?",
                () -> this.setActiveDialogue(levelOneDialogue[2])
        );

        levelOneDialogue[2] = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960, 720, true, true)),
                "Oh my. Are there any more of these?",
                () -> this.setActiveDialogue(levelOneDialogue[3])
        );

        levelOneDialogue[3] = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960, 720, true, true)),
                "I think I know what's going on here...",
                () -> this.setActiveDialogue(levelOneDialogue[4])
        );

        levelOneDialogue[4] = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960, 720, true, true)),
                "Social Anxiety.",
                () -> this.setActiveDialogue(null)
        );
    }

    private void setupEntities() {
        otherEntities = new ArrayList<>();

        player = new Entity(740, 600, 40, 40, Color.WHITE);
        this.getChildren().add(player);

        // add clipping
        Entity clipping1 = new Entity(0, 0, 960, 230, Color.TRANSPARENT);
        Entity clipping2 = new Entity(0, 230, 152, 490, Color.TRANSPARENT);
        Entity clipping3 = new Entity(150, 600, 572, 120, Color.TRANSPARENT);
        Entity clipping4 = new Entity(720, 650, 100, 70, Color.TRANSPARENT);
        Entity clipping5 = new Entity(820, 228, 142, 490, Color.TRANSPARENT);

        // furniture clipping
        Entity bedClipping = new Entity(180, 240, 180, 260, Color.TRANSPARENT);
        Entity nightstandClipping = new Entity(370, 240, 80, 80, Color.TRANSPARENT);
        Entity closetClipping = new Entity(490, 120, 320, 170, Color.TRANSPARENT);
        Entity deskClipping = new Entity(736, 330, 84, 200, Color.TRANSPARENT);
        Entity chairClipping = new Entity(670, 380, 36, 100, Color.TRANSPARENT);

        otherEntities.add(clipping1);
        otherEntities.add(clipping2);
        otherEntities.add(clipping3);
        otherEntities.add(clipping4);
        otherEntities.add(clipping5);
        otherEntities.add(bedClipping);
        otherEntities.add(nightstandClipping);
        otherEntities.add(closetClipping);
        otherEntities.add(deskClipping);
        otherEntities.add(chairClipping);

        this.getChildren().addAll(otherEntities);
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {

        setupDialogue();

        PauseTransition pt = new PauseTransition(Duration.millis(500));
        pt.setOnFinished(event -> setActiveDialogue(levelOneDialogue[0]));
        pt.play();

        setupEntities();

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

    public Entity getPlayer() {
        return player;
    }

    public List<Entity> getOtherEntities() {
        return otherEntities;
    }
}
