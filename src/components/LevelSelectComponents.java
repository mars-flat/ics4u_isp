package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scenes.AboutScreen;
import scenes.LevelSelectScreen;
import scenes.MenuScreen;
import utilities.Constants;

import java.io.File;

/**
 * The components for level select.
 *
 * @since 3.4, 6/3/2022
 * @author Annie Wong
 */
public class LevelSelectComponents extends ScreenComponent {
    /**
     * {@link Rectangle} object to create the {@link javafx.animation.FillTransition}
     * effect.
     */
    private Rectangle transitionRectangle;

    /**
     * Creates an instance of this class.
     */
    public LevelSelectComponents() {
        super();
        addComponents();
    }

    /**
     * Add any level select button components to the root.
     * @param zoneX
     * X coordinate of the button hover zone.
     * @param zoneY
     * Y coordinate of the button hover zone.
     * @param zoneWidth
     * Width of the button hover zone.
     * @param zoneHeight
     * Height of the button hover zone.
     * @param buttonX
     * X coordinate of the button click zone.
     * @param buttonY
     * Y coordinate of the button click zone.
     * @param buttonWidth
     * Width of the button click zone.
     * @param buttonHeight
     * Height of the button click zone.
     * @param screen
     * Background state for when the button is selected.
     */
    private void addLevelButton(int zoneX, int zoneY, int zoneWidth, int zoneHeight,
                                int buttonX, int buttonY, int buttonWidth, int buttonHeight, File screen){
        Rectangle levelButtonZone = new Rectangle(zoneX, zoneY, zoneWidth, zoneHeight);
        levelButtonZone.setFill(Color.TRANSPARENT);
        levelButtonZone.setOnMouseEntered(event -> {
            this.changeBackground(screen);
        });

        Rectangle levelButton = new Rectangle(buttonX, buttonY, buttonWidth, buttonHeight);
        levelButton.setFill(Color.TRANSPARENT);
        levelButton.setOnMouseEntered(event -> {
            this.changeBackground(screen);
        });
        levelButton.setOnMouseClicked(event -> {
            ((LevelSelectScreen) this.getScene()).nextScene(1);
        });

        this.getChildren().addAll(levelButtonZone, levelButton);
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {
        //level select buttons
        addLevelButton(0, 0, 340, 720, 75, 300, 225, 300, Constants.LEVEL_SELECT_SCREEN_1);
        addLevelButton(341, 0, 340, 720, 380, 335, 225, 300, Constants.LEVEL_SELECT_SCREEN_2);
        addLevelButton(681, 0, 340, 720, 665, 325, 225, 300, Constants.LEVEL_SELECT_SCREEN_3);

        //arrow button to return to main menu
        Rectangle arrowButton = new Rectangle(25, 20, 90, 70);
        arrowButton.setFill(Color.TRANSPARENT);
        arrowButton.setOnMouseClicked(event -> {
            ((LevelSelectScreen) this.getScene()).nextScene(0);
        });

        //transition
        transitionRectangle = new Rectangle(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                Color.BLACK);

        transitionRectangle.setPickOnBounds(false);
        transitionRectangle.setMouseTransparent(true);

        this.getChildren().addAll(arrowButton, transitionRectangle);
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
