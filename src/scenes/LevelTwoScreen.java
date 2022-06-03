package scenes;

import components.DialoguePopup;
import components.LevelOneComponents;
import components.LevelTwoComponents;
import utilities.Constants;
import utilities.GameHandler;
import java.util.HashSet;

/**
 * The screen for the second level of the game.
 *
 * @since 3.0, 5/30/2022
 *
 * @author Shane Chen
 */
public class LevelTwoScreen extends GameScreen {

    /**
     * Instance of associated component class.
     * @see LevelTwoComponents
     */
    private LevelTwoComponents components;

    /**
     * Set of current keyboard inputs.
     */
    private HashSet<String> keyboardInputs;

    /**
     * Creates an instance of this class.
     *
     * @param width
     * Width of the window. Should conform to the constants in {@link utilities.Constants}
     *
     * @param height
     * Height of the window. Should conform to the constants in {@link utilities.Constants}
     *
     * @param controller
     * {@link GameHandler} instance.
     *
     */
    public LevelTwoScreen(double width, double height, GameHandler controller) {
        super(new LevelTwoComponents(), width, height, controller);
        components = (LevelTwoComponents) super.getRoot();

        this.onLoad();
    }

    /**
     * What happens when the scene is loaded (i.e., an instance is created).
     */
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

    /**
     * Handles keyboard inputs such as SPACE for changing popups
     *
     * @param currentTick
     * The current tick.
     */
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
