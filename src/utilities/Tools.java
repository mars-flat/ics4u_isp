package utilities;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.*;

public class Tools {
    public static Font getCustomFont(File f, int size) {
        try {
            return Font.loadFont(new FileInputStream(f), size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image getImage(File f,
                                 int requestedWidth,
                                 int requestedHeight,
                                 boolean preserveRatio,
                                 boolean smooth) {
        try {
            return new Image(new FileInputStream(f),
                    requestedWidth,
                    requestedHeight,
                    preserveRatio,
                    smooth);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
