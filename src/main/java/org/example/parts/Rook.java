package org.example.parts;

import org.example.enums.Cor;
import org.example.enums.SymbolParts;
import org.example.game.Board;

public class Rook extends Piece {
	public Rook(Cor color) {
		super(color, SymbolParts.ROOK);
	}

	public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
		// Verificar se o movimento é vertical ou horizontal

		if (startRow == endRow || startCol == endCol) {

			// Verificar se não há peças bloqueando o caminho
			if (startRow == endRow) {
				// Movimento horizontal

				var colStep = (endCol > startCol) ? 1 : -1;
				var currentCol = startCol + colStep;

				while (currentCol != endCol) {
					if (board.getPiece(startRow, currentCol) != null) {
						return false; // Há uma peça bloqueando o caminho
					}
					currentCol += colStep;
				}

			} else {
				// Movimento vertical

				var rowStep = (endRow > startRow) ? 1 : -1;
				var currentRow = startRow + rowStep;

				while (currentRow != endRow) {
					if (board.getPiece(currentRow, startCol) != null) {
						return false; // Há uma peça bloqueando o caminho
					}
					currentRow += rowStep;
				}
			}

			// Verificar se a casa de destino está vazia ou contém uma peça adversária
			Piece endPiece = board.getPiece(endRow, endCol);
			return endPiece == null || !endPiece.getColor().equals(getColor());
		}
		return false;
	}

}
