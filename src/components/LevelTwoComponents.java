package components;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utilities.Constants;
import utilities.Tools;

import javax.tools.Tool;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Components for the second level of the game.
 *
 * @since 3.0, 5/30/2022
 *
 * @author Shane Chen
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
                "Let's play a game. I have these cards with questions and answers on them, and you pick what you think is the best answer.",
                "Hm. Thanks for the advice, I guess?",
                "Trust me. If you do the right things, you'll get over it.",
                "I've experienced social anxiety myself. These cards helped me a ton. Listen, none of your fears are real. Get out there and try!",
                "*Convinced* I- You didn't tell me?",
                "For the same reason you didn't tell me.",
                "Well then, thanks. I guess."
        };

        levelTwoDialogue = new DialoguePopup[8];
        levelTwoDialogue[0] = new DialoguePopup(
                new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 240, 280, true, true)),
                "Older Sibling", dialogue[0], () ->
        {
            this.setActivePopup(null);
            this.setCurrentQuestion(questions[0]);
        });
        for (int i = 1; i <= 6; ++i) {
            int finalI = i;
            levelTwoDialogue[i] = new DialoguePopup(
                    new ImageView(Tools.getImage(
                            (i == 1 || i == 4 || i == 6) ? Constants.YOUNGER_SIBLING : Constants.OLDER_SIBLING, 240, 280, true, true)),
                            (i == 1 || i == 4 || i == 6) ? "Younger Sibling" : "Older Sibling", dialogue[i], () -> {
                this.setActivePopup(finalI == 6 ? null : levelTwoDialogue[finalI + 1]);
            });
        }
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
     * @param newPopup
     * The new active popup, or {@code null} if there will be no active popup.
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
        if (curQuestionNum == TOTAL_QUESTIONS-1) {
            this.setCurrentQuestion(null);
            this.setActivePopup(levelTwoDialogue[1]);
        }
        else this.setCurrentQuestion(questions[++curQuestionNum]);
    }

    /**
     * Sets the current quesiton. May be {@code null} to indicate to have no question.
     * 
     * @param newQuestion
     * The new question, or {@code null} if there will be no question.
     */
    public void setCurrentQuestion(QuestionCard newQuestion) {
        if (currentQuestion != null) this.getChildren().remove(currentQuestion);
        currentQuestion = newQuestion;
        if (newQuestion != null) this.getChildren().add(newQuestion);
    }
}
