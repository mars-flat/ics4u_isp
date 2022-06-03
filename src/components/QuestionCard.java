package components;

import javafx.scene.text.Text;
import utilities.Constants;
import utilities.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionCard extends Popup {

    /**
     * current todos:
     *
     * shuffle deck
     * Display card & text within it
     * make clicking work -> map to associated dialogue
     * Deal with the correct/wrong answer gui & functionality
     *
     */

    private String question;
    private String[] options;
    private List<Integer> order;

    private boolean flipped;
    private int correctAnswer;

    public QuestionCard(String question, String[] options, int correctIndex, PopupChangeRequest changeRequestHandler) {
        super(changeRequestHandler);
        this.question = question;

        if (options.length != 4)
            throw new RuntimeException("Options array size invalid (must be 3)");
        if (correctIndex < 0 || correctIndex > 3)
            throw new RuntimeException("Correct index is invalid (must be from 0-3)");

        this.options = options;

        order = new ArrayList<>(Arrays.asList(0,1,2,3));
        Collections.shuffle(order);
        for (int i = 0; i < 3; ++i) {
            if (correctIndex == order.get(i)) {
                correctAnswer = i;
                break;
            }
        }

        flipped = false;
        addComponents();
    }

    private void addComponents() {

        Text questionText = new Text(360, 200, question);
        questionText.setFont(Tools.getCustomFont(Constants.PRINT_FONT, 20));
        questionText.setWrappingWidth(240);
        this.getChildren().add(questionText);

        for (int i = 0; i < 4; ++i) {
            int idx = order.get(i);
            Text answerText = new Text(230 + 135 * i, 400, options[idx]);
            answerText.setFont(Tools.getCustomFont(Constants.PRINT_FONT, 12));
            answerText.setWrappingWidth(80);
            this.getChildren().add(answerText);
        }
    }


}
