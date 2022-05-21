package scenes;

import components.LevelOneComponents;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
        this.setOnKeyPressed(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.add(keyName);
        });
        this.setOnMousePressed(event -> {

        });
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
        if (currentTick % 5 == 0) components.getActiveDialogue().showNextChar();

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
