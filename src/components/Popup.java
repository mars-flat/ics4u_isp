package components;

import javafx.scene.layout.Pane;

/**
 * Container for popups within the game.
 *
 * Functionality includes a change request handler which is implemented with
 * the creation of each instance.
 *
 * @since 2.4, 5/27/2022
 * @author Shane Chen
 */
public class Popup extends Pane {
    private ChangeRequest changeRequestHandler;
    public Popup(ChangeRequest changeRequestHandler) {
        super();
        this.changeRequestHandler = changeRequestHandler;
    }

    public void onChangeRequest() {
        changeRequestHandler.onChangeRequest();
    }

    public void setOnChangeRequest(ChangeRequest dialogueChangeRequest) {
        changeRequestHandler = dialogueChangeRequest;
    }

    public ChangeRequest getOnChangeRequest() {
        return changeRequestHandler;
    }
}
