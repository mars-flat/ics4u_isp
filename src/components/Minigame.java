package components;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Minigame extends Pane {

    private LevelThreeComponents controller;
    private ImageView background;

    public Minigame(ImageView background, LevelThreeComponents controller) {
        this.controller = controller;
        this.background = background;
        getChildren().add(background);
    }
}
