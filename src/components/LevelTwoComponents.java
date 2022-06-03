package components;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utilities.Constants;
import utilities.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelTwoComponents extends ScreenComponent {

    private final static int TOTAL_QUESTIONS = 6;

    private Popup activePopup;
    private QuestionCard currentQuestion;
    private DialoguePopup[] levelTwoDialogue;
    private QuestionCard[] questions;
    private int curQuestionNum;

    public LevelTwoComponents() {
        super();
        curQuestionNum = 0;
        addComponents();
    }

    private void setupDialogue() {

        // the dialogue to be displayed in dialogue popups.
        String[] dialogue = {
                "Let's play a game. I have these cards with questions and answers on them, and you pick what you think is the best answer.",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
        };

        levelTwoDialogue = new DialoguePopup[8];

        for (int i = 0; i < 1; ++i) {
            levelTwoDialogue[i] = new DialoguePopup(
                    new ImageView(Tools.getImage(Constants.OLDER_SIBLING, 240, 280, true, true)),
                    "Older Sibling", dialogue[i], () ->
            {
                this.setActivePopup(null);
                this.setCurrentQuestion(questions[0]);
            }
            );
        }

    }

    private void setupQuestions() {
        Scanner fr = null;
        try {
            fr = new Scanner(new File(Constants.DATA_PATH + "cardcontent.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        questions = new QuestionCard[TOTAL_QUESTIONS];
        
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

    @Override
    public void addComponents() {
        // TODO: cutscene

        setupDialogue();
        setupQuestions();

        PauseTransition pt = new PauseTransition(Duration.millis(500));
        pt.setOnFinished(event -> setActivePopup(levelTwoDialogue[0]));
        pt.play();

    }

    public Popup getActivePopup() {
        return activePopup;
    }

    public void setActivePopup(Popup newPopup) {
        if (activePopup != null) this.getChildren().remove(activePopup);
        activePopup = newPopup;
        if (activePopup != null) this.getChildren().add(activePopup);
    }

    public void nextQuestion() {
        if (curQuestionNum == TOTAL_QUESTIONS-1) setCurrentQuestion(null);
        else setCurrentQuestion(questions[++curQuestionNum]);
    }

    public void setCurrentQuestion(QuestionCard newQuestion) {
        if (currentQuestion != null) this.getChildren().remove(currentQuestion);
        currentQuestion = newQuestion;
        if (newQuestion != null) this.getChildren().add(newQuestion);
    }
}
