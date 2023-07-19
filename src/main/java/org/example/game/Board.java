package org.example.game;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Cor;
import org.example.parts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@Service
public class Board {

	@Getter
	@Setter
	private Piece[][] board;

	@Getter
	private boolean gameOver;

	@Autowired
	private ChessGame chessGame;

	public Board() {
		setBoard(new Piece[8][8]);
		// Inicializar as peças no tabuleiro
		initializeBoard();
		setGameOver(false);
	}

	private void initializeBoard() {
		// Peças pretas
		var corPreto = Cor.PRETO;
		getBoard()[0][0] = new Rook(corPreto);
		getBoard()[0][1] = new Knight(corPreto);
		getBoard()[0][2] = new Bishop(corPreto);
		getBoard()[0][3] = new Queen(corPreto);
		getBoard()[0][4] = new King(corPreto);
		getBoard()[0][5] = new Bishop(corPreto);
		getBoard()[0][6] = new Knight(corPreto);
		getBoard()[0][7] = new Rook(corPreto);

		for (int col = 0; col < 8; col++) {
			getBoard()[1][col] = new Pawn(corPreto);
		}

		// Peças brancas
		var corBranco = Cor.BRANCO;
		getBoard()[7][0] = new Rook(corBranco);
		getBoard()[7][1] = new Knight(corBranco);
		getBoard()[7][2] = new Bishop(corBranco);
		getBoard()[7][3] = new Queen(corBranco);
		getBoard()[7][4] = new King(corBranco);
		getBoard()[7][5] = new Bishop(corBranco);
		getBoard()[7][6] = new Knight(corBranco);
		getBoard()[7][7] = new Rook(corBranco);

		for (int col = 0; col < 8; col++) {
			getBoard()[6][col] = new Pawn(corBranco);
		}
	}

	public void display() {

		out.print("      ");
		for (int col = 0; col < 8; col++) {
			out.print("C" + col + "          ");
		}
		out.println();
		out.printf("      %s%n", "--------------------------------------------------------------------------------------");
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Piece piece = getBoard()[row][col];
				if (col == 0) {
					out.print("L" + row + "  |  ");
				} else {
					out.print("     ");
				}

				if (piece == null) {
					out.print("-      ");
				} else {
					out.print(piece.getSymbol() + "      ");
				}
			}
			out.println();
		}
		out.println();
	}

	public Piece getPiece(int row, int col) {
		return getBoard()[row][col];
	}

	public boolean isCheckmate() {
		// Verificar se o rei está em xeque
		var currentPlayerColor = getChessGame().isPlayerTurn() ? Cor.BRANCO : Cor.PRETO;

		if (!isKingInCheck(currentPlayerColor)) {
			return false; // Não está em xeque-mate
		}

		// Verificar se o jogador pode fazer algum movimento para sair do xeque
		List<Piece> currentPlayerPieces = getAllPiecesOfColor(currentPlayerColor);
		for (Piece piece : currentPlayerPieces) {
			int startRow = piece.getRow();
			int startCol = piece.getCol();
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (isValidMove(startRow, startCol, row, col)) {
						// Fazer o movimento temporariamente para verificar se ainda estará em xeque
						Piece capturedPiece = movePiece(startRow, startCol, row, col);
						boolean isStillInCheck = isKingInCheck(currentPlayerColor);
						undoMove(startRow, startCol, row, col, capturedPiece);
						if (!isStillInCheck) {
							return false; // O jogador pode sair do xeque
						}
					}
				}
			}
		}
		return true; // Xeque-mate
	}

	public boolean isKingInCheck(Cor kingColor) {
		// Encontre a posição do rei
		var kingRow = -1;
		var kingCol = -1;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Piece piece = getBoard()[row][col];
				if (piece != null && piece instanceof King && piece.getColor().equals(kingColor)) {
					kingRow = row;
					kingCol = col;
					break;
				}
			}
		}
		if (kingRow == -1 || kingCol == -1) {
			return false; // Rei não encontrado
		}

		// Verifique se alguma peça adversária pode capturar o rei
		var opponentColor = kingColor.equals(Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
		List<Piece> opponentPieces = getAllPiecesOfColor(opponentColor);
		for (Piece piece : opponentPieces) {
			int startRow = piece.getRow();
			int startCol = piece.getCol();
			if (piece.isValidMove(startRow, startCol, kingRow, kingCol, this)) {
				return true; // Rei está em xeque
			}
		}
		return false; // Rei não está em xeque
	}

	public List<Piece> getAllPiecesOfColor(Cor color) {
		List<Piece> pieces = new ArrayList<>();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Piece piece = getBoard()[row][col];
				if (piece != null && piece.getColor().equals(color)) {
					pieces.add(piece);
				}
			}
		}
		return pieces;
	}


	public boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
		Piece piece = getBoard()[startRow][startCol];
		return piece != null && piece.isValidMove(startRow, startCol, endRow, endCol, this);
	}

	public void undoMove(int startRow, int startCol, int endRow, int endCol, Piece capturedPiece) {
		Piece piece = getBoard()[endRow][endCol];
		getBoard()[startRow][startCol] = piece;
		getBoard()[endRow][endCol] = capturedPiece;
		piece.setRow(startRow);
		piece.setCol(startCol);
	}

	public Piece movePiece(int startRow, int startCol, int endRow, int endCol) {
		Piece piece = getBoard()[startRow][startCol];
		Piece capturedPiece = getBoard()[endRow][endCol];
		getBoard()[startRow][startCol] = null;
		getBoard()[endRow][endCol] = piece;
		piece.setRow(endRow);
		piece.setCol(endCol);
		return capturedPiece;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ChessGame getChessGame() {
		return chessGame;
	}

}
