package system;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 2565479115146193516L;

	public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}