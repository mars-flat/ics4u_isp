package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Constants;

public class SplashScreenComponents extends ScreenComponent {

    private Rectangle transitionRectangle;

    public SplashScreenComponents() {
        super();
        addComponents();
    }

    public void addComponents() {

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
