package org.example.parts;

import org.example.enums.Cor;
import org.example.enums.SymbolParts;
import org.example.game.Board;
import org.springframework.beans.factory.annotation.Autowired;

public class Queen extends Piece {

	@Autowired
	private Piece pieceService;

	public Queen(Cor color) {
		super(color, SymbolParts.QUEEN);
	}

	public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board) {
		// Verificar se o movimento é vertical, horizontal ou diagonal

		if (startRow == endRow || startCol == endCol || Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
			// Verificar se não há peças bloqueando o caminho

			var rowStep = Integer.compare(endRow, startRow);
			var colStep = Integer.compare(endCol, startCol);

			int currentRow = startRow + rowStep;
			int currentCol = startCol + colStep;

			while (currentRow != endRow || currentCol != endCol) {
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
