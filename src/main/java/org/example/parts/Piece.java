package org.example.parts;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Cor;
import org.example.enums.SymbolParts;
import org.example.game.Board;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public abstract class Piece {

	private Cor color;

	private SymbolParts symbol;

	private int row;

	private int col;

	protected Piece(Cor color, SymbolParts symbol) {
		this.color = color;
		this.symbol = symbol;
	}

	public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Board board);

	public String getSymbol() {
		var chr = String.valueOf(symbol.name().charAt(0));
		return color.equals(Cor.BRANCO) ? chr.toUpperCase() : chr.toLowerCase();
	}

}
