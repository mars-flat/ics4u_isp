package components;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Superclass for all minigames.
 *
 * @since 4.2, 6/8/2022
 * @author Shane Chen
 */
public abstract class Minigame extends Pane {

    /**
     * LevelThreeComponents instance.
     */
    private LevelThreeComponents controller;

    /**
     * Background image.
     */
    private ImageView background;

    /**
     * Instantiates this class.
     *
     * @param background
     * The background for the minigame.
     *
     * @param controller
     * LevelThreeComponents instance.
     */
    public Minigame(ImageView background, LevelThreeComponents controller) {
        this.controller = controller;
        this.background = background;
        getChildren().add(background);
    }

    /**
     * What happens on the launching of the minigame.
     */
    public abstract void onLaunch();
}
