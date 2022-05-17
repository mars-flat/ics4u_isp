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

    Stage window;
    Scene scene, scene2;

    private final static int SCREEN_WIDTH = 970;
    private final static int SCREEN_HEIGHT = 720;

    private final static String URL
            = "https://i.ytimg.com/vi/K0qmNIZgbLM/maxresdefault.jpg";
    private HashSet<String> inputs = new HashSet<>();
    private AnimationTimer timer;
    private Entity plr;

    private int curcol = 0;
    Color[] cols = {Color.WHITE, Color.BLACK};

    private void movePlayer(boolean change) {
        if (inputs.contains("A")) {
            plr.moveLeft();
            if (change) {
                plr.setColor(cols[++curcol % 2]);
                change = false;
            }
        }
        if (inputs.contains("D")) {
            plr.moveRight();
            if (change) {
                plr.setColor(cols[++curcol % 2]);
                change = false;
            }
        }
        if (inputs.contains("W")) {
            plr.moveUp();
            if (change) {
                plr.setColor(cols[++curcol % 2]);
                change = false;
            }
        }
        if (inputs.contains("S")) {
            plr.moveDown();
            if (change) {
                plr.setColor(cols[++curcol % 2]);
                change = false;
            }
        }
        if (inputs.isEmpty()) plr.setColor(cols[1]);
        if (inputs.contains("M")) {
            timer.stop();
            window.setScene(scene2);
        }
    }

    private void gameLoop() {
        final int[] sus = {0};
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                sus[0]++;
                movePlayer((sus[0] % 15) == 0);
            }
        };
        timer.start();
    }

    private void loadEntities(Pane root) {

        plr = new Entity(100,100,50,50, Color.BLUEVIOLET);

        root.getChildren().add(plr);

        Entity wall = new Entity(200, 200, 500, 50, Color.ORANGE);

        root.getChildren().add(wall);

    }

    private Parent setup2() {
        Pane root = new Pane();
        return root;
    }

    private Parent setup() {
        Pane root = new Pane();

        BackgroundImage myBI = new BackgroundImage(
                new Image(URL, SCREEN_WIDTH, SCREEN_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT,
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
        window = primaryStage;
        primaryStage.setTitle("Application");
        primaryStage.setResizable(false);

        scene = new Scene(setup(), 960, 720);
        gameLoop();
        addEventHandlers(scene);

        primaryStage.setScene(scene);
        primaryStage.show();

        scene2 = new Scene(setup2(), 960, 720);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
