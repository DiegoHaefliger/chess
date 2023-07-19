package org.example.game;

import lombok.Getter;
import lombok.Setter;
import org.example.players.AIPlayer;
import org.example.players.Player;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static java.lang.System.out;

@Service
public class ChessGame {
	private Board board;
	private Player player;
	private AIPlayer aiPlayer;

	@Getter
	@Setter
	private boolean playerTurn;

	public ChessGame() {
		board = new Board();
		player = new Player(board);
		aiPlayer = new AIPlayer(board);
		setPlayerTurn(true);
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);

		while (!board.isGameOver()) {
			board.display();

			if (isPlayerTurn()) {
				out.println("Sua vez de jogar:");
				player.makeMove(scanner);
			} else {
				// É a vez do oponente
				out.println("Vez do oponente:");
				aiPlayer.makeMove(scanner);
			}

			setPlayerTurn(!isPlayerTurn());
		}

		board.display();
		out.println("Fim de jogo. " + (board.isCheckmate() ? "Checkmate!" : "Empate!"));
		scanner.close();
	}

}
