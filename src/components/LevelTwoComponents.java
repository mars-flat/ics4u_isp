package components;

public class LevelTwoComponents extends ScreenComponent {

    private DialoguePopup activeDialogue;

    public LevelTwoComponents() {
        super();
        addComponents();
    }

    private void setupDialogue() {

    }

    @Override
    public void addComponents() {
        // play the cutscene

    }

    public void setActiveDialogue(DialoguePopup newDialogue) {
        if (activeDialogue != null) this.getChildren().remove(activeDialogue);
        activeDialogue = newDialogue;
        if (activeDialogue != null) this.getChildren().add(activeDialogue);
    }

    public DialoguePopup getActiveDialogue() {
        return activeDialogue;
    }
}
