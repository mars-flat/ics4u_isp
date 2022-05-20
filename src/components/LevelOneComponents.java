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

    private DialogPane activeDialogue;

    public LevelOneComponents() {
        super();
        addComponents();
    }

    @Override
    public void addComponents() {

        activeDialogue = new DialogPane(
                new ImageView(Tools.getImage(Constants.BEDROOM_SCREEN,
                        960,720,true,true)),
                "I wonder if my sibling has the book I need."
        );

        this.getChildren().add(activeDialogue);
    }

    public DialogPane getActiveDialogue() {
        return activeDialogue;
    }
}
