package components;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Constants;

import java.io.File;

public class SplashScreenComponents extends Pane {

    private Rectangle transitionRectangle;

    public SplashScreenComponents() {
        super();
        addComponents();
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

    private void addComponents() {

        transitionRectangle = new Rectangle(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                Color.BLACK);

        this.getChildren().addAll(transitionRectangle);
    }

    public Rectangle getTransitionRectangle() {
        return transitionRectangle;
    }
}
