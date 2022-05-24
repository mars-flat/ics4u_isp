package utilities;

import java.io.File;

/**
 * This class contains all the constants for the application.
 *
 * This method was created for easy access to files and
 * essential, frequently-used constants throughout
 * the program, such as screen dimensions.
 *
 * @since 1.0, 5/17/2022
 * @author Annie Wong
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
     * Main menu background images.
     */
    public final static File MAIN_MENU_SCREEN_1 = new File("src\\data\\mainmenu1.png");
    public final static File MAIN_MENU_SCREEN_2 = new File("src\\data\\mainmenu2.png");
    public final static File MAIN_MENU_SCREEN_3 = new File("src\\data\\mainmenu3.png");

    /**
     * Splash screen background image.
     */
    public final static File SPLASH_SCREEN = new File("src\\data\\splashscreen.png");

    /**
     * Title/loading screen background image.
     */
    public final static File TITLE_SCREEN = new File("src\\data\\titlescreen.png");

    /**
     * Bedroom image for the background of level 1.
     */
    public final static File BEDROOM_SCREEN = new File("src\\data\\bedroom.png");

    /**
     * The primary font used throughout the game, Minecraft.
     */
    public final static File FONT_FILE = new File("src\\data\\Minecraft.ttf");
}
