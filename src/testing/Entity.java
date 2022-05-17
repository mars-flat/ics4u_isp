package testing;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Entity extends Rectangle {
    private final int movementSpeed = 2;

    public Entity(int spawnX, int spawnY, int width, int height, Color color) {
        super(width, height, color);
        setTranslateX(spawnX);
        setTranslateY(spawnY);
    }

    public void setColor(Color c) {
        super.setFill(c);
    }

    public void moveLeft() {
        setTranslateX(getTranslateX() - movementSpeed);
    }

    public void moveRight() {
        setTranslateX(getTranslateX() + movementSpeed);
    }

    public void moveUp() {
        setTranslateY(getTranslateY() - movementSpeed);
    }

    public void moveDown() {
        setTranslateY(getTranslateY() + movementSpeed);
    }

    public boolean inVicinity(Entity other, int range) {
        return range * range >=
                (getTranslateX() - other.getTranslateX()) * (getTranslateX() - other.getTranslateX()) +
                (getTranslateY() - other.getTranslateY()) * (getTranslateY() - other.getTranslateY());
    }


}
