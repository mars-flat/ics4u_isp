package utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;

/**
 * This class is the player, which extends the {@link Entity} class.
 *
 * Additional attributes and behavior include the character, which is the player's image
 * displayed on the screen. This is updated according to whether the player is currently
 * moving and the direction of movement.
 *
 * Directions
 * Left: 1
 * Right: 4
 * Up: 7
 * Down: 10
 *
 * @since 2.2, 5/25/2022
 * @author Shane Chen, Annie Wong
 */
public class Player extends Entity {

    /**
     * The characters stances. In other words, the possible ways that the player
     * can be displayed on the screen.
     */
    private Image[] characterStances; // 4 x [a, b, idle] in L R U D order

    /**
     * The current image that is depicting the character.
     */
    private final ImageView character;

    /**
     * The current direction the character is facing.
     */
    private int currentDirection;

    /**
     * Variable to help with alternating the two moving stances for each direction.
     */
    private int cur;

    /**
     * Creates a {@link Player} instance. All attributes from {@link Entity} will be inherited.
     *
     * @param spawnX
     * The x-coordinate where the player should initially be at.
     *
     * @param spawnY
     * The y-coordinate where the player should initially be at.
     *
     * @param width
     * The width of the rectangle bounding the player's hitbox.
     *
     * @param height
     * The height of the rectangle bounding the player's hitbox.
     *
     */
    public Player(int spawnX, int spawnY, int width, int height, int startingDirection, int offsetR, int offsetB, boolean older) {
        super(spawnX, spawnY, width, height, Color.TRANSPARENT);

        // load stances from data
        loadStances(older, offsetR, offsetB);

        // set initial character stance
        character = new ImageView(characterStances[9]);
        character.setPickOnBounds(false);
        character.setMouseTransparent(true);
        currentDirection = startingDirection;

        // ensures that the character retains the x and y coordinates of the player
        character.xProperty().bind(xProperty());
        character.yProperty().bind(yProperty());
    }

    /**
     * Loads all stances from data and stores them for access.
     */
    private void loadStances(boolean older, int offsetR, int offsetB) {
        characterStances = new Image[13];
        for (int i = 1; i <= 12; ++i) {
            characterStances[i] = Tools.getImage(new File(Constants.DATA_PATH + "stances\\" + (older ? "older" : "younger") + i + ".png"),
                    (int) getWidth() + offsetR, (int) getHeight() + offsetB, true, true);
        }
    }

    /**
     * Calls the superclass {@code moveLeft()} method.
     * Sets the current direction to 1 (left).
     */
    public void moveLeft() {
        super.moveLeft();
        currentDirection = 1;
    }

    /**
     * Calls the superclass {@code moveRight()} method.
     * Sets the current direction to 4 (right).
     */
    public void moveRight() {
        super.moveRight();
        currentDirection = 4;
    }

    /**
     * Calls the superclass {@code moveUp()} method.
     * Sets the current direction to 7 (up).
     */
    public void moveUp() {
        super.moveUp();
        currentDirection = 7;
    }

    /**
     * Calls the superclass {@code moveDown()} method.
     * Sets the current direction to 10 (down).
     */
    public void moveDown() {
        super.moveDown();
        currentDirection = 10;
    }

    /**
     * Updates the character's stance based on whether it is walking and the current
     * direction the player is facing.
     *
     * @param isWalking
     * Whether the player is walking.
     * @param currentTick
     * The current tick, used to determine whether or not to change the stance while walking.
     */
    public void update(boolean isWalking, long currentTick) {
        // change every 15 ticks
        boolean change = (currentTick % 15 == 0);

        // update character
        if (isWalking) {
            if (change) cur = (cur + 1) % 2;
            character.setImage(characterStances[cur + currentDirection]);
        } else {
            character.setImage(characterStances[currentDirection + 2]);
        }
    }

    /**
     * Returns the current character stance.
     *
     * @return
     * The current character stance image.
     */
    public ImageView getCharacter() {
        return character;
    }
}
