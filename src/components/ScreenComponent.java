package components;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import utilities.Constants;

import java.io.File;
import java.io.IOException;

public abstract class ScreenComponent extends Pane {

    public ScreenComponent() {
        super();
    }

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
    public abstract void addComponents() throws IOException;
}
