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
public class KnightTest {

	@InjectMocks
	private Knight sut;

	@Nested
	class isValidMove {

		@Test
		void deve_retornar_true_se_movimento_do_cavalo_for_valido() {

			var startRow = 0;
			var startCol = 0;
			var endRow = 2;
			var endCol = 1;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.PRETO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertTrue(retorno);
		}

		@Test
		void deve_retornar_falso_se_movimento_do_cavalo_for_valido_e_contem_uma_peca_no_destino() {

			var startRow = 0;
			var startCol = 0;
			var endRow = 2;
			var endCol = 1;

			var board = getBoard();
			board.getBoard()[startRow][startCol] = sut;
			board.getBoard()[startRow][startCol].setColor(Cor.PRETO);
			board.getBoard()[endRow][endCol] = new Pawn(Cor.PRETO);

			var retorno = sut.isValidMove(startRow, startCol, endRow, endCol, board);

			assertFalse(retorno);
		}

		@Test
		void deve_retornar_falso_se_movimento_do_cavalo_for_invalido() {

			var startRow = 0;
			var startCol = 0;
			var endRow = 2;
			var endCol = 2;

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