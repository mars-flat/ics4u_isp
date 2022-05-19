package components;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scenes.GameScreen;
import utilities.Constants;
import utilities.EmbeddedButton;

import java.io.File;

public class MenuScreenComponents extends Pane {

    private final static File BACKGROUND_IMAGE = new File("C:\\Users\\shane\\IdeaProjects\\ics4u_isp\\src\\data\\splashscreen.png");
    private GameScreen screen;
    private EmbeddedButton back;
    private Text goBackText;
    private Rectangle transitionRectangle;

    public MenuScreenComponents() {
        super();
        addComponents();
    }

    // order matters
    private void addComponents() {
        back = new EmbeddedButton(200, 200, 200, 100, Color.YELLOW);
        back.setOnMouseClicked(event -> {
            screen = (GameScreen) this.getScene();
            screen.transitionOut();
        });

        back.setOnMouseEntered(event -> {
            BackgroundImage background = new BackgroundImage(
                    new Image(BACKGROUND_IMAGE.toURI().toString(),
                            Constants.SCREEN_WIDTH,
                            Constants.SCREEN_HEIGHT,
                            false,
                            true),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            this.setBackground(new Background(background));

        });

        back.setOnMouseExited(event -> {
            this.setBackground(null);
        });

        goBackText = new Text(210, 250, "GO BACK");
        goBackText.setFont(Font.font(30));
        goBackText.setPickOnBounds(false);

        transitionRectangle = new Rectangle(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                Color.BLACK);
        transitionRectangle.setPickOnBounds(false);
        transitionRectangle.setMouseTransparent(true);

        this.getChildren().addAll(back, goBackText, transitionRectangle);
    }

    public Rectangle getTransitionRectangle() {
        return transitionRectangle;
    }
}
