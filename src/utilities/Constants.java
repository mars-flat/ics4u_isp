package utilities;

import java.io.File;

/**
 * This class contains all the constants for the application.
 * <p>
 * This method was created for easy access to files and
 * essential, frequently-used constants throughout
 * the program, such as screen dimensions.
 *
 * @since 1.0, 5/17/2022
 * Modification on 5/26/2022
 *
 * @author Shane Chen, Annie Wong
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

    /**
     * The data folder path.
     */
    public static final String DATA_PATH = "src\\data\\";

    /**
     * Splash screen background image.
     */
    public final static File SPLASH_SCREEN = new File(DATA_PATH + "splashscreen.png");

    /**
     * Title/loading screen background image.
     */
    public final static File TITLE_SCREEN = new File(DATA_PATH + "titlescreen.png");

    /**
     * Main menu background images.
     */
    public final static File MAIN_MENU_SCREEN_1 = new File(DATA_PATH + "mainmenu1.png");
    public final static File MAIN_MENU_SCREEN_2 = new File(DATA_PATH + "mainmenu2.png");
    public final static File MAIN_MENU_SCREEN_3 = new File(DATA_PATH + "mainmenu3.png");

    /**
     * About screen background image.
     */
    public final static File ABOUT_SCREEN = new File(DATA_PATH + "about.png");

    /**
     * Bedroom image for the background of level 1.
     */
    public final static File BEDROOM_SCREEN = new File(DATA_PATH + "bedroom.png");

    /**
     * The primary font used throughout the game, Minecraft.
     */
    public final static File FONT_FILE = new File(DATA_PATH + "Minecraft.ttf");

    /**
     * The image used for the dialogue box.
     */
    public static final File DIALOGUE_BOX = new File(DATA_PATH + "dialoguebox.png");

    /**
     * The image used for journal entries in level 1.
     */
    public static final File JOURNAL_BOX = new File(DATA_PATH + "journal.png");

    /**
     * The older sibling's image file path.
     */
    public static final File OLDER_SIBLING = new File(DATA_PATH + "oldersibling.png");

    /**
     * The younger sibling's image file path.
     */
    public static final File YOUNGER_SIBLING = new File(DATA_PATH + "youngersibling.png");

    /**
     * Journal icon colored to indicate that the journal is not found.
     */
    public static final File JOURNAL_ICON_1 = new File(DATA_PATH + "journalicon1.png");

    /**
     * Journal icon colored to indicate that the journal is found.
     */
    public static final File JOURNAL_ICON_2 = new File(DATA_PATH + "journalicon2.png");
}
