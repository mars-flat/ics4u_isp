package components;

/**
 * Implemented in the initialization of a {@link Popup} instance.
 *
 * This is what is called when a change request occurs (i.e., the user presses SPACE).
 *
 * @since 2.0, 5/23/2022
 * @author Shane Chen
 */
@FunctionalInterface
public interface PopupChangeRequest {
    void onChangeRequest();
}
