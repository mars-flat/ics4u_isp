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

/**
 * Container for dialogue within the game.
 *
 * @since 1.4, 5/20/2022
 * @author Shane Chen
 */
public class DialogPane extends Pane {

    /**
     * The image the dialogue should be written over.
     */
    private ImageView dialogueBox;

    /**
     * Testing box.
     */
    private Rectangle testBox;

    /**
     * The string of dialogue to be displayed.
     */
    private String dialogue;

    /**
     * The Text object for the string of dialogue.
     */
    private Text text;

    /**
     * The number of characters in the string the dialogue should show.
     * Used for animation effect.
     */
    private int showChars;

    /**
     *
     */
    private DialogueChangeRequest changeRequestHandler;

    public DialogPane(String dialogue, DialogueChangeRequest changeRequestHandler) {
        this(new ImageView(
                Tools.getImage(Constants.BEDROOM_SCREEN, 960, 720, true, true)
            ), dialogue, changeRequestHandler);
    }

    /**
     * Creates an instance of this class.
     *
     * @param dialogueBox
     * The image the dialogue should be written over.
     *
     * @param dialogue
     * The dialogue string to be displayed.
     */
    public DialogPane(ImageView dialogueBox, String dialogue, DialogueChangeRequest changeRequestHandler) {
        this.dialogueBox = dialogueBox;
        this.dialogue = dialogue;
        this.changeRequestHandler = changeRequestHandler;
        showChars = 0;
        addComponents();
    }

    /**
     * Add components to this component.
     */
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

    /**
     * Increase the number of characters in the string the dialogue should show.
     */
    public void showNextChar() {
        if (showChars == dialogue.length()) return;
        text.setText(dialogue.substring(0, ++showChars));
    }

    /**
     * What happens when a request has been made to change the dialogue.
     */
    public void onChangeRequest() {
        changeRequestHandler.onChangeRequest();
    }

    public void setOnChangeRequest(DialogueChangeRequest dialogueChangeRequest) {
        changeRequestHandler = dialogueChangeRequest;
    }

    public void reset() {
        showChars = 0;
    }
}
