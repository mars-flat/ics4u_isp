package components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

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
