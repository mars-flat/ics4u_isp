package scenes;

import components.LevelOneComponents;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import utilities.Constants;
import utilities.GameHandler;

import java.util.HashSet;

/**
 * The screen for the first level of the game.
 *
 * Required functionality includes keyboard and mouse
 * event listening, and updating components on each tick.
 *
 * @since 1.3, 5/19/2022
 * @author Shane Chen
 */
public class LevelOneScreen extends GameScreen {

    /**
     * Instance of associated component class.
     * @see LevelOneComponents
     */
    private LevelOneComponents components;

    /**
     * Set of current keyboard inputs.
     */
    private HashSet<String> keyboardInputs;

    /**
     * Creates an instance of this class.
     *
     * @param width
     * Width of the window. Should conform to the constants in {@link utilities.Constants}
     *
     * @param height
     * Height of the window. Should conform to the constants in {@link utilities.Constants}
     *
     * @param controller
     * {@link GameHandler} instance.
     *
     */
    public LevelOneScreen(double width, double height, GameHandler controller) {
        super(new LevelOneComponents(), width, height, controller);
        components = (LevelOneComponents) super.getRoot();

        this.onLoad();
    }

    /**
     * What happens when the scene is loaded (i.e. an instance is created).
     */
    private void onLoad() {
        this.setFill(Color.BLACK);
        components.changeBackground(Constants.BEDROOM_SCREEN);

        keyboardInputs = new HashSet<>();

        this.setOnKeyPressed(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.add(keyName);
        });

        this.setOnKeyReleased(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.remove(keyName);
        });
    }

    private void handleKeyboardInputs() {
        // handle movement
        if (keyboardInputs.contains("W") ||
            keyboardInputs.contains("A") ||
            keyboardInputs.contains("S") ||
            keyboardInputs.contains("D")) {
            if (components.getActiveDialogue() == null) {

            }
        }

        if (keyboardInputs.contains("SPACE")) {
            if (components.getActiveDialogue() != null) {
                components.getActiveDialogue().onChangeRequest();
                keyboardInputs.remove("SPACE");
            }
        }
    }

    /**
     * What happens on each tick. AnimationTimer in {@link GameHandler} will
     * repeatedly call this method of the currently displayed scene.
     *
     * @param currentTick
     * The current tick count.
     */
    @Override
    public void onTick(long currentTick) {
        handleKeyboardInputs();
        if (currentTick % 3 == 0) {
            if (components.getActiveDialogue() != null)
                components.getActiveDialogue().showNextChar();
        }

    }

    /**
     * What happens on a transition into this scene.
     */
    @Override
    public void transitionIn() {

    }

    /**
     * What happens on a transition out of this scene.
     */
    @Override
    public void transitionOut() {

    }

    /**
     * Handle the switching of scenes. This should utilize {@link GameHandler#changeScene(Scene, Scene)}.
     */
    @Override
    public void nextScene() {

    }
}
