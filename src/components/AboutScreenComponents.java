package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import scenes.AboutScreen;
import scenes.MenuScreen;
import utilities.Constants;
import utilities.Tools;

public class AboutScreenComponents extends ScreenComponent {

    private Rectangle transitionRectangle;

    public AboutScreenComponents() {
        super();
        addComponents();
    }

    @Override
    public void addComponents() {
        Rectangle arrowButton = new Rectangle(25, 20, 90, 70);
        arrowButton.setFill(Color.TRANSPARENT);
        arrowButton.setOnMouseClicked(event -> {
            ((AboutScreen) this.getScene()).nextScene();
        });

        Text generalDescrip = new Text(75, 120, "Everyone has their own fears that crawl and coil around their minds. But don't let\n" +
                "it control you. Panik is a game aimed to help teens raise awareness of/overcome\n" +
                "the social adversity known as social anxiety.");
        generalDescrip.setTextAlignment(TextAlignment.CENTER);
        generalDescrip.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 20));
        generalDescrip.setPickOnBounds(false);
        generalDescrip.setMouseTransparent(true);
        generalDescrip.setFill(Color.WHITE);
        generalDescrip.setVisible(true);

        Text level1Descrip = new Text(90, 283, "Level one, the deficiencies room, educates about\n" +
                "the symptoms of social anxiety and what it\'s\n" +
                "like.");
        level1Descrip.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 18));
        level1Descrip.setPickOnBounds(false);
        level1Descrip.setMouseTransparent(true);
        level1Descrip.setFill(Color.BLACK);
        level1Descrip.setVisible(true);

        Text level2Descrip = new Text(90, 445, "Level two, the panic room, involves a card game\n" +
                "that focuses on what you should do to block the\n" +
                "effects of social anxiety.");
        level2Descrip.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 18));
        level2Descrip.setPickOnBounds(false);
        level2Descrip.setMouseTransparent(true);
        level2Descrip.setFill(Color.BLACK);
        level2Descrip.setVisible(true);

        Text level3Descrip = new Text(90, 599, "Level three, the final escape room level, is where\n" +
                "everything comes together as knowledge from the\n" +
                "previous two levels are applied to get through a\n" +
                "school day without letting anxiety taking control.");
        level3Descrip.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 18));
        level3Descrip.setPickOnBounds(false);
        level3Descrip.setMouseTransparent(true);
        level3Descrip.setFill(Color.BLACK);
        level3Descrip.setVisible(true);

        this.getChildren().addAll(arrowButton, generalDescrip, level1Descrip, level2Descrip, level3Descrip);
    }

    public Rectangle getTransitionRectangle() {
        return transitionRectangle;
    }
}
