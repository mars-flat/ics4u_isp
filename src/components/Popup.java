package components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

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
    private PopupChangeRequest changeRequestHandler;
    public Popup(PopupChangeRequest changeRequestHandler) {
        super();
        this.changeRequestHandler = changeRequestHandler;
    }

    public void onChangeRequest() {
        changeRequestHandler.onChangeRequest();
    }

    public void setOnChangeRequest(PopupChangeRequest dialogueChangeRequest) {
        changeRequestHandler = dialogueChangeRequest;
    }

    public PopupChangeRequest getOnChangeRequest() {
        return changeRequestHandler;
    }
}
