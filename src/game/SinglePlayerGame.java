package game;

import game.exceptions.IllegalPositionException;
import game.exceptions.InvalidPlayerTurn;
import game.exceptions.NotEmptyPositionException;


public class SinglePlayerGame extends Game {

	public SinglePlayerGame() {
		super();
		if (this.getTurn()==Piece.O) {
			try {
				playCpu();
			} catch (InvalidPlayerTurn e) {
				System.err.println(e.getMessage());
			}
		}
	}
	public SinglePlayerGame(Piece start) {
		super(start);
	}


	/**
	 * The human player puts a piece
	 * @param position  the piece to put
	 * @throws InvalidPlayerTurn
	 * @throws IllegalPositionException
	 * @throws NotEmptyPositionException
	 */
	public void playHuman(int position) throws InvalidPlayerTurn, IllegalPositionException, NotEmptyPositionException {
		super.playX(position);
	}

	/**
	 * The CPU puts a piece.
	 * @throws NotEmptyPositionException 
	 * @throws IllegalPositionException 
	 * @throws InvalidPlayerTurn 
	 */
	public void playCpu() throws InvalidPlayerTurn {
		// Check 2-O-line
		boolean finished = true;
		try {//TRY
			finished = this.findGap(Piece.O);

			// Check 2-X-line
			if (!finished) {
				finished = this.findGap(Piece.X);
			}

			// Check center
			if (!finished && this.board.getPiece(5)==null) {
				this.playO(5);
				finished = true;
			}

			// Check double bridge
			if (!finished) {finished = this.doubleBridge(1, 1);}
			if (!finished) {finished = this.doubleBridge(1, 3);}
			if (!finished) {finished = this.doubleBridge(3, 1);}
			if (!finished) {finished = this.doubleBridge(3, 3);}

			// Check corners
			if (!finished) {finished = this.adjacentCorner();}

			// Check empty corner
			for(int i=1; i<=9 && !finished; i+=2 ) {
				if (this.board.getPiece(i)==null) {
					this.playO(i);
					finished = true;
				}
			}

			// ioc
			for(int i=2; i<=9 && !finished; i+=2 ) {
				if (this.board.getPiece(i)==null) {
					this.playO(i);
					finished = true;
				}
			}
		}//TRY
		catch (IllegalPositionException e) {
			System.err.println(e.getMessage());
		} catch (NotEmptyPositionException e) {
			System.err.println(e.getMessage());
		}

	}


	// PRIVATES
	/**
	 * Check if there is a 2-in-a-line of a piece in a given array.
	 * Then, returns the index of the array corresponding to the gap
	 * 
	 * @param piece  the piece that makes the 2-in-a-line
	 * @param arr    the array to look for
	 * @return  the index of the array that has a gap
	 */
	private int checkTwoInArray(Piece piece, Piece[] arr) {
		int nullPosition = -1;
		for (int i=0; i<3 && nullPosition==-1; i++) {
			if (arr[i]==null) {
				nullPosition = i;
			}
		}

		if (arr[(nullPosition+1)%3]==piece && arr[(nullPosition+2)%3]==piece) {
			return nullPosition;
		} else {
			return -1;
		}
	}

	// PRIVATES
	/**
	 * Plays an O to break a 3-in-a-row or to make it
	 * @param piece  the piece to look for.
	 *               Piece.O if 
	 * @return true  if piece is played succesfully
	 * @throws InvalidPlayerTurn
	 */
	private boolean findGap(Piece piece) throws InvalidPlayerTurn {
		boolean finished = false;

		try {
			// Horizontal
			for (int i=1; i<=3 && !finished; i++) {
				int j = checkTwoInArray(piece, this.board.getRow(i));
				if (j!=-1) {
					this.playO(i, j+1);
					finished = true;
				}
			}

			// Vertical
			for (int i=1; i<=3 && !finished; i++) {
				int j = checkTwoInArray(piece, this.board.getColumn(i));
				if (j!=-1) {
					this.playO(j+1, i);
					finished = true;
				}
			}

			// Diagonal
			if (!finished) {
				int j = checkTwoInArray(piece, this.board.getDiagonal(true));
				if (j!=-1) {
					this.playO(j+1, j+1);
					finished = true;
				}
			}
			if (!finished) {
				int j = checkTwoInArray(piece, this.board.getDiagonal(false));
				if (j!=-1) {
					this.playO(3-j, j+1);
					finished = true;
				}
			}
		} catch (IllegalPositionException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		} catch (NotEmptyPositionException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		}

		return finished;
	}
	
	/**
	 * Plays an O to double 2-in-a-line given a corner coordinates
	 * @param i  1 or 3
	 * @param j  1 or 3
	 * @return  true if played succesfully
	 * @throws InvalidPlayerTurn
	 */

	private boolean doubleBridge(int i, int j) throws InvalidPlayerTurn {
		boolean finished = false;
		try {
			if (this.board.getPiece(i, j)==Piece.O && this.board.getPiece(5)==Piece.O) {
				// Check S adjacent
				if (!finished && i==1 && this.board.getPiece(i+1, j)==null) {
					this.playO(i+1, j);
					finished = true;
				}

				// Check N adjacent
				if (!finished && i==3 && this.board.getPiece(i-1, j)==null) {
					this.playO(i-1, j);
					finished = true;
				}

				// Check E adjacent
				if (!finished && j==1 && this.board.getPiece(i, j+1)==null) {
					this.playO(i, j+1);
					finished = true;
				}
				// Check W adjacent
				if (!finished && j==3 && this.board.getPiece(i, j-1)==null) {
					this.playO(i, j-1);
					finished = true;
				}
			} 

		} catch (IllegalPositionException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		} catch (NotEmptyPositionException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		}
		return finished;

	}
	/**
	 * Plays an O
	 * @return
	 * @throws InvalidPlayerTurn
	 */
	private boolean adjacentCorner () throws InvalidPlayerTurn {
		boolean result = false;
		try {
			Piece[] line = this.board.getRow(1); 
			if (!result && line[1]==Piece.X && line[0]==null) {
				this.playO(1, 1);
				result = true;
			} else if (!result && line[1]==Piece.X && line[2]==null) {
				this.playO(1, 3);
				result = true;
			}

			line = this.board.getRow(3); 
			if (!result && line[1]==Piece.X && line[0]==null) {
				this.playO(3, 1);
				result = true;
			} else if (!result && line[1]==Piece.X && line[2]==null) {
				this.playO(3, 3);
				result = true;
			}

			line = this.board.getColumn(1); 
			if (!result && line[1]==Piece.X && line[0]==null) {
				this.playO(1, 1);
				result = true;
			} else if (!result && line[1]==Piece.X && line[2]==null) {
				this.playO(3, 1);
				result = true;
			}

			line = this.board.getColumn(3); 
			if (!result && line[1]==Piece.X && line[0]==null) {
				this.playO(1, 3);
				result = true;
			} else if (!result && line[1]==Piece.X && line[2]==null) {
				this.playO(3, 3);
				result = true;
			}

		} catch (IllegalPositionException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		} catch (NotEmptyPositionException e) {
			System.err.println("Something go wrong");
			System.err.println(e.getMessage());
		}

		return result;
	}
}
