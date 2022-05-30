package components;

public class LevelThreeComponents extends ScreenComponent {

    private DialoguePopup activeDialogue;

    public LevelThreeComponents() {
        super();
        addComponents();
    }

    private void setupDialogue() {

    }

    @Override
    public void addComponents() {


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
