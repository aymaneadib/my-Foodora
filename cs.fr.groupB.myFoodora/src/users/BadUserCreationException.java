package users;

public class BadUserCreationException extends Exception {
    public BadUserCreationException(String message) {
        super(message);
    }

    public BadUserCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
