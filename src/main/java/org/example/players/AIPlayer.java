package org.example.players;

import org.example.game.Board;

import java.util.Scanner;

import static java.lang.System.out;

public class AIPlayer {
	private Board board;

	public AIPlayer(Board board) {
		this.board = board;
	}

	public void makeMove(Scanner scanner) {
		out.println("Digite a posição da peça que deseja mover (linha coluna):");
		var startRow = scanner.nextInt();
		var startCol = scanner.nextInt();

		out.println("Digite a posição para onde deseja mover a peça (linha coluna):");
		var endRow = scanner.nextInt();
		var endCol = scanner.nextInt();

		if (isValidMove(startRow, startCol, endRow, endCol)) {
			board.movePiece(startRow, startCol, endRow, endCol);
		} else {
			out.println("Movimento inválido. Tente novamente.");
			makeMove(scanner);
		}
	}

	private boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
		var piece = board.getBoard()[startRow][startCol];
		return piece != null && piece.isValidMove(startRow, startCol, endRow, endCol, board);
	}

}
