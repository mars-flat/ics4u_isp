package components;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utilities.Constants;
import utilities.Tools;

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

        String[] qs = {
                "Q: You're greeted by a classmate you’ve never talked to before. What do you do?",
                "Q: You have a question about homework. What do you do?",
                "Q: You're talking with others in a group, and you stumble on your words. What will they most likely do?",
                "Q: You forgot your pencils for a quiz. What do you do?",
                "Q: You see a group of people during lunch that are talking about something you like. What do you do?",
                "Q: When faced with a presentation, you should:"
        };

        questions = new QuestionCard[TOTAL_QUESTIONS];
        questions[0] = new QuestionCard(qs[0],
                new String[]{"Duck into the nearest washroom",
                        "Pretend to look at a squirrel outside",
                        "Wave or greet them back",
                        "Look at them awkwardly and give no response"},
                new String[]{"The other classmate would be sad that you ignored them...",
                        "The other classmate would be sad that you think an imaginary squirrel is more important than them...",
                        "That's right, a simple hi back not only resolves this crisis and could earn you a potential new friend!",
                        "That's just going to make things more awkward than it should be."
                },
                2, this);
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

    public void setCurrentQuestion(QuestionCard newQuestion) {
        if (currentQuestion != null) this.getChildren().remove(currentQuestion);
        currentQuestion = newQuestion;
        if (newQuestion != null) this.getChildren().add(newQuestion);
    }
}
