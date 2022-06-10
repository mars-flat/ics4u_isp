package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import utilities.Constants;
import utilities.Tools;

import java.io.File;

public class AnxietyBar extends Group {
    private int anxiety;

    ImageView[] bars = new ImageView[4];

    public AnxietyBar() {
        anxiety = 0;
        for (int i = 0; i < 4; ++i) {
            bars[i] = new ImageView(Tools.getImage(
                    new File(Constants.DATA_PATH + "bars\\bar" + (i+1) + ".png"), 300, 60, true, true));
            bars[i].setX(40);
            bars[i].setY(10);
            bars[i].setVisible(false);
            getChildren().add(bars[i]);
        }
        bars[anxiety].setVisible(true);
    }

    public void incrementAnxiety() {
        bars[anxiety].setVisible(false);
        anxiety = Math.min(3, anxiety+1);
        bars[anxiety].setVisible(true);
    }

    public int getAnxiety() {
        return anxiety;
    }

    public boolean panic() {
        return anxiety == 3;
    }
}
