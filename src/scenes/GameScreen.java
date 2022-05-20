package scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import utilities.GameHandler;

public abstract class GameScreen extends Scene {

    public GameHandler controller;

    public GameScreen(Parent root, double width, double height, GameHandler controller) {
        super(root, width, height);
        this.controller = controller;
    }

    public abstract void onTick(long currentTick);
    public abstract void transitionIn();
    public abstract void transitionOut();
    public abstract void nextScene();
}
