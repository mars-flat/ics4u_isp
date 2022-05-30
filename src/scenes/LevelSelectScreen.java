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

//public class LevelSelectScreen extends GameScreen {
//
//    private LevelSelectComponents components;
//
//    public LevelSelectScreen(double width, double height, GameHandler controller) {
//        super(new MenuScreenComponents(), width, height, controller);
//        components = (LevelSelectComponents) super.getRoot();
//        this.onLoad();
//    }
//
//    /**
//     * What happens when the scene is loaded (i.e. an instance is created).
//     */
//    private void onLoad() {
//        //TODO: change components.changeBackground(Constants.MAIN_MENU_SCREEN_1);
//        transitionIn();
//    }
//
//    /**
//     * What happens on each tick. AnimationTimer in {@link GameHandler} will
//     * repeatedly call this method of the currently displayed scene.
//     *
//     * @param currentTick
//     * The current tick count.
//     */
//    @Override
//    public void onTick(long currentTick) {
//
//    }
//
//    /**
//     * What happens on a transition into this scene.
//     *
//     * In this case, a {@link FillTransition} plays for 1 second.
//     */
//    @Override
//    public void transitionIn() {
//        FillTransition ft = new FillTransition(
//                Duration.millis(1000),
//                components.getTransitionRectangle(),
//                Color.BLACK, Color.TRANSPARENT);
//
//        ft.play();
//    }
//
//    /**
//     * What happens on a transition into this scene.
//     *
//     * In this case, a loading screen pairs with a transition to load
//     * the next scene..
//     */
//    @Override
//    public void transitionOut() {
//        components.getLoadingText().setVisible(true);
//
//        FillTransition ft = new FillTransition(
//                Duration.millis(400),
//                components.getTransitionRectangle(),
//                Color.TRANSPARENT, Color.BLACK);
//
//        PauseTransition pt = new PauseTransition(
//                Duration.millis(2000)
//        );
//
//        SequentialTransition loadAnimation = new SequentialTransition(ft, pt);
//        loadAnimation.setOnFinished(event -> play());
//        loadAnimation.play();
//    }
//
//    private void play() {
//        LevelOneScreen nxt = new LevelOneScreen(
//                Constants.SCREEN_WIDTH,
//                Constants.SCREEN_HEIGHT,
//                controller);
//        controller.changeScene(this, nxt);
//    }
//
//    @Override
//    public void nextScene() {
//        nextScene(1);
//    }
//
//    public void nextScene(int choice) {
//
//        components.getTransitionRectangle().setPickOnBounds(true);
//        components.getTransitionRectangle().setMouseTransparent(false);
//
//        switch (choice) {
//            case 1:
//
//                break;
//            case 2:
//
//                break;
//            case 3:
//
//                break;
//        }
//    }
//}
