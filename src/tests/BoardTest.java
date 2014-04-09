package tests;

import static org.junit.Assert.*;
import game.Piece;
import game.board.Board;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private static Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void testGetNullPiece() throws Exception {
		assertEquals(null, board.getPiece(2));
	}

	@Test
	public void testputPiece() throws Exception {
		board.putPiece(Piece.O, 1);
		assertEquals(Piece.O, board.getPiece(1));
	}
	
	@Test
	public void testputPiece2() throws Exception {
		board.putPiece(Piece.X, 6);
		assertEquals(Piece.X, board.getPiece(2, 3));
	}
	
	@Test
	public void testputPiece3() throws Exception {
		board.putPiece(Piece.X, 1, 3);
		assertEquals(Piece.X, board.getPiece(3));
	}
	
	@Test
	public void testGetColumn() throws Exception {
		Piece[] col = {Piece.X, Piece.X, Piece.X};
		
		board.putPiece(Piece.X, 1);
		board.putPiece(Piece.X, 4);
		board.putPiece(Piece.X, 7);
		
		assertArrayEquals(col, board.getColumn(1));
	}
	
	
	@Test
	public void testGetRow() throws Exception {
		Piece[] col = {Piece.X, null, Piece.X};
		
		board.putPiece(Piece.X, 4);
		board.putPiece(Piece.X, 6);
		
		assertArrayEquals(col, board.getRow(2));
	}
	
	
	@Test
	public void testGetDiagonal() throws Exception {
		Piece[] col = {Piece.X, null, Piece.X};
		
		board.putPiece(Piece.X, 1);
		board.putPiece(Piece.X, 9);
		
		assertArrayEquals(col, board.getDiagonal(true));
	}
	
	@Test
	public void testGetDiagonal2() throws Exception {
		Piece[] col = {Piece.X, null, Piece.X};
		
		board.putPiece(Piece.X, 3);
		board.putPiece(Piece.X, 7);
		
		assertArrayEquals(col, board.getDiagonal(false));
	}
}
