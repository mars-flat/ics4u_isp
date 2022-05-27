package utilities;

import java.io.File;

/**
 * This class contains all the constants for the application.
 * <p>
 * This method was created for easy access to files and
 * essential, frequently-used constants throughout
 * the program, such as screen dimensions.
 *
 * @author Annie Wong
 * @since 1.0, 5/17/2022
 */
public class Constants {

    /**
     * The width of the application screen.
     */
    public static final int SCREEN_WIDTH = 960;

    /**
     * The height of the application screen.
     */
    public static final int SCREEN_HEIGHT = 720;

    public static final String DATA_PATH = "src\\data\\";

    /**
     * Main menu background images.
     */
    public final static File MAIN_MENU_SCREEN_1 = new File(DATA_PATH + "mainmenu1.png");
    public final static File MAIN_MENU_SCREEN_2 = new File(DATA_PATH + "mainmenu2.png");
    public final static File MAIN_MENU_SCREEN_3 = new File(DATA_PATH + "mainmenu3.png");

    /**
     * Splash screen background image.
     */
    public final static File SPLASH_SCREEN = new File(DATA_PATH + "splashscreen.png");

    /**
     * Title/loading screen background image.
     */
    public final static File TITLE_SCREEN = new File(DATA_PATH + "titlescreen.png");

    /**
     * Bedroom image for the background of level 1.
     */
    public final static File BEDROOM_SCREEN = new File(DATA_PATH + "bedroom.png");

    /**
     * The primary font used throughout the game, Minecraft.
     */
    public final static File FONT_FILE = new File(DATA_PATH + "Minecraft.ttf");

    public static final File DIALOGUE_BOX = new File(DATA_PATH + "dialoguebox.png");

    public static final File CHARACTER_FRONT_IDLE = new File(DATA_PATH + "characterfrontidle.png");

    public static final File JOURNAL_BOX = new File(DATA_PATH + "journal.png");
}
