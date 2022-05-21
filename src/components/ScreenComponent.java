package components;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import utilities.Constants;

import java.io.File;

/**
 * This abstract class serves as a superclass for all screen
 * component classes within the program. This class will function
 * as the root component for all attached components to the scene.
 *
 * These components require implementation of essential behavior,
 * such as changing the background and adding components to this class.
 *
 * @since 1.1, 5/17/2022
 * @author Shane Chen
 */
public abstract class ScreenComponent extends Pane {

    /**
     * Creates an instance of this class.
     */
    public ScreenComponent() {
        super();
    }

    /**
     * Changes the background image.
     *
     * @param loc
     * The file of the new background image.
     */
    public void changeBackground(File loc) {
        BackgroundImage background = new BackgroundImage(
                new Image(loc.toURI().toString(),
                        Constants.SCREEN_WIDTH,
                        Constants.SCREEN_HEIGHT,
                        false,
                        true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(background));
    }

    /**
     * Add components to this root component.
     */
    public abstract void addComponents();
}
