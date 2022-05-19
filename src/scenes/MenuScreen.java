package scenes;

import components.MenuScreenComponents;
import javafx.animation.FillTransition;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

import java.util.HashSet;

public class MenuScreen extends GameScreen {

    private MenuScreenComponents components;
    private Point2D lastClick; // position of last click

    public MenuScreen(double width, double height, GameHandler controller) {
        super(new MenuScreenComponents(), width, height, controller);
        components = (MenuScreenComponents) super.getRoot();

        this.addEventHandlers();
        this.onLoad();
    }

    private void addEventHandlers() {
        this.setOnMouseClicked(event -> {
            lastClick = new Point2D(event.getX(), event.getY());
            this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        });
    }

    private void onLoad() {
        transitionIn();
        this.setFill(Color.BLACK);
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

        ft.setOnFinished(event -> waitForOption());
        ft.play();
    }

    private void waitForOption() {
        System.out.println("waiting for option...");
    }

    @Override
    public void transitionOut() {
        SplashScreen nxt = new SplashScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, controller);
        controller.changeScene(this, nxt);
    }
}
