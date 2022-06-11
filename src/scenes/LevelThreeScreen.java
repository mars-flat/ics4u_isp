package scenes;

import components.DialoguePopup;
import components.LevelThreeComponents;
import javafx.scene.paint.Color;
import utilities.Constants;
import utilities.Entity;
import utilities.GameHandler;

import java.util.HashSet;

public class LevelThreeScreen extends GameScreen {

    private LevelThreeComponents components;

    /**
     * Set of current keyboard inputs.
     */
    private HashSet<String> keyboardInputs;

    public LevelThreeScreen(double width, double height, GameHandler controller) {
        super(new LevelThreeComponents(), width, height, controller);
        components = (LevelThreeComponents) super.getRoot();

        this.onLoad();
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
                components.getActivePopup() == null && components.getActiveMinigame() == null) {

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
            keyboardInputs.remove("SPACE");
            if (components.getActivePopup() != null) {
                components.getActivePopup().onChangeRequest();
            }
        }

        if (keyboardInputs.contains("E") && components.getActiveMinigame() == null) {
            if (components.getActivePopup() == null) components.checkForRoomChange();
        }
    }

    private void onLoad() {
        this.setFill(Color.BLACK);

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

        handleKeyboardInputs(currentTick);
        components.checkInBounds();

        if (currentTick % 3 == 0) {
            if (components.getActivePopup() instanceof DialoguePopup)
                ((DialoguePopup)components.getActivePopup()).showNextChar();
        }

        components.checkGameOver();
    }

    @Override
    public void nextScene() {
        //return to menu screen
        LevelSelectScreen nxt = new LevelSelectScreen(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                controller);
        controller.changeScene(this, nxt);
    }
}
