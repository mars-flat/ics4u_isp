package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

/**
 * Components for the "math" minigame, played in classroom 2 of level 3.
 *
 * @since 4.4, 6/10/2022
 * @author Annie Wong
 */
public class MathMinigame extends Minigame {

    /**
     * LevelThreeComponents instance.
     */
    private LevelThreeComponents controller;

    /**
     * Dialogue for the minigame.
     */
    private DialoguePopup[] minigameDialogue;

    /**
     * The choice part of the minigame.
     */
    private Group choice;

    /**
     * Instantiates this class.
     *
     * @param background
     * The background for the minigame.
     *
     * @param controller
     * LevelThreeComponents instance.
     */
    public MathMinigame(ImageView background, LevelThreeComponents controller) {
        super(background, controller);
        this.controller = controller;
        addComponents();
    }

    /**
     * Adds components to this minigame.
     */
    private void addComponents() {

        ImageView dialogueBg = new ImageView(Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true));

        Text question = new Text(300, 500, "What do you do?");
        question.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));

        Text tag = new Text(635, 430, "Question");
        tag.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 26));

        Text avoid = new Text(170, 600, "Avoid eye contact");
        avoid.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        avoid.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[3]);
        });

        Text raise = new Text(570, 600, "Raise hand");
        raise.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        raise.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[1]);
        });

        choice = new Group(dialogueBg, question, tag, avoid, raise);
        choice.setVisible(false);
        this.getChildren().add(choice);

        minigameDialogue = new DialoguePopup[5];

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "Who can answer this question?",
                "Hey, I know this answer. It's 69.",
                "That's right! Good job.",
                "No one then, I guess. :(",
                "You let anxiety get the better of you, not answering the question when you knew the answer. (+1 anxiety)",
        };

        minigameDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.TEACHER_2, 240, 280, true, true)),
                "Teacher", dialogue[0], () -> {
            controller.setActivePopup(null);
            choice.setVisible(true);
        }
        );

        minigameDialogue[1] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.YOUNGER_SIBLING, 240, 280, true, true)),
                "Younger Sibling", dialogue[1], () -> {
            controller.setActivePopup(minigameDialogue[2]);
            controller.setActiveMinigame(null);
        });

        minigameDialogue[2] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.TEACHER_2, 240, 280, true, true)),
                "Teacher", dialogue[2], () -> {
            controller.setActivePopup(null);
            controller.setActiveMinigame(null);
        });

        minigameDialogue[3] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Narration", dialogue[3], () -> {
            controller.setActivePopup(minigameDialogue[4]);
        });

        minigameDialogue[4] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Narration", dialogue[4], () -> {
            controller.setActivePopup(null);
            controller.setActiveMinigame(null);
            controller.getAnxietyBar().incrementAnxiety();
        });
    }

    /**
     * What happens on the launching of the minigame.
     */
    public void onLaunch() {
        controller.setActivePopup(minigameDialogue[0]);
    }
}
