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
                Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true)
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

        text = new Text(150, 550, dialogue.substring(0, showChars));
        text.setFont(Tools.getCustomFont(Constants.FONT_FILE, 36));
        text.setWrappingWidth(650);
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
        reset();
        changeRequestHandler.onChangeRequest();
    }

    public void setOnChangeRequest(DialogueChangeRequest dialogueChangeRequest) {
        changeRequestHandler = dialogueChangeRequest;
    }

    public void reset() {
        showChars = 0;
    }
}
