package scenes;

import components.AboutScreenComponents;
import javafx.scene.Parent;
import utilities.Constants;
import utilities.GameHandler;

public class AboutScreen extends GameScreen {

    private AboutScreenComponents components;

    /**
     * Creates an instance of this class.
     *
     * @param root       JavaFX node root. Known as components in this project.
     * @param width      Width of the window. Should conform to the constants in {@link Constants}
     * @param height     Height of the window. Should conform to the constants in {@link Constants}
     * @param controller {@link GameHandler} instance.
     */
    public AboutScreen(Parent root, double width, double height, GameHandler controller) {
        super(root, width, height, controller);
        components = (AboutScreenComponents) super.getRoot();
        this.onLoad();
    }

    private void onLoad() {

    }

    @Override
    public void onTick(long currentTick) {

    }

    @Override
    public void transitionIn() {

    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void nextScene() {

    }
}
