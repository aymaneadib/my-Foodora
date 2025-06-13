package user;

/**
 * Exception class for handling errors related to user creation.
 * This exception is thrown when there are issues with creating a user.
 * 
 * @author Aymane Adib
 */
public class BadUserCreationException extends Exception {
    public BadUserCreationException(String message) {
        super(message);
    }

    public BadUserCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
