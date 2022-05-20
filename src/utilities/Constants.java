package utilities;

import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Constants {
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 720;

    public static FileInputStream FONT_INPUT_STREAM;

    static {
        try {
            FONT_INPUT_STREAM = new FileInputStream(
                    "C:\\Users\\shane\\IdeaProjects\\ics4u_isp\\src\\data\\Minecraft.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
