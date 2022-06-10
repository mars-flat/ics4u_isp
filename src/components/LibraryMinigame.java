package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

public class LibraryMinigame extends Minigame {

    private LevelThreeComponents controller;

    private DialoguePopup[] minigameDialogue;
    private Group choice;

    public LibraryMinigame(ImageView background, LevelThreeComponents controller) {
        super(background, controller);
        this.controller = controller;
        addComponents();
    }

    private void addComponents() {

        ImageView askHelpBg = new ImageView(Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true));

        Text question = new Text(250, 500, "Ask the librarian for help?");
        question.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));

        Text tag = new Text(635, 430, "Question");
        tag.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 26));

        Text no = new Text(570, 600, "No");
        no.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        no.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[1]);
        });

        Text yes = new Text(270, 600, "Yes");
        yes.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        yes.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[2]);
        });

        choice = new Group(askHelpBg, question, tag, no, yes);
        choice.setVisible(false);
        this.getChildren().add(choice);

        minigameDialogue = new DialoguePopup[3];

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "Hey bud, need any help?",
                "Alright, hope you have a good day!",
                "Ah! I happen to have a copy right here which was just returned."
        };

        minigameDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Librarian", dialogue[0], () -> {
                    controller.setActivePopup(null);
                    choice.setVisible(true);
                }
        );

        minigameDialogue[1] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Librarian", dialogue[1], () -> {
            controller.setActivePopup(null);
            controller.setActiveMinigame(null);
            }
        );

        minigameDialogue[2] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Librarian", dialogue[2], () -> {
            controller.setActivePopup(null);
            controller.setActiveMinigame(null);
            }
        );
    }

    public void onLaunch() {
        controller.setActivePopup(minigameDialogue[0]);
    }
}
