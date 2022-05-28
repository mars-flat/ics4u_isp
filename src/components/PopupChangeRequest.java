package components;

/**
 * Implemented in the initialization of a {@link Popup} instance.
 *
 * This is what is called when a change request occurs (i.e., the user presses SPACE).
 */
@FunctionalInterface
public interface PopupChangeRequest {
    void onChangeRequest();
}
