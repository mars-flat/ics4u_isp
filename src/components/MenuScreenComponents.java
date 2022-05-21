package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import scenes.MenuScreen;
import utilities.Constants;
import utilities.Tools;

/**
 * The components for the main menu.
 *
 * @since 1.2, 5/17/2022
 * @author Shane Chen
 */
public class MenuScreenComponents extends ScreenComponent {

    /**
     * {@link Rectangle} object to create the {@link javafx.animation.FillTransition}
     * effect.
     */
    private Rectangle transitionRectangle;

    /**
     * Loading Text object.
     */
    private Text loadingText;

    /**
     * Creates an instance of this class.
     */
    public MenuScreenComponents() {
        super();
        addComponents();
    }

    /**
     * Add the play button components to the root.
     */
    private void addPlayButton() {
        Rectangle playButtonZone = new Rectangle(0, 370, 960, 85);
        playButtonZone.setFill(Color.TRANSPARENT);
        playButtonZone.setOnMouseEntered(event -> {
            this.changeBackground(Constants.MAIN_MENU_SCREEN_1);
        });

        Rectangle playButton = new Rectangle(345, 380, 270, 65);
        playButton.setFill(Color.TRANSPARENT);
        playButton.setOnMouseEntered(event -> {
            this.changeBackground(Constants.MAIN_MENU_SCREEN_1);
        });
        playButton.setOnMouseClicked(event -> {
            ((MenuScreen) this.getScene()).nextScene(1);
        });

        this.getChildren().addAll(playButtonZone, playButton);
    }

    /**
     * Add the about button components to the root.
     */
    private void addAboutButton() {
        Rectangle aboutButtonZone = new Rectangle(0, 450, 960, 90);
        aboutButtonZone.setFill(Color.TRANSPARENT);
        aboutButtonZone.setOnMouseEntered(event -> {
            this.changeBackground(Constants.MAIN_MENU_SCREEN_2);
        });

        Rectangle aboutButton = new Rectangle(345, 465, 270, 65);
        aboutButton.setFill(Color.TRANSPARENT);
        aboutButton.setOnMouseEntered(event -> {
            this.changeBackground(Constants.MAIN_MENU_SCREEN_2);
        });
        aboutButton.setOnMouseClicked(event -> {
            ((MenuScreen) this.getScene()).nextScene(2);
        });

        this.getChildren().addAll(aboutButtonZone, aboutButton);
    }

    /**
     * Add the quit button components to the root.
     */
    private void addQuitButton() {
        Rectangle quitButtonZone = new Rectangle(0, 540, 960, 90);
        quitButtonZone.setFill(Color.TRANSPARENT);
        quitButtonZone.setOnMouseEntered(event -> {
            this.changeBackground(Constants.MAIN_MENU_SCREEN_3);
        });

        Rectangle quitButton = new Rectangle(345, 550, 270, 65);
        quitButton.setFill(Color.TRANSPARENT);
        quitButton.setOnMouseEntered(event -> {
            this.changeBackground(Constants.MAIN_MENU_SCREEN_3);
        });
        quitButton.setOnMouseClicked(event -> {
            ((MenuScreen) this.getScene()).nextScene(3);
        });

        this.getChildren().addAll(quitButtonZone, quitButton);
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {

        addPlayButton();
        addAboutButton();
        addQuitButton();

        transitionRectangle = new Rectangle(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                Color.BLACK);

        transitionRectangle.setPickOnBounds(false);
        transitionRectangle.setMouseTransparent(true);

        loadingText = new Text(390, 320, "Loading...");
        loadingText.setFont(Tools.getCustomFont(Constants.FONT_FILE, 48));
        loadingText.setPickOnBounds(false);
        loadingText.setMouseTransparent(true);
        loadingText.setFill(Color.WHITE);
        loadingText.setVisible(false);


        this.getChildren().addAll(transitionRectangle, loadingText);
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

    /**
     * Get the Text object.
     *
     * @return
     * The Text object.
     */
    public Text getLoadingText() {
        return loadingText;
    }
}
