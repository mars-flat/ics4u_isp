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


    public static final File f = new File("./");
    /**
     * The data folder path.
     */
    public static final String DATA_PATH = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1) + "data\\";

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
     * Level select screen background images.
     */
    public final static File LEVEL_SELECT_SCREEN_1 = new File(DATA_PATH + "levelselect1.png");
    public final static File LEVEL_SELECT_SCREEN_2 = new File(DATA_PATH + "levelselect2.png");
    public final static File LEVEL_SELECT_SCREEN_3 = new File(DATA_PATH + "levelselect3.png");
    /**
     * Bedroom image for the background of level 1.
     */
    public final static File BEDROOM_SCREEN = new File(DATA_PATH + "bedroom.png");

    /**
     * Table image with cards for the background of level 2.
     */
    public static final File TABLE_SCREEN = new File(DATA_PATH + "table.png");

    /**
     * The primary font used throughout the game, Minecraft.
     */
    public final static File PIXEL_FONT = new File(DATA_PATH + "Minecraft.ttf");


    public static final File PRINT_FONT = new File(DATA_PATH + "Verdana.ttf");

    public static final File HANDWRITING_FONT = new File(DATA_PATH + "caveat.regular.ttf");

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

    public static final File BAD_CARD_DOWN = new File(DATA_PATH + "badcarddown.png");

    public static final File GOOD_CARD_DOWN = new File(DATA_PATH + "goodcarddown.png");

    public static final File LIBRARIAN = new File(DATA_PATH + "librarian.png");

    public static final File TEACHER = new File(DATA_PATH + "teacher.png");

    public static final File CASHIER = new File(DATA_PATH + "cashier.png");

    public static final File CHECKBOX = new File(DATA_PATH + "checkbox.png");

    public static final File CHECKMARK = new File(DATA_PATH + "checkmark.png");

    public static final File LOGO = new File(DATA_PATH + "logo.png");
}
