package game.board;

import game.Piece;
import game.exceptions.IllegalPositionException;
import game.exceptions.NotEmptyPositionException;
import game.exceptions.NullPieceException;

/**
 * This class stores the data of a TickTackToe game pieces.
 *
 */
public class Board implements Cloneable {

	private Piece[][] pieces;

	public Board() {
		this.pieces = new Piece[3][3];
	}

	/**
	 * Sets a piece in a position
	 * @param piece     the piece to put
	 * @param position  the position number (from 1 to 9)
	 * 
	 * @throws IllegalPositionException  if the position is not from 1 to 9
	 * @throws NullPieceException        if a null piece is passed as an argument
	 * @throws NotEmptyPositionException if there is a piece in the position
	 */
	public void putPiece(Piece piece, int position) throws IllegalPositionException, NullPieceException, NotEmptyPositionException {
		if (position<1 || position>9) {
			throw new IllegalPositionException("Position " + position + " is not valid");
		}
		if (piece==null) {
			throw new NullPieceException("A not null piece must be placed");
		}
		int y = (position-1)%3+1;
		int x = (int)Math.ceil(position/3.0);
		
		try {
			this.putPiece(piece, x, y);
		} catch (IllegalPositionException e) {
			System.err.println("Given the position " + position + " something goes wrong");
			System.err.println(e.getMessage());
		} catch (NotEmptyPositionException e) {
			throw new NotEmptyPositionException("There is a piece in position " + position);
		}
	}

	/**
	 * Sets a piece in a position (x, y)
	 * @param piece  the piece to put
	 * @param i  the row (from 1 to 3)
	 * @param j  the column (from 1 to 3)
	 * 
	 * @throws IllegalPositionException  if one coordinate is not from 1 to 3
	 * @throws NullPieceException        if a null piece is passed as an argument
	 * @throws IllegalPositionException if there is a piece in the position
	 */
	public void putPiece(Piece piece, int i, int j) throws IllegalPositionException, NullPieceException, NotEmptyPositionException {
		if (i<1 || i>3 || j<1 || j>3) {
			throw new IllegalPositionException("Position (" + i + "," + j + ") is not valid");
		}
		if (piece==null) {
			throw new NullPieceException("A not null piece must be placed");
		}
		if (this.pieces[i-1][j-1]!=null) {
			throw new NotEmptyPositionException("The position " + i + "," + j + " is not empty");
		}
		this.pieces[i-1][j-1] = piece;
	}

	/**
	 * Gets a piece in a position (x, y)
	 * @param i  the row (from 1 to 3)
	 * @param j  the column (from 1 to 3)
	 * @return   the piece in that position
	 * 
	 * @throws IllegalPositionException  if one coordinate is not from 1 to 3
	 */
	public Piece getPiece(int i, int j) throws IllegalPositionException {
		if (i<1 || i>3 || j<1 || j>3) {
			throw new IllegalPositionException("Position (" + i + "," + j + ") is not valid");
		}
		return this.pieces[i-1][j-1];
	}
	
	/**
	 * Gets a piece in a position from 1 to 9
	 * @param   position the position (from 1 to 9)
	 * @return  the piece in that position
	 * 
	 * @throws IllegalPositionException
	 */
	public Piece getPiece(int position) throws IllegalPositionException {
		
		if (position<1 || position>9) {
			throw new IllegalPositionException("Position " + position + " is not valid");
		}
		int y = (position-1)%3+1;
		int x = (int)Math.ceil(position/3.0);
		
		Piece result = null;
		try {
			result = this.getPiece(x, y);
		} catch (IllegalPositionException e) {
			System.err.println("Given the position " + position + " something goes wrong");
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * Gets all the elements of a single row
	 * @param y  the row (from 1 to 3)
	 * @return   an array of length 3 with the elements of the row
	 * 
	 * @throws IllegalPositionException  if position given is not valid
	 */
	public Piece[] getRow(int y) throws IllegalPositionException {
		if (y<1 || y>3) {
			throw new IllegalPositionException("Row " + y + " is not valid");
		}
		Piece[] arr = new Piece[3];
		for (int i=0; i<3; i++) {
			if (pieces[y-1][i]!=null) {
				arr[i] = pieces[y-1][i];
			}
		}
		return arr;
	}
	
	/**
	 * Gets all the elements of a single column
	 * @param x  the column (from 1 to 3)
	 * @return   an array of length 3 with the elements of the column
	 * @throws IllegalPositionException  if position given is not valid
	 */
	public Piece[] getColumn(int x) throws IllegalPositionException {
		if (x<1 || x>3) {
			throw new IllegalPositionException("Column " + x + " is not valid");
		}
		Piece[] arr = new Piece[3];
		for (int i=0; i<3; i++) {
			if (pieces[i][x-1]!=null) {
				arr[i] = pieces[i][x-1];
			}
		}
		return arr;
	}
	
	/**
	 * Gets all the elements of a diagonal
	 * @param  positive  true to return the main diagonal. false to return the inverse diagonal
	 * @return an array of length 3 with the elements of the diagonal
	 */
	public Piece[] getDiagonal(boolean positive) {
		Piece[] arr = new Piece[3];
		for (int i=0; i<3; i++) {
			if (positive) {
				arr[i] = this.pieces[i][i];
			} else {
				arr[i] = this.pieces[2-i][i];
			}
		}
		return arr;
	}
	
	public Piece[][] getPieces() {
		return this.pieces.clone();
	}
	
	/**
	 * Check if the pieces is full
	 * @return  true if is full. false ioc
	 */
	public boolean isFull() {
		boolean result = true;
		for (int i=0; i<3 && result; i++) {
			for (int j=0; j<3 && result; j++) {
				result = this.pieces[i][j]!=null;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += " --- \n";
		for (int i=0; i<3; i++) {
			str += "|";
			for (int j=0; j<3; j++) {
				if (this.pieces[i][j]==null) {
					str += " ";
				} else {
					str += this.pieces[i][j];
				}
			}
			str+= "|\n";
		}
		str += " --- ";
		return str;
		
	}
	
	
}
