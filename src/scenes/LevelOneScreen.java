package scenes;

import components.DialoguePopup;
import components.LevelOneComponents;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import utilities.Constants;
import utilities.Entity;
import utilities.GameHandler;

import java.util.HashSet;

/**
 * The screen for the first level of the game.
 *
 * Required functionality includes keyboard and mouse
 * event listening, and updating components on each tick.
 *
 * @since 1.3, 5/19/2022
 * Modification on 5/27/2022
 *
 * @author Shane Chen, Annie Wong
 */
public class LevelOneScreen extends GameScreen {

    /**
     * Instance of associated component class.
     * @see LevelOneComponents
     */
    private LevelOneComponents components;

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
    public LevelOneScreen(double width, double height, GameHandler controller) {
        super(new LevelOneComponents(), width, height, controller);
        components = (LevelOneComponents) super.getRoot();

        this.onLoad();
    }

    /**
     * What happens when the scene is loaded (i.e., an instance is created).
     */
    private void onLoad() {
        this.setFill(Color.BLACK);
        components.changeBackground(Constants.BEDROOM_SCREEN);

        keyboardInputs = new HashSet<>();

        this.setOnKeyPressed(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.add(keyName);
        });

        this.setOnKeyReleased(event -> {
            String keyName = event.getCode().toString();
            keyboardInputs.remove(keyName);
        });

        this.setOnMouseClicked(event -> {
            System.out.println(event.getX() + " " + event.getY());
        });
    }

    /**
     * Handles keyboard inputs such as WASD for moving the player,
     * and SPACE for changing popups
     *
     * @param currentTick
     * The current tick.
     */
    private void handleKeyboardInputs(long currentTick) {

        // handle movement, update player's character
        if ((keyboardInputs.contains("W") ||
             keyboardInputs.contains("A") ||
             keyboardInputs.contains("S") ||
             keyboardInputs.contains("D")) &&
             components.getActivePopup() == null) {

            boolean canMoveUp = true;
            boolean canMoveLeft = true;
            boolean canMoveDown = true;
            boolean canMoveRight = true;
            for (Entity e : components.getOtherEntities()) {
                if (components.getPlayer().isCollidingUp(e)) canMoveUp = false;
                if (components.getPlayer().isCollidingLeft(e)) canMoveLeft = false;
                if (components.getPlayer().isCollidingDown(e)) canMoveDown = false;
                if (components.getPlayer().isCollidingRight(e)) canMoveRight = false;
            }


            if (keyboardInputs.contains("W")) {
                if (canMoveUp) components.getPlayer().moveUp();
            }
            else if (keyboardInputs.contains("A")) {
                if (canMoveLeft) components.getPlayer().moveLeft();
            }
            else if (keyboardInputs.contains("S")) {
                if (canMoveDown) components.getPlayer().moveDown();
            }
            else if (keyboardInputs.contains("D")) {
                if (canMoveRight) components.getPlayer().moveRight();
            }
            components.getPlayer().update(true, currentTick);
        } else {
            components.getPlayer().update(false, currentTick);
        }

        // handle the changing of popups if applicable
        if (keyboardInputs.contains("SPACE")) {
            if (components.getActivePopup() != null) {
                components.getActivePopup().onChangeRequest();
                keyboardInputs.remove("SPACE");
            }
        }
    }

    /**
     * What happens on each tick. AnimationTimer in {@link GameHandler} will
     * repeatedly call this method of the currently displayed scene.
     *
     * @param currentTick
     * The current tick count.
     */
    @Override
    public void onTick(long currentTick) {
        handleKeyboardInputs(currentTick);
        if (currentTick % 3 == 0) {
            if (components.getActivePopup() instanceof DialoguePopup)
                ((DialoguePopup)components.getActivePopup()).showNextChar();
        }
//        System.out.println(components.getPlayer().getX() + " " + components.getPlayer().getY()
//         + " " + components.getPlayer().getTranslateX() + " " + components.getPlayer().getTranslateY());
    }

    /**
     * What happens on a transition into this scene.
     */
    @Override
    public void transitionIn() {

    }

    /**
     * What happens on a transition out of this scene.
     */
    @Override
    public void transitionOut() {

    }

    /**
     * Handle the switching of scenes. This should utilize {@link GameHandler#changeScene(Scene, Scene)}.
     */
    @Override
    public void nextScene() {

    }
}
