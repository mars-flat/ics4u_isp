package components;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DialogPane extends Pane {

    private ImageView dialogueBox;
    private Rectangle testBox;
    private String dialogue;
    private Text text;
    private int showChars;

    public DialogPane(ImageView dialogueBox, String dialogue) {
        this.dialogueBox = dialogueBox;
        this.dialogue = dialogue;
        showChars = 0;
        addComponents();
    }

    private void addComponents() {
        this.getChildren().add(dialogueBox);

        testBox = new Rectangle(80, 420, 780, 240);
        testBox.setFill(Color.GRAY);
        testBox.setArcWidth(50);
        testBox.setArcHeight(50);
        this.getChildren().add(testBox);

        text = new Text(100, 500, dialogue.substring(0, showChars));
        text.setFont(Tools.getCustomFont(Constants.FONT_FILE, 36));
        text.setWrappingWidth(700);
        this.getChildren().add(text);
    }

    public void showNextChar() {
        if (showChars == dialogue.length()) return;
        text.setText(dialogue.substring(0, ++showChars));
    }
}
