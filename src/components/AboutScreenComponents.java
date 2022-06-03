package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scenes.AboutScreen;
import scenes.MenuScreen;
import utilities.Constants;

public class AboutScreenComponents extends ScreenComponent {

    private Rectangle transitionRectangle;

    public AboutScreenComponents() {
        super();
        addComponents();
    }

    @Override
    public void addComponents() {
        Rectangle arrowButton = new Rectangle(25, 20, 115, 80);
        arrowButton.setFill(Color.TRANSPARENT);
        arrowButton.setOnMouseClicked(event -> {
            ((AboutScreen) this.getScene()).nextScene();
        });

        this.getChildren().addAll(arrowButton);
    }

    public Rectangle getTransitionRectangle() {
        return transitionRectangle;
    }
}
