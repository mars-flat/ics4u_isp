package scenes;

import components.MenuScreenComponents;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

/**
 * The screen for the main menu of the game.
 *
 * Required functionality includes mouse event handling.
 *
 * @since 1.2, 5/19/2022
 * @author Shane Chen
 */
public class MenuScreen extends GameScreen {

    /**
     * Instance of associated component class.
     * @see MenuScreenComponents
     */
    private MenuScreenComponents components;

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
    public MenuScreen(double width, double height, GameHandler controller) {
        super(new MenuScreenComponents(), width, height, controller);
        components = (MenuScreenComponents) super.getRoot();
        this.onLoad();
    }

    /**
     * What happens when the scene is loaded (i.e. an instance is created).
     */
    private void onLoad() {
        components.changeBackground(Constants.MAIN_MENU_SCREEN_1);
        transitionIn();
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

    }

    /**
     * What happens on a transition into this scene.
     *
     * In this case, a {@link FillTransition} plays for 1 second.
     */
    public void transitionIn() {
        FillTransition ft = new FillTransition(
                Duration.millis(1000),
                components.getTransitionRectangle(),
                Color.BLACK, Color.TRANSPARENT);

        ft.play();
    }

    /**
     * What happens on a transition into this scene.
     *
     * In this case, a loading screen pairs with a transition to load
     * the next scene.
     */
    public void transitionOut() {
        components.getLoadingText().setVisible(true);

        FillTransition ft = new FillTransition(
                Duration.millis(400),
                components.getTransitionRectangle(),
                Color.TRANSPARENT, Color.BLACK);

        PauseTransition pt = new PauseTransition(
                Duration.millis(2000)
        );
    }

    /**
     * Switches the scene to level select.
     */
    private void play() {
        LevelSelectScreen nxt = new LevelSelectScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                controller);
        controller.changeScene(this, nxt);
    }

    private void about() {
        AboutScreen nxt = new AboutScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                controller);
        controller.changeScene(this, nxt);
    }

    /**
     * Handle the switching of scenes. This should utilize {@link GameHandler#changeScene(Scene, Scene)}.
     *
     * By defaults, calls overridden method with {@code choice(1)}.
     */
    public void nextScene() {
        nextScene(1);
    }

    /**
     * Handle the switching of scenes. This should utilize {@link GameHandler#changeScene(Scene, Scene)}.
     *
     * The parameter handles multiple selections from the main menu.
     *
     * @param choice
     * The next event to occur. This will be handled in a switch inside the method.
     */
    public void nextScene(int choice) {

        components.getTransitionRectangle().setPickOnBounds(true);
        components.getTransitionRectangle().setMouseTransparent(false);

        transitionOut();

        switch (choice) {
            case 1:
                play();
                break;
            case 2:
                about();
                break;
            case 3:
                controller.closeProgram();
                break;
        }
    }
}
