package system;

public class AvailableCourierNotFoundException extends Exception {

	private static final long serialVersionUID = 2455407000631728881L;

	public AvailableCourierNotFoundException(String message) {
        super(message);
    }

    public AvailableCourierNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}