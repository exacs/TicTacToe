package game.exceptions;

@SuppressWarnings("serial")
public class InvalidPlayerTurn extends Exception {

	public InvalidPlayerTurn() {
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerTurn(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerTurn(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerTurn(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerTurn(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
