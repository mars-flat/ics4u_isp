package utilities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is the blueprint for movable
 * in-game objects such as the player, as well as those that interact with it.
 *
 * @since 1.0, 5/16/2022
 * @author Shane Chen
 */
public class Entity extends Rectangle {

    /**
     * The entity's movement speed. Do not change.
     */
    private static final int movementSpeed = 2;

    /**
     * Overloaded {@link Entity} class constructor. Defaults {@code mouseInteract}
     * to {@code False}.
     */
    public Entity(int spawnX, int spawnY, int width, int height, Color color) {
        this(spawnX, spawnY, width, height, color, false);
    }

    /**
     * Creates an {@link Entity} instance.
     * Initially displays the entity at ({@code spawnX}, {@code spawnY}).
     *
     * Because the instance is a {@link Rectangle} the width, height,
     * and color are also specified.
     *
     * @param spawnX
     * The x-coordinate where the entity should initially be displayed.
     *
     * @param spawnY
     * The y-coordinate where the entity should initially be displayed.
     *
     * @param width
     * The width of the rectangle bounding the entity.
     *
     * @param height
     * The height of the rectangle bounding the entity.
     *
     * @param color
     * The color of the rectangle bounding the entity.
     *
     * @param mouseInteract
     * Whether mouse actions should be handled by the entity.
     *
     */
    public Entity(int spawnX, int spawnY, int width, int height, Color color, boolean mouseInteract) {
        super(width, height, color);
        setX(spawnX);
        setY(spawnY);
        if (!mouseInteract) {
            this.setPickOnBounds(false);
            this.setMouseTransparent(true);
        }
    }

    /**
     * Moves the entity left by {@link Entity#movementSpeed} pixels.
     */
    public void moveLeft() {
        setX(getX() - movementSpeed);
    }

    /**
     * Moves the entity right by {@link Entity#movementSpeed} pixels.
     */
    public void moveRight() {
        setX(getX() + movementSpeed);
    }

    /**
     * Moves the entity up by {@link Entity#movementSpeed} pixels.
     */
    public void moveUp() {
        setY(getY() - movementSpeed);
    }

    /**
     * Moves the entity down by {@link Entity#movementSpeed} pixels.
     */
    public void moveDown() {
        setY(getY() + movementSpeed);
    }

    /**
     * Returns whether this entity is colliding with another entity
     * on this entity's left boundary.
     *
     * @param other
     * The other entity.
     *
     * @return
     * Whether this entity is colliding with another entity
     * on this entity's left boundary.
     */
    public boolean isCollidingLeft(Entity other) {

        // deal with Y
        if (getY() + getHeight() <= other.getY()) return false;
        if (getY() >= other.getY() + other.getHeight()) return false;

        // deal with X
        double otherLeftX = other.getX();
        double otherRightX = otherLeftX + other.getWidth();
        double thisLeftX = getX();
        double thisRightX = thisLeftX+ getWidth();

        return (thisLeftX == otherRightX);
    }

    /**
     * Returns whether this entity is colliding with another entity
     * on this entity's right boundary.
     *
     * @param other
     * The other entity.
     *
     * @return
     * Whether this entity is colliding with another entity
     * on this entity's right boundary.
     */
    public boolean isCollidingRight(Entity other) {

        // deal with Y
        if (getY() + getHeight() <= other.getY()) return false;
        if (getY() >= other.getY() + other.getHeight()) return false;

        // deal with X
        double otherLeftX = other.getX();
        double otherRightX = otherLeftX + other.getWidth();
        double thisLeftX = getX();
        double thisRightX = thisLeftX+ getWidth();

        return (thisRightX == otherLeftX);
    }

    /**
     * Returns whether this entity is colliding with another entity
     * on this entity's top boundary.
     *
     * @param other
     * The other entity.
     *
     * @return
     * Whether this entity is colliding with another entity
     * on this entity's top boundary.
     */
    public boolean isCollidingUp(Entity other) {

        // deal with X
        if (getX() + getWidth() <= other.getX()) return false;
        if (getX() >= other.getX() + other.getWidth()) return false;

        // deal with Y
        double otherTopY = other.getY();
        double otherBottomY = otherTopY + other.getHeight();
        double thisTopY = getY();
        double thisBottomY = thisTopY + getHeight();

        return (thisTopY == otherBottomY);
    }

    /**
     * Returns whether this entity is colliding with another entity
     * on this entity's bottom boundary.
     *
     * @param other
     * The other entity.
     *
     * @return
     * Whether this entity is colliding with another entity
     * on this entity's bottom boundary.
     */
    public boolean isCollidingDown(Entity other) {

        // deal with X
        if (getX() + getWidth() <= other.getX()) return false;
        if (getX() >= other.getX() + other.getWidth()) return false;

        // deal with Y
        double otherTopY = other.getY();
        double otherBottomY = otherTopY + other.getHeight();
        double thisTopY = getY();
        double thisBottomY = thisTopY + getHeight();

        return (thisBottomY == otherTopY);
    }

    /**
     * Returns whether this entity is in the vicinity of another entity.
     * The range, the radius of the circle considered to be the vicinity,
     * should be specified.
     *
     * Note that the distance calculation is done with the top-left corner of
     * both entities.
     *
     * @param other
     * The other entity.
     *
     * @param range
     * The radius of the circle considered to be the vicinity.
     *
     * @return
     * Whether the entity is in the vicinity of the other entity.
     */
    public boolean inVicinity(Entity other, double range) {
        double tx = (getX() + getWidth()) / 2;
        double ty = (getY() + getHeight()) / 2;
        double ox = (other.getX() + other.getWidth()) / 2;
        double oy = (other.getY() + other.getHeight()) / 2;
        return range * range > (tx - ox) * (tx - ox) + (ty - oy) * (ty - oy);
    }
}
