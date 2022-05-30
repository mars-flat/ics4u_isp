package scenes;

import components.LevelThreeComponents;
import utilities.GameHandler;

public class LevelThreeScreen extends GameScreen {

    private LevelThreeComponents components;

    public LevelThreeScreen(double width, double height, GameHandler controller) {
        super(new LevelThreeComponents(), width, height, controller);
        components = (LevelThreeComponents) super.getRoot();

        this.onLoad();
    }

    private void onLoad() {

    }

    @Override
    public void onTick(long currentTick) {
        if (currentTick % 3 == 0) {
            if (components.getActiveDialogue() != null) components.getActiveDialogue().showNextChar();
        }
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
