package components;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class LevelOneComponents extends Pane implements ScreenComponent {

    private Text dialogue;

    public LevelOneComponents() {
        super();
        addComponents();
    }

    @Override
    public void changeBackground(File loc) {

    }

    @Override
    public void addComponents() {
        dialogue = new Text(100, 100, "Level 1");
        dialogue.setFont(Font.font(24));
        this.getChildren().add(dialogue);
    }
}
