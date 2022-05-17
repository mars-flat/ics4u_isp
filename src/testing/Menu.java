package testing;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashSet;

public abstract class Menu extends Scene {

    private MainMenuComponents components;
    private HashSet<String> inputs;

    public Menu(MainMenuComponents components, double width, double height) {
        super(components, width, height);
        this.components = components;
        inputs = new HashSet<>();
    }

    //private void

    private void addEventHandlers() {
        this.setOnKeyPressed(event -> {
            String keyName = event.getCode().toString();
            inputs.add(keyName);
        });

        this.setOnKeyReleased(event -> {
            String keyName = event.getCode().toString();
            inputs.remove(keyName);
        });
    }



}
