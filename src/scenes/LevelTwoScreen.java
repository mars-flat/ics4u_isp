package scenes;

import components.DialoguePopup;
import components.LevelTwoComponents;
import utilities.Constants;
import utilities.GameHandler;
import java.util.HashSet;

public class LevelTwoScreen extends GameScreen {

    private LevelTwoComponents components;

    private HashSet<String> keyboardInputs;


    public LevelTwoScreen(double width, double height, GameHandler controller) {
        super(new LevelTwoComponents(), width, height, controller);
        components = (LevelTwoComponents) super.getRoot();

        this.onLoad();
    }

    private void onLoad() {
        components.changeBackground(Constants.TABLE_SCREEN);

        keyboardInputs = new HashSet<>();

        this.setOnKeyPressed(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.add(keyName);
        });
        this.setOnKeyReleased(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.remove(keyName);
        });
    }

    @Override
    public void onTick(long currentTick) {
        // handle the changing of popups if applicable
        if (keyboardInputs.contains("SPACE")) {
            if (components.getActivePopup() != null) {
                components.getActivePopup().onChangeRequest();
                keyboardInputs.remove("SPACE");
            }
        }

        if (currentTick % 3 == 0) {
            if (components.getActivePopup() instanceof DialoguePopup)
                ((DialoguePopup)components.getActivePopup()).showNextChar();
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
