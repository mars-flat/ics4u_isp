package scenes;

import components.LevelOneComponents;
import javafx.scene.Parent;
import utilities.GameHandler;

public class LevelOneScreen extends GameScreen {

    private LevelOneComponents components;

    public LevelOneScreen(double width, double height, GameHandler controller) {
        super(new LevelOneComponents(), width, height, controller);
        components = (LevelOneComponents) super.getRoot();

        this.onLoad();
    }

    private void onLoad() {

    }

    @Override
    public void onTick(long currentTick) {

    }

    @Override
    public void transitionIn() {

    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void nextScene() {

    }
}
