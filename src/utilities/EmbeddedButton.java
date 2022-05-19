package utilities;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EmbeddedButton extends Rectangle {
    public EmbeddedButton(int spawnX, int spawnY, int width, int height, Color color) {
        super(width, height, color);
        setTranslateX(spawnX);
        setTranslateY(spawnY);
    }

    public boolean mouseInRange(Point2D clickLocation) {
        return super.contains(clickLocation);
    }
}
