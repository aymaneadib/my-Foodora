package system;

public class IncorrectCredentialsException extends Exception {

	private static final long serialVersionUID = 1171147363304793519L;

	public IncorrectCredentialsException(String message) {
        super(message);
    }

    public IncorrectCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

}