package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import utilities.Constants;
import utilities.Tools;

import java.io.File;

/**
 * Anxiety bar component for use in level 3.
 *
 * @since 4.2, 6/8/2022
 * @author Shane Chen
 */
public class AnxietyBar extends Group {

    /**
     * The current anxiety level in range [0, 3].
     */
    private int anxiety;

    /**
     * Images used for respective anxiety levels.
     */
    ImageView[] bars = new ImageView[4];

    /**
     * Instantiates this class.
     *
     * Utilizes file functionality, may result in issues if paths are ill-configured.
     */
    public AnxietyBar() {
        anxiety = 0;
        for (int i = 0; i < 4; ++i) {
            bars[i] = new ImageView(Tools.getImage(
                    new File(Constants.DATA_PATH + "bars\\bar" + (i+1) + ".png"), 300, 60, true, true));
            bars[i].setX(775);
            bars[i].setY(30);
            bars[i].setVisible(false);
            getChildren().add(bars[i]);
        }
        bars[anxiety].setVisible(true);
    }

    /**
     * increases the anxiety level. Does not change if anxiety level is 3.
     */
    public void incrementAnxiety() {
        bars[anxiety].setVisible(false);
        anxiety = Math.min(3, anxiety+1);
        bars[anxiety].setVisible(true);
    }

    /**
     * Gets the current anxiety level.
     *
     * @return
     * The current anxiety level.
     */
    public int getAnxiety() {
        return anxiety;
    }

    /**
     * Returns whether the current anxiety level is 3.
     *
     * @return
     * Whether the current anxiety level is 3.
     */
    public boolean panic() {
        return anxiety == 3;
    }
}
