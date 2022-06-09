package scenes;

import components.AboutScreenComponents;
import components.MenuScreenComponents;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

/**
 * The screen for the about screen of the game.
 *
 * Required functionality includes mouse event handling.
 *
 * @since 3.4, 6/3/2022
 * @author Annie Wong
 */
public class AboutScreen extends GameScreen {

    /**
     * Instance of associated component class.
     * @see AboutScreenComponents
     */
    private AboutScreenComponents components;

    /**
     * Creates an instance of this class.
     * @param width      Width of the window. Should conform to the constants in {@link Constants}
     * @param height     Height of the window. Should conform to the constants in {@link Constants}
     * @param controller {@link GameHandler} instance.
     */
    public AboutScreen(double width, double height, GameHandler controller) {
        super(new AboutScreenComponents(), width, height, controller);
        components = (AboutScreenComponents) super.getRoot();
        this.onLoad();
    }

    /**
     * What happens when the scene is loaded (i.e. an instance is created).
     */
    private void onLoad() {
        components.changeBackground(Constants.ABOUT_SCREEN);
        transitionIn();
        this.setOnMouseClicked(event -> {
            System.out.println(event.getX() + " " + event.getY());
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
     * the next scene..
     */
    public void transitionOut() {
        FillTransition ft = new FillTransition(
                Duration.millis(400),
                components.getTransitionRectangle(),
                Color.TRANSPARENT, Color.BLACK);

        PauseTransition pt = new PauseTransition(
                Duration.millis(2000)
        );
    }

    /**
     * Handle the switching of scenes. This should utilize {@link GameHandler#changeScene(Scene, Scene)}.
     * In this case, the next scene is returning to the main menu.
     *
     * By defaults, calls overridden method with {@code choice(1)}.
     */
    public void nextScene() {
        transitionOut();
        MenuScreen nxt = new MenuScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                controller);
        controller.changeScene(this, nxt);
    }
}
