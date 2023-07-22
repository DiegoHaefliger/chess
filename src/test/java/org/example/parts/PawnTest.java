package org.example.parts;

import org.example.enums.Cor;
import org.example.game.Board;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PawnTest {

	@InjectMocks
	private Pawn sut;

	@Nested
	class isValidMove {

		@Test
		void deve_retornar_true_se_movimento_vertical_peca_preta_for_valido() {

			var startRow = 0;
			var startCol = 0;
			var endRow = 1;
			var endCol = 0;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.PRETO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertTrue(retorno);
		}

		@Test
		void deve_retornar_true_se_movimento_vertical_peca_preta_e_avancar_duas_casas_na_primeira_jogada_for_valido() {

			var startRow = 1;
			var startCol = 0;
			var endRow = 3;
			var endCol = 0;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.PRETO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertTrue(retorno);
		}

		@Test
		void deve_retornar_true_se_movimento_vertical_peca_branca_for_valido() {

			var startRow = 6;
			var startCol = 0;
			var endRow = 5;
			var endCol = 0;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.BRANCO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertTrue(retorno);
		}

		@Test
		void deve_retornar_true_se_movimento_vertical_peca_branca_e_avancar_duas_casas_na_primeira_jogada_for_valido() {

			var startRow = 6;
			var startCol = 0;
			var endRow = 4;
			var endCol = 0;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.BRANCO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertTrue(retorno);
		}

		@Test
		void deve_retornar_true_se_movimento_for_diagonal_para_peca_preta_quando_capturar_outra_peca() {

			var startRow = 1;
			var startCol = 0;
			var endRow = 2;
			var endCol = 1;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.PRETO);

			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[endRow][endCol] = new Pawn(Cor.BRANCO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertTrue(retorno);
		}

		@Test
		void deve_retornar_true_se_movimento_for_diagonal_para_peca_branco_quando_capturar_outra_peca() {

			var startRow = 6;
			var startCol = 0;
			var endRow = 5;
			var endCol = 1;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.BRANCO);

			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[endRow][endCol] = new Pawn(Cor.PRETO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertTrue(retorno);
		}

		@Test
		void deve_retornar_falso_se_movimento_for_diagonal_para_peca_branco_quando_capturar_outra_peca() {

			var startRow = 6;
			var startCol = 0;
			var endRow = 5;
			var endCol = 1;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.BRANCO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertFalse(retorno);
		}

		@Test
		void deve_retornar_falso_se_movimento_for_diagonal_para_peca_preta_quando_capturar_outra_peca() {

			var startRow = 1;
			var startCol = 0;
			var endRow = 2;
			var endCol = 1;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.PRETO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertFalse(retorno);
		}

	}

	private Board getBoard() {
		var startRow = 0;
		var startCol = 0;

		var board = new Board();
		board.setBoard(new Piece[8][8]);
		board.getBoard()[0][0] = sut;

		board.getBoard()[startRow][startCol] = sut;

		return board;
	}

}