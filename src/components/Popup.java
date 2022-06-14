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

    /**
     * Change request functional interface.
     */
    private ChangeRequest changeRequestHandler;

    /**
     * Instantiates this class.
     *
     * @param changeRequestHandler
     * The change request lambda expression.
     */
    public Popup(ChangeRequest changeRequestHandler) {
        super();
        this.changeRequestHandler = changeRequestHandler;
    }

    /**
     * Calls the change request handler's method.
     */
    public void onChangeRequest() {
        changeRequestHandler.onChangeRequest();
    }

    /**
     * Set's the change request handler's method.
     * @param dialogueChangeRequest
     * The new change request handler's method.
     */
    public void setOnChangeRequest(ChangeRequest dialogueChangeRequest) {
        changeRequestHandler = dialogueChangeRequest;
    }

    /**
     * Gets the change request handler's method.
     *
     * @return
     * The change request handler's method.
     */
    public ChangeRequest getOnChangeRequest() {
        return changeRequestHandler;
    }
}
