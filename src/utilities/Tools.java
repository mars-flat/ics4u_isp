package utilities;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.*;

/**
 * This class contains frequently-used {@code static} methods throughout
 * the program.
 *
 * @since 1.0, 5/17/2022
 * @author Annie Wong
 */
public class Tools {

    /**
     * Loads a custom font via a FileInputStream.
     * The font size is also specified.
     *
     * @param f
     * The file of the font.
     *
     * @param size
     * The size of the font to create.
     *
     * @return
     * The created Font instance.
     */
    public static Font getCustomFont(File f, int size) {
        try {
            return Font.loadFont(new FileInputStream(f), size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates an image via a FileInputStream.
     * Other parameters to construct the image are copied from the
     * {@link Image} class constructor.
     *
     * The purpose of this method is to eliminate adding excessive
     * exception throwing on class methods.
     *
     * @param f
     * The file of the image.
     *
     * @param requestedWidth
     * The image's bounding box width.
     *
     * @param requestedHeight
     * The image's bounding box height.
     *
     * @param preserveRatio
     * Indicates whether to preserve the aspect ratio of
     * the original image when scaling to fit the image within the
     * specified bounding box.
     *
     * @param smooth
     * Indicates whether to use a better quality filtering
     * algorithm or a faster one when scaling this image to fit within
     * the specified bounding box.
     *
     * @return
     * The created Image instance.
     */
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
