package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

public class LibraryMinigame extends Minigame {

    private LevelThreeComponents controller;

    private DialoguePopup[] minigameDialogue;
    private Group choice;

    public LibraryMinigame(ImageView background, LevelThreeComponents controller) {
        super(background, controller);

        addComponents();
    }

    private void addComponents() {

        ImageView askHelpBg = new ImageView(Tools.getImage(Constants.DIALOGUE_BOX, 960, 720, true, true));
        Text t = new Text();

        minigameDialogue = new DialoguePopup[3];

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "Hey bud, need any help?",
                "No problem, ask any time if you do!",
                "Ah! I happen to have a copy right here which was just returned."
        };

        minigameDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 0, 0, true, true)),
                "Librarian", dialogue[0], () -> {
                    controller.setActivePopup(null);
                    choice.setVisible(true);
                }
        );


    }
}
