package components;

/**
 * Implemented in the initialization of an instance that requires a change request.
 *
 * This is what is called when a change request occurs (ex. the user presses SPACE).
 *
 * @since 2.0, 5/23/2022
 * @author Shane Chen
 */
@FunctionalInterface
public interface ChangeRequest {

    /**
     * What runs on a change request.
     */
    void onChangeRequest();
}
