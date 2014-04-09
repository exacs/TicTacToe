package game.exceptions;

/**
 * Thrown when a not valid piece is given as a parameter
 *
 */
@SuppressWarnings("serial")
public class NullPieceException extends Exception {

	public NullPieceException() {
		// TODO Auto-generated constructor stub
	}

	public NullPieceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NullPieceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NullPieceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NullPieceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
