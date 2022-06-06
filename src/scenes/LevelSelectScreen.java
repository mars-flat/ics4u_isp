package scenes;

import components.LevelSelectComponents;
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
 * The level select screen for the  of the game.
 *
 * Required functionality includes mouse event handling.
 *
 * @since 3.4, 6/3/2022
 * @author Annie Wong
 */
public class LevelSelectScreen extends GameScreen {

    /**
     * Instance of associated component class.
     * @see LevelSelectComponents
     */
    private LevelSelectComponents components;

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
    public LevelSelectScreen(double width, double height, GameHandler controller) {
        super(new LevelSelectComponents(), width, height, controller);
        components = (LevelSelectComponents) super.getRoot();
        this.onLoad();
    }

    /**
     * What happens when the scene is loaded (i.e. an instance is created).
     */
    private void onLoad() {
        components.changeBackground(Constants.LEVEL_SELECT_SCREEN_1);
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
    @Override
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
    @Override
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
     * Switches the scene to level one.
     */
    private void level1() {
        LevelOneScreen nxt = new LevelOneScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                controller);
        controller.changeScene(this, nxt);
    }

    /**
     * Switches the scene to level two.
     */
    private void level2() {
        LevelTwoScreen nxt = new LevelTwoScreen(
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
    @Override
    public void nextScene() {
        nextScene(1);
    }

    /**
     * Handle the switching of scenes. This should utilize {@link GameHandler#changeScene(Scene, Scene)}.
     *
     * The parameter handles multiple selections from the level select.
     *
     * @param choice
     * The next event to occur. This will be handled in a switch inside the method.
     */
    public void nextScene(int choice) {

        components.getTransitionRectangle().setPickOnBounds(true);
        components.getTransitionRectangle().setMouseTransparent(false);

        transitionOut();

        switch (choice) {
            case 0: //return to main menu
                MenuScreen nxt = new MenuScreen(
                        Constants.SCREEN_WIDTH,
                        Constants.SCREEN_HEIGHT,
                        controller);
                controller.changeScene(this, nxt);
                break;
            case 1: //level 1
                level1();
                break;
            case 2: //level 2
                level2();
                break;
            case 3: //level 3
                break;
        }
    }
}
