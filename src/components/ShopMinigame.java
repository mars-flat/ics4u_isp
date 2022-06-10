package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

public class ShopMinigame extends Minigame {

    private LevelThreeComponents controller;

    private DialoguePopup[] minigameDialogue;
    private Group choice;

    public ShopMinigame(ImageView background, LevelThreeComponents controller) {
        super(background, controller);
        this.controller = controller;
        addComponents();
    }

    private void addComponents() {

        ImageView dialogueBg = new ImageView(Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true));

        Text question = new Text(250, 500, "Buy lunch from the store?");
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

        choice = new Group(dialogueBg, question, tag, no, yes);
        choice.setVisible(false);
        this.getChildren().add(choice);

        minigameDialogue = new DialoguePopup[4];

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "C-can I get a burger? With fries, please.",
                "No worries.",
                "That'll be 8.25. Enjoy!",
                "You ended up not eating lunch... (+1 Anxiety)"
        };

        minigameDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.YOUNGER_SIBLING, 240, 280, true, true)),
                "Younger Sibling", dialogue[1], () -> {
            controller.setActivePopup(minigameDialogue[2]);
        }
        );

        minigameDialogue[1] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Cashier", dialogue[1], () -> {
            controller.setActivePopup(minigameDialogue[3]);
            controller.setActiveMinigame(null);
        }
        );

        minigameDialogue[2] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Cashier", dialogue[2], () -> {
            controller.setActivePopup(null);
            controller.setActiveMinigame(null);
        }
        );

        minigameDialogue[3] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Narration", dialogue[3], () -> {
                    controller.setActivePopup(null);
                    controller.getAnxietyBar().incrementAnxiety();
                });
    }

    public void onLaunch() {
        choice.setVisible(true);
    }
}
