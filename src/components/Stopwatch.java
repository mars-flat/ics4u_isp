package components;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

/**
 * Stopwatch component.
 *
 * @since 4.5, 6/14/2022
 * @author Shane Chen
 */
public class Stopwatch extends Group {

    /**
     * The current time.
     */
    private long currentTime;

    /**
     * Whether this stopwatch has stopped.
     */
    private boolean stopped;

    /**
     * The display text.
     */
    private Text display;

    /**
     * Instantiates this class.
     */
    public Stopwatch() {
        currentTime = 0;
        stopped = false;
        display = new Text(450, 50, toString());
        display.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 32));
        display.setFill(Color.WHITE);
        this.getChildren().add(display);
    }

    /**
     * Increments the stopwatch.
     */
    public void increment() {
        if (!stopped) {
            ++currentTime;
            display.setText(toString());
        }
    }

    /**
     * Stops the stopwatch.
     */
    public void stop() {
        stopped = true;
    }

    /**
     * Get the display text.
     * @return
     * The display text.
     */
    public Text getDisplay() {
        return display;
    }

    /**
     * Returns a formatted string of minutes and seconds, clock-form.
     * @return
     * Minutes:Seconds in ##:## format
     */
    public String toString() {
        long seconds = currentTime % 60;
        long minutes = currentTime / 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
