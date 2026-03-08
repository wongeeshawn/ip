/**
 * Represents an exception specific to the Chat application.
 * Thrown when the user provides invalid or unrecognised input.
 */
public class ChatException extends Exception {

    /**
     * Constructs a ChatException with the given error message.
     *
     * @param message the error message describing the problem
     */
    public ChatException(String message) {
        super(message);
    }
}