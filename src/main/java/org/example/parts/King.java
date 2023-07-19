package org.example.parts;

import org.example.enums.Cor;
import org.example.enums.SymbolParts;
import org.example.game.Board;

public class King extends Piece {

	public King(Cor color) {
		super(color, SymbolParts.KING);
	}

	public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
		// Verificar se o movimento é uma casa adjacente
		if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) {

			// Verificar se a casa de destino está vazia ou contém uma peça adversária
			Piece endPiece = board.getPiece(endRow, endCol);
			return endPiece == null || !endPiece.getColor().equals(getColor());
		}
		return false;
	}

}
