package org.example.parts;

import org.example.enums.Cor;
import org.example.enums.SymbolParts;
import org.example.game.Board;

public class Bishop extends Piece {
	public Bishop(Cor color) {
		super(color, SymbolParts.BISHOP);
	}

	public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
		// Verificar se o movimento é diagonal

		if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
			// Verificar se não há peças bloqueando o caminho
			var rowStep = (endRow > startRow) ? 1 : -1;
			var colStep = (endCol > startCol) ? 1 : -1;

			var currentRow = startRow + rowStep;
			var currentCol = startCol + colStep;

			while (currentRow != endRow && currentCol != endCol) {
				if (board.getPiece(currentRow, currentCol) != null) {
					return false; // Há uma peça bloqueando o caminho
				}
				currentRow += rowStep;
				currentCol += colStep;
			}

			// Verificar se a casa de destino está vazia ou contém uma peça adversária
			Piece endPiece = board.getPiece(endRow, endCol);
			return endPiece == null || !endPiece.getColor().equals(getColor());
		}
		return false;
	}

}
