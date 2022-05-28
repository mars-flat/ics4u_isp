package components;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

/**
 * Container for dialogue within the game.
 * Extends {@link Popup} to inherit it's change request functionality.
 *
 * @since 1.4, 5/20/2022
 * @author Shane Chen
 */
public class DialoguePopup extends Popup {

    /**
     * The image of the speaker.
     */
    private ImageView speaker;

    /**
     * The title of the speaker.
     */
    private String speakerTitle;

    /**
     * The image the dialogue should be written over.
     */
    private ImageView dialogueBox;

    /**
     * The string of dialogue to be displayed.
     */
    private String dialogue;

    /**
     * The Text object for the string of the title.
     */
    private Text titleText;

    /**
     * The Text object for the string of dialogue.
     */
    private Text dialogueText;

    /**
     * The number of characters in the string the dialogue should show.
     * Used for animation effect.
     */
    private int showChars;

    public DialoguePopup(ImageView speaker, String speakerTitle, String dialogue, PopupChangeRequest changeRequestHandler) {
        this(speaker, speakerTitle, new ImageView(
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
    public DialoguePopup(ImageView speaker, String speakerTitle, ImageView dialogueBox, String dialogue, PopupChangeRequest changeRequestHandler) {
        super(changeRequestHandler);
        this.speaker = speaker;
        this.speakerTitle = speakerTitle;
        this.dialogueBox = dialogueBox;
        this.dialogue = dialogue;
        showChars = 0;
        addComponents();
    }

    /**
     * Add components to this component.
     */
    private void addComponents() {
        speaker.setX(80);
        speaker.setY(220);
        this.getChildren().add(speaker);
        this.getChildren().add(dialogueBox);

        dialogueText = new Text(150, 550, dialogue.substring(0, showChars));
        dialogueText.setFont(Tools.getCustomFont(Constants.FONT_FILE, 36));
        dialogueText.setWrappingWidth(650);
        this.getChildren().add(dialogueText);

        titleText = new Text(630, 430, speakerTitle);
        titleText.setFont(Tools.getCustomFont(Constants.FONT_FILE, 26));
        this.getChildren().add(titleText);

        Text continueText = new Text(625, 650, "SPACE to continue");
        continueText.setFont(Tools.getCustomFont(Constants.FONT_FILE, 22));
        this.getChildren().add(continueText);
    }

    /**
     * Increase the number of characters in the string the dialogue should show.
     */
    public void showNextChar() {
        if (showChars == dialogue.length()) return;
        dialogueText.setText(dialogue.substring(0, ++showChars));
    }

    /**
     * Call the {@code onChangeRequest()} of the superclass instance.
     */
    public void onChangeRequest() {
        reset();
        super.getOnChangeRequest().onChangeRequest();
    }

    /**
     * Reset the number of characters in the string the dialogue should show to 0.
     */
    public void reset() {
        showChars = 0;
    }
}
