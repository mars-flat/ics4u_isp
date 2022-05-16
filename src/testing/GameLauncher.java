package testing;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;

public class GameLauncher extends Application {

    Scene scene;

    private final static int SCREEN_WIDTH = 960;
    private final static int SCREEN_HEIGHT = 720;

    private final static String URL
            = "https://i.ytimg.com/vi/K0qmNIZgbLM/maxresdefault.jpg";
    private HashSet<String> inputs = new HashSet<>();
    private Pane root;
    private AnimationTimer timer;
    private Entity plr;

    private void movePlayer() {
        if (inputs.contains("A")) plr.moveLeft();
        if (inputs.contains("D")) plr.moveRight();
        if (inputs.contains("W")) plr.moveUp();
        if (inputs.contains("S")) plr.moveDown();
        if (inputs.contains("M")) {
            timer.stop();
        }
    }

    private void gameLoop() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
            }
        };
        timer.start();
    }

    private void loadEntities(Pane root) {

        Entity wall = new Entity(200, 200, 500, 50, Color.ORANGE);

        root.getChildren().add(wall);

        plr = new Entity(100,100,50,50, Color.BLUEVIOLET);

        root.getChildren().add(plr);

    }

    private Parent setup() {
        root = new Pane();

        BackgroundImage myBI = new BackgroundImage(
                new Image(URL, SCREEN_WIDTH, SCREEN_HEIGHT, false, true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(myBI));

        loadEntities(root);

        return root;
    }

    private void addEventHandlers(Scene curScene) {
        curScene.setOnKeyPressed(event -> {
            String keyName = event.getCode().toString();
            inputs.add(keyName);
        });

        curScene.setOnKeyReleased(event -> {
            String keyName = event.getCode().toString();
            inputs.remove(keyName);
        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application");
        primaryStage.setResizable(false);

        scene = new Scene(setup(), 960, 720);



        gameLoop();
        addEventHandlers(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
