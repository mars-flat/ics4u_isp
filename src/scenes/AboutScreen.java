package scenes;

import components.AboutScreenComponents;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

public class AboutScreen extends GameScreen {

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

    private void onLoad() {
        components.changeBackground(Constants.ABOUT_SCREEN);
        transitionIn();
        this.setOnMouseClicked(event -> {
            System.out.println(event.getX() + " " + event.getY());
        });
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
        FillTransition ft = new FillTransition(
                Duration.millis(400),
                components.getTransitionRectangle(),
                Color.TRANSPARENT, Color.BLACK);

        PauseTransition pt = new PauseTransition(
                Duration.millis(2000)
        );
    }

    @Override
    public void nextScene() {
        transitionOut();
        MenuScreen nxt = new MenuScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                controller);
        controller.changeScene(this, nxt);
    }
}
