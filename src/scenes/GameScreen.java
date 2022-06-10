package scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import utilities.GameHandler;

/**
 * This abstract class serves as a superclass for all scenes within
 * the program. These scenes require implementation of essential behavior,
 * such as ticking, transitions, and changing scenes.
 *
 * @since 1.0, 5/16/2022
 * @author Shane Chen
 */
public abstract class GameScreen extends Scene {

    /**
     * {@link GameHandler} instance to control scene changing.
     */
    public GameHandler controller;

    /**
     * Creates an instance of this class.
     *
     * @param root
     * JavaFX node root. Known as components in this project.
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
    public GameScreen(Parent root, double width, double height, GameHandler controller) {
        super(root, width, height);
        this.controller = controller;
    }

    /**
     * What happens on each tick. AnimationTimer in {@link GameHandler} will
     * repeatedly call this method of the currently displayed scene.
     *
     * @param currentTick
     * The current tick count.
     */
    public abstract void onTick(long currentTick);

    /**
     * Moves on to the next scene/screen.
     */
    public abstract void nextScene();
}
