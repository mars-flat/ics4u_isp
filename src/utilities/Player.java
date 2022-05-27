package utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.tools.Tool;
import java.io.File;

public class Player extends Entity {

    private Image[] characterStances; // 4 x [a, b, idle]
    private ImageView character;
    private int currentDirection;

    public Player(int spawnX, int spawnY, int width, int height) {
        super(spawnX, spawnY, width, height, Color.TRANSPARENT);
        loadStances();
        character = new ImageView(characterStances[11]);
        currentDirection = 4;
        character.xProperty().bind(xProperty());
        character.yProperty().bind(yProperty());
    }

    private void loadStances() {
        characterStances = new Image[12];
        for (int i = 1; i <= 12; ++i) {
            characterStances[i-1] = Tools.getImage(new File(Constants.DATA_PATH + "stances\\character" + i),
                    (int)getWidth()+20, (int)getHeight()+20, true, true);
        }
    }

    public void moveLeft() {
        super.moveLeft();
        currentDirection = 1;
    }

    public void moveRight() {
        super.moveRight();
        currentDirection = 2;
    }

    public void moveUp() {
        super.moveUp();
        currentDirection = 3;
    }

    public void moveDown() {
        super.moveDown();
        currentDirection = 4;
    }

    public void update(boolean isWalking) {
        if (isWalking) {

        } else {
            switch(currentDirection) {
                case 1:
                    character.setImage(characterStances[2]);
                    break;
                case 2:

            }
        }
    }

    public ImageView getCharacter() {
        return character;
    }
}
