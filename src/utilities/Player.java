package utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;

public class Player extends Entity {

    private Image[] characterStances; // 4 x [a, b, idle]
    private ImageView character;
    private int currentDirection;
    private int cur;

    public Player(int spawnX, int spawnY, int width, int height) {
        super(spawnX, spawnY, width, height, Color.TRANSPARENT);
        loadStances();
        character = new ImageView(characterStances[9]);
        currentDirection = 7;
        character.xProperty().bind(xProperty());
        character.yProperty().bind(yProperty());
    }

    private void loadStances() {
        characterStances = new Image[13];
        for (int i = 1; i <= 12; ++i) {
            characterStances[i] = Tools.getImage(new File(Constants.DATA_PATH + "stances\\character" + i + ".png"),
                    (int)getWidth()+20, (int)getHeight()+20, true, true);
        }
    }

    public void moveLeft() {
        super.moveLeft();
        currentDirection = 1;
    }

    public void moveRight() {
        super.moveRight();
        currentDirection = 4;
    }

    public void moveUp() {
        super.moveUp();
        currentDirection = 7;
    }

    public void moveDown() {
        super.moveDown();
        currentDirection = 10;
    }

    public void update(boolean isWalking, long currentTick) {
        boolean change = (currentTick % 15 == 0);

        if (isWalking) {
            if (change) cur = (cur + 1) % 2;
            character.setImage(characterStances[cur + currentDirection]);
        } else {
            character.setImage(characterStances[currentDirection + 2]);
        }
    }

    public ImageView getCharacter() {
        return character;
    }
}
