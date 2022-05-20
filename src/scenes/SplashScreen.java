package scenes;

import components.SplashScreenComponents;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

import java.io.File;

public class SplashScreen extends GameScreen {

    private boolean displayedGameTitle;
    private SplashScreenComponents components;

    public SplashScreen(double width, double height, GameHandler controller) {
        super(new SplashScreenComponents(), width, height, controller);
        components = (SplashScreenComponents) super.getRoot();
        this.onLoad();
    }

    private void onLoad() {
        displayedGameTitle = false;
        displayLogo();
    }

    @Override
    public void onTick(long currentTick) {

    }

    private void displayLogo() {
        components.changeBackground(Constants.SPLASH_SCREEN);
        transitionIn();
    }

    private void displayGameTitle() {
        displayedGameTitle = true;
        components.changeBackground(Constants.TITLE_SCREEN);
        transitionIn();
    }

    @Override
    public void transitionIn() {
        FillTransition ft = new FillTransition(
                Duration.millis(1000),
                components.getTransitionRectangle(),
                Color.BLACK, Color.TRANSPARENT);

        ft.setOnFinished(event -> pause());
        ft.play();
    }

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

    public void pause() {
        PauseTransition pt = new PauseTransition(Duration.millis(
                displayedGameTitle ? 2000 : 1000
        ));
        pt.setOnFinished(event -> transitionOut());
        pt.play();
    }

    public void nextScene() {
        MenuScreen nxt = new MenuScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, controller);
        controller.changeScene(this, nxt);
    }
}
