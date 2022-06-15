package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

/**
 * Components for the "presentation" minigame, played in classroom 1 of level 3.
 *
 * @since 4.4, 6/10/2022
 * @author Annie Wong
 */
public class PresentationMinigame extends Minigame {

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
    public PresentationMinigame(ImageView background, LevelThreeComponents controller) {
        super(background, controller);
        this.controller = controller;
        addComponents();
    }

    /**
     * Adds components to this minigame.
     */
    private void addComponents() {

        ImageView dialogueBg = new ImageView(Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true));

        Text question = new Text(130, 500, "You already have your slides. Present?");
        question.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));

        Text tag = new Text(635, 430, "Question");
        tag.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 26));

        Text no = new Text(500, 600, "No, stall for longer");
        no.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        no.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[3]);
            controller.setActiveMinigame(null);
        });

        Text yes = new Text(130, 600, "Yes, I'm ready");
        yes.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        yes.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[2]);
            controller.setActiveMinigame(null);
        });

        choice = new Group(dialogueBg, question, tag, no, yes);
        choice.setVisible(false);
        this.getChildren().add(choice);

        minigameDialogue = new DialoguePopup[4];

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "Alright, class. Today we'll be continuing our About Me presentations.",
                "Who would like to come up and share a little about themselves? Ooh, how about you?",
                "You gave your presentation and was met with applause!",
                "You missed the opportunity to express yourself and overcome your fears... (+1 Anxiety)"
        };

        minigameDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.TEACHER_1, 240, 280, true, true)),
                "Teacher", dialogue[0], () -> { controller.setActivePopup(minigameDialogue[1]); }
        );

        minigameDialogue[1] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.TEACHER_1, 240, 280, true, true)),
                "Teacher", dialogue[1], () -> {
                    controller.setActivePopup(null);
                    choice.setVisible(true);
                }
        );

        minigameDialogue[2] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Narration", dialogue[2], () -> {
                    controller.setActivePopup(null);
            }
        );

        minigameDialogue[3] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Narration", dialogue[3], () -> {
                controller.setActivePopup(null);
                controller.getAnxietyBar().incrementAnxiety();
        }
        );
    }

    /**
     * What happens on the launching of the minigame.
     */
    public void onLaunch() {
        controller.setActivePopup(minigameDialogue[0]);
    }
}
