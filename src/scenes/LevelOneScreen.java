package scenes;

import components.LevelOneComponents;
import javafx.scene.paint.Color;
import utilities.GameHandler;

import java.util.HashSet;

public class LevelOneScreen extends GameScreen {

    private LevelOneComponents components;
    private HashSet<String> keyboardInputs;

    public LevelOneScreen(double width, double height, GameHandler controller) {
        super(new LevelOneComponents(), width, height, controller);
        components = (LevelOneComponents) super.getRoot();

        this.onLoad();
    }

    private void onLoad() {
        this.setFill(Color.BLACK);
        this.setOnKeyPressed(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.add(keyName);
        });
        this.setOnMousePressed(event -> {

        });
    }

    @Override
    public void onTick(long currentTick) {
        if (currentTick % 5 == 0) components.getActiveDialogue().showNextChar();

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
