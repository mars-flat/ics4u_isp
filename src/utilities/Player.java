package utilities;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Player extends Entity {

    private ImageView character;

    public Player(int spawnX, int spawnY, int width, int height) {
        super(spawnX, spawnY, width, height, Color.TRANSPARENT);
        character = new ImageView(
                Tools.getImage(Constants.CHARACTER_FRONT_IDLE, width+20, height+20, true, true)
        );
        character.xProperty().bind(xProperty());
        character.yProperty().bind(yProperty());
    }

    public ImageView getCharacter() {
        return character;
    }
}
