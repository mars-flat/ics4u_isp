package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

/**
 * Components for the "library" minigame, played in the library room of level 3.
 *
 * @since 4.4, 6/10/2022
 * @author Shane Chen
 */
public class LibraryMinigame extends Minigame {

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
    public LibraryMinigame(ImageView background, LevelThreeComponents controller) {
        super(background, controller);
        this.controller = controller;
        addComponents();
    }

    /**
     * Adds components to this minigame.
     */
    private void addComponents() {

        ImageView dialogueBg = new ImageView(Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true));

        Text question = new Text(250, 500, "Ask the librarian for help?");
        question.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));

        Text tag = new Text(635, 430, "Question");
        tag.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 26));

        Text no = new Text(570, 600, "No, decline the help");
        no.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        no.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[1]);
        });

        Text yes = new Text(270, 600, "Yes, it would be much easier");
        yes.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 36));
        yes.setOnMouseClicked(event -> {
            choice.setVisible(false);
            controller.setActivePopup(minigameDialogue[3]);
        });

        choice = new Group(dialogueBg, question, tag, no, yes);
        choice.setVisible(false);
        this.getChildren().add(choice);

        minigameDialogue = new DialoguePopup[5];

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "Hey bud, need any help?",
                "Alright, hope you have a good day!",
                "Ah! I happen to have a copy right here which was just returned.",
                "Yes... uhh... do you have Diary of Max 2?",
                "You spent the next half hour searching for the book... (+1 Anxiety)"
        };

        minigameDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.LIBRARIAN, 1, 1, true, true)),
                "Librarian", dialogue[0], () -> {
                    controller.setActivePopup(null);
                    choice.setVisible(true);
                }
        );

        minigameDialogue[1] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.LIBRARIAN, 1, 1, true, true)),
                "Librarian", dialogue[1], () -> {
            controller.setActivePopup(minigameDialogue[4]);
            controller.setActiveMinigame(null);
            }
        );

        minigameDialogue[2] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.LIBRARIAN, 1, 1, true, true)),
                "Librarian", dialogue[2], () -> {
            controller.setActivePopup(null);
            controller.setActiveMinigame(null);
            }
        );

        minigameDialogue[3] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.YOUNGER_SIBLING, 240, 280, true, true)),
                "Younger Sibling", dialogue[3], () -> {
            controller.setActivePopup(minigameDialogue[2]);
        }
        );

        minigameDialogue[4] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 1, 1, true, true)),
                "Narration", dialogue[4], () -> {
                    controller.setActivePopup(null);
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
