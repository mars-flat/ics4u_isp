package components;

public class LevelTwoComponents extends ScreenComponent {

    private DialogPane activeDialogue;

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

    public void setActiveDialogue(DialogPane newDialogue) {
        if (activeDialogue != null) this.getChildren().remove(activeDialogue);
        activeDialogue = newDialogue;
        if (activeDialogue != null) this.getChildren().add(activeDialogue);
    }

    public DialogPane getActiveDialogue() {
        return activeDialogue;
    }
}
