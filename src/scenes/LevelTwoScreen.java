package scenes;

import components.LevelTwoComponents;
import utilities.GameHandler;

public class LevelTwoScreen extends GameScreen {

    private LevelTwoComponents components;

    public LevelTwoScreen(double width, double height, GameHandler controller) {
        super(new LevelTwoComponents(), width, height, controller);
        components = (LevelTwoComponents) super.getRoot();

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
