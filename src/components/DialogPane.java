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
    private List<String> texts;

    public DialogPane(ImageView dialogueBox, String dialogue) {
        this.dialogueBox = dialogueBox;
        this.dialogue = dialogue;
        texts = new ArrayList<>();
        addComponents();
    }

    private void addComponents() {
        this.getChildren().add(dialogueBox);

        testBox = new Rectangle(80, 420, 780, 200);
        testBox.setFill(Color.GRAY);
        testBox.setArcWidth(5);
        testBox.setArcHeight(5);
        this.getChildren().add(testBox);

//        for (int current = 0; current < dialogue.length(); current += CHAR_LINE_LIMIT) {
//            texts.add(dialogue.substring(current, Math.min(dialogue.length(), current + CHAR_LINE_LIMIT)
//            ));
//        }
//        int currentY = 400, offset = 0;
//        for (String str : texts) {
//            Text t = new Text(100, currentY+offset, str);
//            t.setFont(Tools.getCustomFont(Constants.FONT_FILE, DIALOGUE_FONT_SIZE));
//            this.getChildren().add(t);
//            offset += 50;
//        }
        Text t = new Text(100, 500, dialogue);
        t.setFont(Tools.getCustomFont(Constants.FONT_FILE, 36));
        t.setWrappingWidth(800);
        this.getChildren().add(t);
    }
}
