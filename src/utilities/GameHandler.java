package utilities;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.GameScreen;
import scenes.SplashScreen;

public class GameHandler extends Application {

    private Stage window;
    private GameScreen currentScene;
    private AnimationTimer gameLoop;

    private void makeGameLoop() {
        final int[] currentTick = {0};
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                currentScene.onTick(currentTick[0]++);
            }
        };
    }

    private void setup() {
        makeGameLoop();
        startGameLoop();
        currentScene = new SplashScreen(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, this);
    }

    private void startGameLoop() {
        gameLoop.start();
    }

    private void stopGameLoop() {
        gameLoop.stop();
    }

    public void closeProgram() {
        stopGameLoop();
        window.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setup();

        window = primaryStage;
        window.setTitle("PANIK");
        window.setResizable(false);
        window.setOnCloseRequest(event -> closeProgram());

        changeScene(null, currentScene);
    }

    // do not handle transitions
    public void changeScene(Scene oldScene, Scene newScene) {
        currentScene = (GameScreen) newScene;
        window.setScene(newScene);
        window.show();
    }
}
