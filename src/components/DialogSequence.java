package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogSequence {
    List<DialogPane> dialogues;

    private int currentDialogue;

    public DialogSequence(DialogPane... dialogPanes) {
        dialogues = new ArrayList<>();
        dialogues.addAll(Arrays.asList(dialogPanes));
        currentDialogue = 0;
    }
}
