package scenes;

import components.LevelOneComponents;
import components.SplashScreenComponents;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

/**
 * This is the splash screen scene. It displays both the logo
 * and the title/loading page.
 *
 * @since 1.1, 5/17/2022
 * @author Annie Wong
 */
public class SplashScreen extends GameScreen {

    /**
     * Whether the game title has been displayed.
     * If not, then the splash screen is being displayed.
     */
    private boolean displayedGameTitle;

    /**
     * Instance of associated component class.
     * @see SplashScreenComponents
     */
    private SplashScreenComponents components;

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
    public SplashScreen(double width, double height, GameHandler controller) {
        super(new SplashScreenComponents(), width, height, controller);
        components = (SplashScreenComponents) super.getRoot();
        this.onLoad();
    }

    /**
     * What happens when the scene is loaded (i.e. an instance is created).
     */
    private void onLoad() {
        displayedGameTitle = false;
        displayLogo();
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
     * Changes the background to the logo image.
     */
    private void displayLogo() {
        components.changeBackground(Constants.SPLASH_SCREEN);
        transitionIn();
    }

    /**
     * Changes the background to the game title/loading screen.
     */
    private void displayGameTitle() {
        displayedGameTitle = true;
        components.changeBackground(Constants.TITLE_SCREEN);
        transitionIn();
    }

    /**
     * What happens on a transition into this scene.
     */
    @Override
    public void transitionIn() {
        FillTransition ft = new FillTransition(
                Duration.millis(1000),
                components.getTransitionRectangle(),
                Color.BLACK, Color.TRANSPARENT);

        ft.setOnFinished(event -> pause());
        ft.play();
    }

    /**
     * What happens on a transition out of this scene.
     */
    @Override
    public void transitionOut() {
        FillTransition ft = new FillTransition(
                Duration.millis(1000),
                components.getTransitionRectangle(),
                Color.TRANSPARENT, Color.BLACK);

        ft.setOnFinished(event -> {
            if (displayedGameTitle) nextScene();
            else displayGameTitle();
        });
        ft.play();
    }

    /**
     * Pause animations to show screens for a duration.
     */
    public void pause() {
        PauseTransition pt = new PauseTransition(Duration.millis(
                displayedGameTitle ? 2000 : 1000
        ));
        pt.setOnFinished(event -> transitionOut());
        pt.play();
    }

    /**
     * Handle the switching of scenes. This should utilize {@link GameHandler#changeScene(Scene, Scene)}.
     */
    public void nextScene() {
        MenuScreen nxt = new MenuScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, controller);
        controller.changeScene(this, nxt);
    }
}
