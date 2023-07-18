package org.example.parts;

import org.example.enums.Cor;
import org.example.enums.SymbolParts;
import org.example.game.Board;

public class Knight extends Piece {
	public Knight(Cor color) {
		super(color, SymbolParts.KNIGHT);
	}

	public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
		// Verificar se o movimento é um movimento válido do cavalo

		var rowDiff = Math.abs(startRow - endRow);
		var colDiff = Math.abs(startCol - endCol);

		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
			// Verificar se a casa de destino está vazia ou contém uma peça adversária

			Piece endPiece = board.getPiece(endRow, endCol);
			return endPiece == null || !endPiece.getColor().equals(getColor());
		}

		return false;
	}

}
