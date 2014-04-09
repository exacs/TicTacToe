package game.exceptions;

/**
 * Thrown when a bad position number is given as a parameter
 *
 */
@SuppressWarnings("serial")
public class IllegalPositionException extends Exception {

	public IllegalPositionException() {
		// TODO Auto-generated constructor stub
	}

	public IllegalPositionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalPositionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalPositionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalPositionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
