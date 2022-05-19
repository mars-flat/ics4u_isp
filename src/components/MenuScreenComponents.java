package components;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scenes.GameScreen;
import scenes.MenuScreen;
import utilities.Constants;

import java.io.File;

public class MenuScreenComponents extends Pane {

    //private final static File BACKGROUND_IMAGE = new File("C:\\Users\\shane\\IdeaProjects\\ics4u_isp\\src\\data\\splashscreen.png");

    //private GameScreen screen;

    private Rectangle playButton;
    private Rectangle aboutButton;
    private Rectangle quitButton;

    private Rectangle transitionRectangle;

    public MenuScreenComponents() {
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

    private void addPlayButton() {
        playButton = new Rectangle(345, 380, 270, 65);
        playButton.setFill(Color.RED);
        playButton.setOnMouseEntered(event -> {
            playButton.setFill(Color.DARKRED);
        });
        playButton.setOnMouseExited(event -> {
            playButton.setFill(Color.RED);
        });
        playButton.setOnMouseClicked(event -> {
            ((MenuScreen) this.getScene()).nextScene(1);
        });

        this.getChildren().add(playButton);
    }

    private void addAboutButton() {
        aboutButton = new Rectangle(345, 465, 270, 65);
        aboutButton.setFill(Color.ORANGE);
        aboutButton.setOnMouseEntered(event -> {
            aboutButton.setFill(Color.DARKORANGE);
        });
        aboutButton.setOnMouseExited(event -> {
            aboutButton.setFill(Color.ORANGE);
        });
        aboutButton.setOnMouseClicked(event -> {
            ((MenuScreen) this.getScene()).nextScene(2);
        });

        this.getChildren().add(aboutButton);
    }

    private void addQuitButton() {
        quitButton = new Rectangle(345, 550, 270, 65);
        quitButton.setFill(Color.YELLOW);
        quitButton.setOnMouseEntered(event -> {
            quitButton.setFill(Color.GOLD);
        });
        quitButton.setOnMouseExited(event -> {
            quitButton.setFill(Color.YELLOW);
        });
        quitButton.setOnMouseClicked(event -> {
            ((MenuScreen) this.getScene()).nextScene(3);
        });

        this.getChildren().add(quitButton);
    }

    // order matters
    private void addComponents() {

        addPlayButton();
        addAboutButton();
        addQuitButton();

        transitionRectangle = new Rectangle(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                Color.BLACK);

        transitionRectangle.setPickOnBounds(false);
        transitionRectangle.setMouseTransparent(true);

        this.getChildren().add(transitionRectangle);
    }

    public Rectangle getTransitionRectangle() {
        return transitionRectangle;
    }
}
