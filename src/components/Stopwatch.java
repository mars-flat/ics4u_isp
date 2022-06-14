package components;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

public class Stopwatch extends Group {

    private long currentTime;
    private boolean stopped;
    private Text display;

    public Stopwatch() {
        currentTime = 0;
        stopped = false;
        display = new Text(450, 50, toString());
        display.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 32));
        display.setFill(Color.WHITE);
        this.getChildren().add(display);
    }
    public void increment() {
        if (!stopped) {
            ++currentTime;
            display.setText(toString());
        }
    }
    public void stop() {
        stopped = true;
    }
    public Text getDisplay() {
        return display;
    }
    public String toString() {
        long seconds = currentTime % 60;
        long minutes = currentTime / 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
