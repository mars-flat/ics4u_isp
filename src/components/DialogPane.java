package components;

import javafx.scene.layout.Pane;

import java.io.File;

public class DialogPane extends Pane {

    private File background;
    private String dialogue;

    public DialogPane(File background, String dialogue) {
        this.background = background;
        this.dialogue = dialogue;
        addText();
    }

    private void addText() {



    }
}
