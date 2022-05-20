package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

import java.io.File;

public class LevelOneComponents extends ScreenComponent {

    private Text test;

    public LevelOneComponents() {
        super();
        addComponents();
    }

    @Override
    public void addComponents() {
        test = new Text(100, 100, "Level 1");
        test.setFont(Font.font(24));
        test.setFill(Color.WHITE);
        this.getChildren().add(test);

        DialogPane dialogPane = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960,720,true,true)),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        );
        this.getChildren().add(dialogPane);
    }
}
