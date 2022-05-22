package components;

import javafx.scene.image.ImageView;
import utilities.Constants;
import utilities.Tools;

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
                () -> this.setActiveDialogue(null)
        );
    }
    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {
        setupDialogue();


        activeDialogue = levelOneDialogue[0];

        this.getChildren().add(activeDialogue);
    }

    public void setActiveDialogue(DialogPane newDialogue) {
        System.out.println(newDialogue);
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
}
