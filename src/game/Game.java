package game;

import game.board.Board;
import game.exceptions.IllegalPositionException;
import game.exceptions.InvalidPlayerTurn;
import game.exceptions.NotEmptyPositionException;
import game.exceptions.NullPieceException;

/**
 * This class manages a TickTackToe game
 * @author csaito
 *
 */
public abstract class Game {
	protected Board board;
	protected Piece turn;
	protected Piece winner;
	protected boolean finished;


	public Game() {
		this.board = new Board();
		// Defines randomly the first player to begin
		if (Math.random()*2<1) {
			this.turn = Piece.O;
		} else {
			this.turn = Piece.X;
		}
	}

	public Game(Piece start) {
		this.board = new Board();
		this.turn = start;
	}
	/**
	 * The player X plays
	 * @param position where to put the X (number from 1 to 9)
	 * @throws InvalidPlayerTurn  if it's not X player turn
	 * @throws IllegalPositionException  if position is not valid
	 * @throws NotEmptyPositionException 
	 */
	protected void playX(int position) throws InvalidPlayerTurn, IllegalPositionException, NotEmptyPositionException {
		if (this.turn != Piece.X) {
			throw new InvalidPlayerTurn("It's not turn of player X.");
		}
		if (position<1 || position>9) {
			throw new IllegalPositionException("Position " + position + " is not valid");
		}
		if (!this.finished) {
			try {
				this.board.putPiece(Piece.X, position);
				this.turn = Piece.O;
			} catch (NullPieceException e) {
				System.err.println("Something go wrong");
				System.err.println(e.getMessage());
			} catch(Exception e) {
				throw e;
			}
			this.turn = Piece.O;
			
		}
	}

	/**
	 * The player O plays
	 * @param position where to put the O (number from 1 to 9)
	 * @throws InvalidPlayerTurn  if it's not O player turn
	 * @throws IllegalPositionException  if position is not valid
	 * @throws NotEmptyPositionException 
	 */
	protected void playO(int position) throws InvalidPlayerTurn, IllegalPositionException, NotEmptyPositionException {
		if (this.turn != Piece.O) {
			throw new InvalidPlayerTurn("It's not turn of player O.");
		}
		if (position<1 || position>9) {
			throw new IllegalPositionException("Position " + position + " is not valid");
		}
		if (!this.finished) {
			try {
				board.putPiece(Piece.O, position);
			} catch (NullPieceException e) {
				System.err.println("Something go wrong");
				System.err.println(e.getMessage());
			} catch(Exception e) {
				throw e;
			}
			this.turn = Piece.X;
		}
	}

	protected void playO(int i, int j) throws InvalidPlayerTurn, IllegalPositionException, NotEmptyPositionException {
		if (this.turn != Piece.O) {
			throw new InvalidPlayerTurn("It's not turn of player O.");
		}
		if (i<1 || i>3 || j<1 || j>3) {
			throw new IllegalPositionException("Position (" + i + "," + j + ") is not valid");
		}
		if (!this.finished) {
			try {
				board.putPiece(Piece.O, i, j);
				this.turn = Piece.X;
			} catch (NullPieceException e) {
				System.err.println("Something go wrong");
				System.err.println(e.getMessage());
			} catch(Exception e) {
				throw e;
			}
			this.turn = Piece.X;
			
		}
	}

	public Piece getTurn() {
		return this.turn;
	}
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * @return the winner of the game if any
	 */
	public Piece getWinner() {
		update();
		return this.winner;
	}

	/**
	 * @return true if the game's finished. false ioc.
	 */
	public boolean isFinished() {
		update();
		return this.finished;
	}

	private void update() {
		this.finished = false;
		this.winner = null;

		try {
			boolean xWins = checkTicTacToe(Piece.X);
			boolean oWins = checkTicTacToe(Piece.O);

			if (xWins && !oWins) {
				this.winner = Piece.X;
				this.finished = true;
			} else if (oWins && !xWins) {
				this.winner = Piece.O;
				this.finished = true;
			}

			this.finished = this.finished || this.board.isFull();
			// Check if is full
		} catch (NullPieceException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Check if there is a 3-in-a-line given an array
	 * @param what  the piece to look for
	 * @param arr   the array to look where
	 * @return  true if all elements of arr are the piece. false ioc.
	 * @throws NullPieceException  if the piece given is null
	 */
	private boolean checkThreeInArray(Piece what, Piece[] arr) throws NullPieceException {
		if (what==null) {
			throw new NullPieceException("The piece must be not null");
		}

		boolean result = true;
		for (int i=0; i<3 && result; i++) {
			result = arr[i]==what;
		}

		return result;
	}

	private boolean checkTicTacToe(Piece piece) throws NullPieceException {
		if (piece==null) {
			throw new NullPieceException("A null piece is given");
		}
		boolean wins = false;
		try {
			for (int i=1; i<=3 && !wins; i++) {
				wins = checkThreeInArray(piece, board.getRow(i));
			}

			for (int i=1; i<=3 && !wins; i++) {
				wins = checkThreeInArray(piece, board.getColumn(i));
			}

			wins = wins || checkThreeInArray(piece, board.getDiagonal(true));
			wins = wins || checkThreeInArray(piece, board.getDiagonal(false));
		} catch (IllegalPositionException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		} catch (NullPieceException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		}
		return wins;
	}

	@Override
	public String toString() {
		String str = "";
		str += this.board + "\n";
		//str += "Turn of " + this.turn;
		if (this.isFinished()) {
			str += "\nFINISHED!";
		}
		return str;
	}
}
