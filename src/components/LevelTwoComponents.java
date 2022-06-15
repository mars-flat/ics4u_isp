package components;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import scenes.LevelTwoScreen;
import utilities.Constants;
import utilities.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Components for the second level of the game.
 *
 * @author Shane Chen
 * @since 3.0, 5/30/2022
 */
public class LevelTwoComponents extends ScreenComponent {

    /**
     * The total number of questions.
     */
    public final static int TOTAL_QUESTIONS = 6;

    /**
     * The current active popup. May be {@code null} to indicate that there is no popup.
     */
    private Popup activePopup;

    /**
     * The current question. May be {@code null} to indicate that there is no question.
     */
    private QuestionCard currentQuestion;

    /**
     * The dialogue popups which may be displayed.
     */
    private DialoguePopup[] levelTwoDialogue;

    /**
     * The question cards which may be displayed.
     */
    private QuestionCard[] questions;

    /**
     * The current question index.
     */
    private int curQuestionNum;

    /**
     * Creates an instance of this class.
     */
    public LevelTwoComponents() {
        super();
        curQuestionNum = 0;
        addComponents();
    }

    /**
     * Set up the dialogue.
     */
    private void setupDialogue() {

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "What did you call me for?",
                "I want us to play a game. I have these cards with questions and answers on them, and you pick what you think is the best answer.",
                "Hm. Thanks for the advice, I guess?",
                "Trust me. If you do the right things, you'll get over it.",
                "I've experienced social anxiety myself. These cards helped me a ton. Listen, none of your fears are real. Get out there and try!",
                "*Convinced* I- You didn't tell me?",
                "For the same reason you didn't tell me.",
                "Well then, thanks. I guess.",
        };

        levelTwoDialogue = new DialoguePopup[8];
        levelTwoDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.YOUNGER_SIBLING, 240, 280, true, true)),
                "Younger Sibling", dialogue[0], () ->
        {
            this.setActivePopup(levelTwoDialogue[1]);
        });
        levelTwoDialogue[1] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 240, 280, true, true)),
                "Older Sibling", dialogue[1], () ->
        {
            this.setActivePopup(null);
            this.setCurrentQuestion(questions[0]);
        });
        for (int i = 2; i <= 7; ++i) {
            int finalI = i;
            levelTwoDialogue[i] = new DialoguePopup(
                    new ImageView(Tools.getImage(
                            (i == 2 || i == 5 || i == 7) ? Constants.YOUNGER_SIBLING : Constants.OLDER_SIBLING, 240, 280, true, true)),
                    (i == 2 || i == 5 || i == 7) ? "Younger Sibling" : "Older Sibling", dialogue[i], () -> {
                this.setActivePopup(finalI == 7 ? null : levelTwoDialogue[finalI + 1]);
            });
        }


        //level ending popup
        Popup ending = new Popup(() -> ((LevelTwoScreen) this.getScene()).nextScene());
        levelTwoDialogue[7].setOnChangeRequest(() -> this.setActivePopup(ending));
        Rectangle bg = new Rectangle(0, 0, 960, 720);
        bg.setFill(Color.BLACK);
        Text complete = new Text(250, 320, "Level 2 Completed.");
        complete.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        complete.setFill(Color.WHITE);

        Text score = new Text(300, 400, "");
        score.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        score.setFill(Color.WHITE);

        Text ret = new Text(110, 460, "SPACE to return to level select.");
        ret.setFont(Tools.getCustomFont(Constants.PIXEL_FONT, 48));
        ret.setFill(Color.WHITE);
        ending.getChildren().addAll(bg, complete, score, ret);

        levelTwoDialogue[6].setOnChangeRequest(() -> {
            score.setText("Score: " + getNumCorrect() + "/6");
            this.setActivePopup(ending);
        });
    }

    /**
     * Sets up the questions. Question content is located in a text file
     * which is read with a {@link Scanner}.
     */
    private void setupQuestions() {
        Scanner fr = null;
        try {
            fr = new Scanner(new File(Constants.DATA_PATH + "cardcontent.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        questions = new QuestionCard[TOTAL_QUESTIONS];

        // each question card has 10 lines in the txt file
        for (int i = 0; i < TOTAL_QUESTIONS; ++i) {
            String question = fr.nextLine();
            String[] answers = new String[4];
            String[] responses = new String[4];
            for (int j = 0; j < 4; ++j) {
                answers[j] = fr.nextLine();
                responses[j] = fr.nextLine();
            }
            int correctAnswer = Integer.parseInt(fr.nextLine());
            questions[i] = new QuestionCard(question, answers, responses, correctAnswer, this);
        }

        fr.close();
    }

    /**
     * Add components to this root component.
     */
    @Override
    public void addComponents() {
        // TODO: cutscene

        setupDialogue();
        setupQuestions();

        PauseTransition pt = new PauseTransition(Duration.millis(500));
        pt.setOnFinished(event -> setActivePopup(levelTwoDialogue[0]));
        pt.play();
    }

    /**
     * Get the current active popup.
     *
     * @return The current active popup, or {@code null} if there is none.
     */
    public Popup getActivePopup() {
        return activePopup;
    }

    /**
     * Sets the active popup. May be {@code null} to indicate that there is no active popup.
     *
     * @param newPopup The new active popup, or {@code null} if there will be no active popup.
     */
    public void setActivePopup(Popup newPopup) {
        if (activePopup != null) this.getChildren().remove(activePopup);
        activePopup = newPopup;
        if (activePopup != null) this.getChildren().add(activePopup);
    }

    /**
     * Goes to the next question, or {@code null} if there is no question after.
     */
    public void nextQuestion() {

        if (curQuestionNum == TOTAL_QUESTIONS - 1) {
            this.setCurrentQuestion(null);
            this.setActivePopup(levelTwoDialogue[2]);
        } else this.setCurrentQuestion(questions[++curQuestionNum]);
    }

    /**
     * Sets the current quesiton. May be {@code null} to indicate to have no question.
     *
     * @param newQuestion The new question, or {@code null} if there will be no question.
     */
    public void setCurrentQuestion(QuestionCard newQuestion) {
        if (currentQuestion != null) this.getChildren().remove(currentQuestion);
        currentQuestion = newQuestion;
        if (newQuestion != null) this.getChildren().add(newQuestion);
    }

    /**
     * Returns the number of correct (first try) answers.
     * @return
     * The number of correct answers.
     */
    public int getNumCorrect() {
        int numCorrect = 0;
        for (QuestionCard q : questions) {
            if (q.getFirstTry()) {
                numCorrect++;
            }
        }
        return numCorrect;
    }
}
