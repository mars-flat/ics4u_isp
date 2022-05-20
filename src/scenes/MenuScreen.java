package scenes;

import components.MenuScreenComponents;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

import java.io.File;
import java.util.HashSet;

public class MenuScreen extends GameScreen {

    private MenuScreenComponents components;
    private Point2D lastClick;

    public MenuScreen(double width, double height, GameHandler controller) {
        super(new MenuScreenComponents(), width, height, controller);
        components = (MenuScreenComponents) super.getRoot();

        this.addEventHandlers();
        this.onLoad();
    }

    private void addEventHandlers() {
        this.setOnMouseClicked(event -> {
            lastClick = new Point2D(event.getX(), event.getY());
            //System.out.println(lastClick.getX() + " " + lastClick.getY());
        });
    }

    private void onLoad() {
        components.changeBackground(Constants.MAIN_MENU_SCREEN_1);
        transitionIn();
    }


    @Override
    public void onTick(long currentTick) {

    }

    @Override
    public void transitionIn() {
        FillTransition ft = new FillTransition(
                Duration.millis(1000),
                components.getTransitionRectangle(),
                Color.BLACK, Color.TRANSPARENT);

        ft.play();
    }

    @Override
    public void transitionOut() {
        components.getLoadingText().setVisible(true);

        FillTransition ft = new FillTransition(
                Duration.millis(400),
                components.getTransitionRectangle(),
                Color.TRANSPARENT, Color.BLACK);

        PauseTransition pt = new PauseTransition(
                Duration.millis(2000)
        );

        SequentialTransition loadAnimation = new SequentialTransition(ft, pt);
        loadAnimation.setOnFinished(event -> play());
        loadAnimation.play();
    }

    @Override
    public void nextScene() {
        nextScene(1);
    }

    private void play() {
        LevelOneScreen nxt = new LevelOneScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                controller);
        controller.changeScene(this, nxt);
    }

    public void nextScene(int choice) {

        components.getTransitionRectangle().setPickOnBounds(true);
        components.getTransitionRectangle().setMouseTransparent(false);

        switch (choice) {
            case 1:
                transitionOut();
                break;
            case 2:
                // about
                break;
            case 3:
                controller.closeProgram();
                break;
        }
    }
}
