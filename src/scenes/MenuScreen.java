package scenes;

import components.MenuScreenComponents;
import javafx.animation.FillTransition;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.Constants;
import utilities.GameHandler;

import java.io.File;
import java.util.HashSet;

public class MenuScreen extends GameScreen {

    private final static File MAIN_MENU_SCREEN = new File("C:\\Users\\shane\\IdeaProjects\\ics4u_isp\\src\\data\\mainmenu.png");

    private MenuScreenComponents components;
    private Point2D lastClick;

    public MenuScreen(double width, double height, GameHandler controller) {
        super(new MenuScreenComponents(), width, height, controller);
        components = (MenuScreenComponents) super.getRoot();

        this.addEventHandlers();
        this.onLoad();
    }

    private void addEventHandlers() {
        this.setOnMouseClicked(event -> {
            lastClick = new Point2D(event.getX(), event.getY());
            //System.out.println(lastClick.getX() + " " + lastClick.getY());
        });
    }

    private void onLoad() {
        components.changeBackground(MAIN_MENU_SCREEN);
        transitionIn();
    }


    @Override
    public void onTick(long currentTick) {

    }

    @Override
    public void transitionIn() {
        FillTransition ft = new FillTransition(
                Duration.millis(1000),
                components.getTransitionRectangle(),
                Color.BLACK, Color.TRANSPARENT);

        ft.play();
    }

    @Override
    public void transitionOut() {

    }

    public void nextScene(int choice) {
        switch (choice) {
            case 1:
                // play
                break;
            case 2:
                // about
                break;
            case 3:
                controller.closeProgram();
                break;
        }
    }
}
