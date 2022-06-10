package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

public class PresentationMinigame extends Minigame {

    private LevelThreeComponents controller;

    private DialoguePopup[] minigameDialogue;
    private Group choice;

    public PresentationMinigame(ImageView background, LevelThreeComponents controller) {
        super(background, controller);
        this.controller = controller;
        addComponents();
    }

    private void addComponents() {

        ImageView dialogueBg = new ImageView(Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true));

        Text question = new Text(250, 500, "Present?");
        question.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));

        Text tag = new Text(635, 430, "Question");
        tag.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 26));

        Text no = new Text(570, 600, "No");
        no.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        no.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[3]);
            controller.setActiveMinigame(null);
        });

        Text yes = new Text(270, 600, "Yes");
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
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Teacher", dialogue[0], () -> { controller.setActivePopup(minigameDialogue[1]); }
        );

        minigameDialogue[1] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
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

    public void onLaunch() {
        controller.setActivePopup(minigameDialogue[0]);
    }
}
