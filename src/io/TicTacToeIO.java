package io;

import java.util.InputMismatchException;
import java.util.Scanner;

import game.SinglePlayerGame;
import game.Piece;
import game.exceptions.IllegalPositionException;
import game.exceptions.InvalidPlayerTurn;
import game.exceptions.NotEmptyPositionException;

public class TicTacToeIO {

	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		int localResult = 0;
		boolean playAgain = true;
		SinglePlayerGame game = new SinglePlayerGame();

		while (playAgain) {
			game = new SinglePlayerGame();
			System.out.println();
			System.out.println();
			System.out.println();
			while(!game.isFinished()) {
				System.out.println(game);
				if (game.getTurn()==Piece.X) {
					try {
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.print("Inserte un número del 1 al 9 >");
						keyboard = new Scanner(System.in);
						int position = keyboard.nextInt(10);
						game.playHuman(position);
					} catch (InputMismatchException e){
						System.out.println("Introduce un número!");
						System.out.println();
					} catch (IllegalPositionException | NotEmptyPositionException e) {
						System.out.println("Posición no válida");
						System.out.println();
					} catch (InvalidPlayerTurn e) {

					}

				} else {
					try {
						game.playCpu();
					} catch (InvalidPlayerTurn e) {

					}
				}
			}//While (game finished)
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(game);
			if (game.getWinner()==Piece.X) {
				System.out.println("===== YOU WIN \t=====");
				localResult++;
			} else if  (game.getWinner()==Piece.O) {
				System.out.println("===== YOU LOSE\t=====");
				localResult--;
			} else {
				System.out.println("===== DRAW    \t=====");
			}
			System.out.println    ("===== SCORE: " + localResult + "\t=====");
			System.out.print("Want to try again? Y=Sí >");
			playAgain = keyboard.next().toLowerCase().equals("y");

		}//WHILE (play again)
		System.out.println    ("===========================");
		System.out.println    ("===========================");
		System.out.println    ("");
		System.out.println    ("FINAL SCORE: " + localResult);
		System.out.println    ("");
		System.out.println    ("===========================");
		System.out.println    ("===========================");
	}

}
