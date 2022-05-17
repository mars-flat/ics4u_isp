package testing;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class MainMenuComponents extends Parent {

    private Pane root;

    public MainMenuComponents() {
        root = new Pane();
        addComponents();
    }

    private void addComponents() {

    }

    public Parent getMainMenuComponents() {
        return root;
    }
}
