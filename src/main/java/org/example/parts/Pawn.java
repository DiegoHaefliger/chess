package org.example.parts;

import org.example.enums.Cor;
import org.example.enums.SymbolParts;
import org.example.game.Board;

public class Pawn extends Piece {

	public Pawn(Cor color) {
		super(color, SymbolParts.PAWN);
	}

	public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {

		var piece = board.getPiece(startRow, startCol);

		if (startCol == endCol) {
			// Movimento para frente
			if (piece.getColor().equals(Cor.BRANCO)) {

				// Movimento válido para o peão branco: uma casa para frente
				if (endRow == startRow - 1 && board.getPiece(endRow, endCol) == null) {
					return true;
				}

				// Movimento válido para o peão branco: duas casas para frente (primeiro movimento)
				return endRow == startRow - 2 &&
						startRow == 6 &&
						board.getPiece(endRow, endCol) == null
						&& board.getPiece(endRow + 1, endCol) == null;

			} else {

				// Movimento válido para o peão preto: uma casa para frente
				if (endRow == startRow + 1 && board.getPiece(endRow, endCol) == null) {
					return true;
				}

				// Movimento válido para o peão preto: duas casas para frente (primeiro movimento)
				return endRow == startRow + 2 &&
						startRow == 1 &&
						board.getPiece(endRow, endCol) == null &&
						board.getPiece(endRow - 1, endCol) == null;

			}
		} else {

			// Verificar se a peça capturou uma peça adversária diagonalmente
			if (piece.getColor().equals(Cor.BRANCO)) {

				// Captura diagonal para o peão branco
				return endRow == startRow - 1 &&
						Math.abs(endCol - startCol) == 1 &&
						board.getPiece(endRow, endCol) != null &&
						!board.getPiece(endRow, endCol).getColor().equals(getColor());

			} else {

				// Captura diagonal para o peão preto
				return endRow == startRow + 1 &&
						Math.abs(endCol - startCol) == 1 &&
						board.getPiece(endRow, endCol) != null &&
						!board.getPiece(endRow, endCol).getColor().equals(getColor());
			}
		}
	}

}
