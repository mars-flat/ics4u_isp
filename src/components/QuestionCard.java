package components;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utilities.Constants;
import utilities.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionCard extends Pane {

    /**
     * current todos:
     *
     * shuffle deck
     * Display card & text within it
     * make clicking work -> map to associated dialogue
     * Deal with the correct/wrong answer gui & functionality
     *
     */

    private LevelTwoComponents controller;

    private String question;
    private String[] options;
    private String[] responses;
    private List<Integer> order;

    private Group[] cardsUp;
    private Group[] cardsDown;

    private boolean down;
    private int correctAnswer;

    public QuestionCard(String question, String[] options, String[] responses, int correctIndex, LevelTwoComponents parent) {
        controller = parent;
        this.question = question;
        if (options.length != 4 || responses.length != 4)
            throw new RuntimeException("Parameter array size invalid (must be 3)");
        if (correctIndex < 0 || correctIndex > 3)
            throw new RuntimeException("Correct index is invalid (must be from 0-3)");

        this.options = options;
        this.responses = responses;

        order = new ArrayList<>(Arrays.asList(0,1,2,3));
        Collections.shuffle(order);
        for (int i = 0; i < 4; ++i) {
            if (correctIndex == order.get(i)) {
                correctAnswer = i;
                break;
            }
        }

        down = false;
        addComponents();
    }

    private void addComponents() {

        cardsUp = new Group[4];
        cardsDown = new Group[4];

        Text questionText = new Text(360, 200, question);
        questionText.setFont(Tools.getCustomFont(Constants.PRINT_FONT, 20));
        questionText.setWrappingWidth(240);
        this.getChildren().add(questionText);

        for (int i = 0; i < 4; ++i) {
            int idx = order.get(i);

            Rectangle clickBox = new Rectangle(230 + 135 * i, 370, 80, 120);
            clickBox.setFill(Color.GRAY);
            clickBox.setOnMouseClicked(event -> {
                flip();
                DialoguePopup response = new DialoguePopup(
                        new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 240, 280, true, true)),
                        "Older Sibling", responses[idx], () -> controller.setActivePopup(null)
                );

                controller.setActivePopup(response);
            });

            Text answerText = new Text(230 + 135 * i, 400, options[idx]);
            answerText.setFont(Tools.getCustomFont(Constants.PRINT_FONT, 12));
            answerText.setWrappingWidth(80);
            answerText.setTextAlignment(TextAlignment.CENTER);
            answerText.setPickOnBounds(false);
            answerText.setMouseTransparent(true);

            cardsUp[idx] = new Group(clickBox, answerText);

            ImageView downCardImage = new ImageView(Tools.getImage((correctAnswer == i ? Constants.GOOD_CARD_DOWN : Constants.BAD_CARD_DOWN), 104, 140, true, true));
            downCardImage.setX(220 + 135 * i);
            downCardImage.setY(360);

            cardsDown[idx] = new Group(downCardImage);

        }

        this.getChildren().addAll(cardsUp);

    }

    public void flip() {
        if (down) {
            // nothing for now
        } else {
            for (Group g : cardsUp) this.getChildren().remove(g);
            for (Group g : cardsDown) this.getChildren().add(g);
        }
        down = true;
    }
}
