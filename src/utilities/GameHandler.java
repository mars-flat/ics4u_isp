/**
 * Name: Shane Chen, Annie Wong
 * Teacher: Ms. Krasteva
 * Date: May 16, 2022
 * Program Description: Everyone has their own fears that crawls and coils around their minds. But don't let
 * it control you. Panik is a game aimed to help teens raise awareness of/overcome the social adversity known as
 * social anxiety. Level one, the deficiencies room, educates about the symptoms of social anxiety and what
 * it's like. Level two, the panic room, involves a card game that focuses on what you should do to block
 * the effects of social anxiety. Level three, the final escape room level, is where everything comes together
 * as knowledge from the previous two levels are applied to get through a school day without letting anxiety
 * taking control. It will be up to you to guide the player throughout the experiences in school to overcome social
 * anxiety. Remember, the choices are yours.
 */

package utilities;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import scenes.*;

/**
 * The GameHandler class contains the main method,
 * which launches a JavaFX application.
 * <p>
 * This class handles the displaying and ticking
 * of the current scene with an AnimationTimer.
 * <p>
 * It also controls the main window, or stage, that
 * the scenes are displayed on.
 *
 * @author Shane Chen
 * @since 1.0, 5/16/2022
 */
public class GameHandler extends Application {

    /**
     * The main window of the application.
     */
    private Stage window;

    /**
     * The current scene.
     */
    private GameScreen currentScene;

    /**
     * The AnimationTimer used for handling
     * the game loop and ticking.
     */
    private AnimationTimer gameLoop;

    /**
     * Main method, launches the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        launch(args);
    }

    /**
     * Instantiates the AnimationTimer. Instantiation
     * requires the implementation of the abstract method
     * {@code handle(long now)}.
     * <p>
     * The implementation in the method
     * will call the current scene's
     * {@code onTick()} method, along with the
     * current tick count.
     */
    private void makeGameLoop() {

        // must be a final variable
        final int[] currentTick = {0};
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                // call the current scene's tick method
                // increment the current tick count
                currentScene.onTick(currentTick[0]++);
            }
        };
    }

    /**
     * Starts the game loop.
     */
    private void startGameLoop() {
        gameLoop.start();
    }

    /**
     * Ends the game loop. Should only be called when
     * the program exits.
     */
    private void stopGameLoop() {
        gameLoop.stop();
    }

    /**
     * Closes the program.
     */
    public void closeProgram() {
        stopGameLoop();
        window.close();
    }

    /**
     * Sets up the program by calling the methods
     * to create the game loop, as well as instantiate
     * the first scene, the {@link SplashScreen}.
     */
    private void setup() {
        makeGameLoop();
        startGameLoop();
        currentScene = new SplashScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, this);
        //currentScene = new LevelOneScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, this);
        //currentScene = new LevelTwoScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, this);
        //currentScene = new LevelThreeScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, this);
    }

    /**
     * Abstract method that must be implemented with a JavaFX application.
     * This method is called implicitly by the application.
     * <p>
     * This method will set the properties of the main window, and set up
     * the first scene and game loop.
     *
     * @param primaryStage The main window.
     * @throws Exception Handle any errors that may occur with the launch of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set stage properties
        window = primaryStage;
        window.setTitle("PANIK");
        window.setResizable(false);
        window.setOnCloseRequest(event -> closeProgram());

        // set up game loop and scene
        setup();
        changeScene(null, currentScene);
    }

    /**
     * Change the current scene being displayed.
     *
     * @param oldScene The old scene.
     * @param newScene The new scene to display.
     */
    public void changeScene(Scene oldScene, Scene newScene) {
        currentScene = (GameScreen) newScene;
        window.setScene(newScene);
        window.show();
    }
}
