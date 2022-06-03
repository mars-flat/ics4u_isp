package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scenes.AboutScreen;
import scenes.LevelSelectScreen;
import scenes.MenuScreen;
import utilities.Constants;

import java.io.File;

public class LevelSelectComponents extends ScreenComponent {

    private Rectangle transitionRectangle;

    public LevelSelectComponents() {
        super();
        addComponents();
    }

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

    @Override
    public void addComponents() {
        addLevelButton(0, 0, 340, 720, 75, 300, 225, 300, Constants.LEVEL_SELECT_SCREEN_1);
        addLevelButton(341, 0, 340, 720, 380, 335, 225, 300, Constants.LEVEL_SELECT_SCREEN_2);
        addLevelButton(681, 0, 340, 720, 665, 325, 225, 300, Constants.LEVEL_SELECT_SCREEN_3);

        Rectangle arrowButton = new Rectangle(25, 20, 90, 70);
        arrowButton.setFill(Color.TRANSPARENT);
        arrowButton.setOnMouseClicked(event -> {
            ((LevelSelectScreen) this.getScene()).nextScene(0);
        });

        transitionRectangle = new Rectangle(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                Color.BLACK);

        transitionRectangle.setPickOnBounds(false);
        transitionRectangle.setMouseTransparent(true);

        this.getChildren().addAll(arrowButton, transitionRectangle);
    }

    public Rectangle getTransitionRectangle() {
        return transitionRectangle;
    }
}
