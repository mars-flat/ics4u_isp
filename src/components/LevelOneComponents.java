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

    /**
     * Creates an instance of this class.
     */
    public LevelOneComponents() {
        super();
        addComponents();
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {

        activeDialogue = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960,720,true,true)),
                "I wonder if my sibling has the book I need."
        );

        this.getChildren().add(activeDialogue);
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
