package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Constants;

/**
 * The components for the splash screen.
 *
 * @since 1.1, 5/17/2022
 * @author Annie Wong
 */
public class SplashScreenComponents extends ScreenComponent {

    /**
     * {@link Rectangle} object to create the {@link javafx.animation.FillTransition}
     * effect.
     */
    private Rectangle transitionRectangle;

    /**
     * Creates an instance of this class. Calls {@link SplashScreenComponents#addComponents()}.
     */
    public SplashScreenComponents() {
        super();
        addComponents();
    }

    /**
     * Add components to this root component.
     */
    public void addComponents() {

        transitionRectangle = new Rectangle(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                Color.BLACK);

        this.getChildren().addAll(transitionRectangle);
    }

    /**
     * Get the Rectangle object.
     *
     * @return
     * The Rectangle object.
     */
    public Rectangle getTransitionRectangle() {
        return transitionRectangle;
    }
}
