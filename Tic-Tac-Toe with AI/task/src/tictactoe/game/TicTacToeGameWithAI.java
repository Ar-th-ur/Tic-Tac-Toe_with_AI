package tictactoe.game;

import tictactoe.board.Board;
import tictactoe.board.Cell;
import tictactoe.board.Coordinate;
import tictactoe.player.Player;
import tictactoe.player.ai.AIEasy;
import tictactoe.player.ai.AIHard;
import tictactoe.player.ai.AIMedium;
import tictactoe.player.human.HumanPlayer;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class TicTacToeGameWithAI {
	private final Board board;
	private final Player[] players;
	private int currentPlayer;

	public TicTacToeGameWithAI() {
		board   = new Board();
		players = new Player[2];
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			currentPlayer = 1;
			System.out.println("Input command: > ");
			String[] input = scanner.nextLine().split(" ");

			if (input[0].equals("exit")) {
				return;
			}

			if (input.length < 3) {
				System.out.println("Bad parameters!");
				continue;
			}

			try {
				players[0] = parseInput(input[1]);
				players[1] = parseInput(input[2]);
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				continue;
			}
			players[0].setSign(Cell.X);
			players[1].setSign(Cell.O);

			play();
		}
	}

	private Player parseInput(String str) {
		if (str.equals("user")) {
			return new HumanPlayer();
		}
		if (str.equals("easy")) {
			return new AIEasy();
		}
		if (str.equals("medium")) {
			return new AIMedium();
		}
		if (str.equals("hard")) {
			return new AIHard();
		}

		throw new NoSuchElementException("Bad parameters!");
	}

	public void play() {
		GameState state = GameState.NOT_FINISHED;

		while (state == GameState.NOT_FINISHED) {
			Player player = getNextPlayer();
			Coordinate position = player.move(board);
			state = board.put(position, player.getSign());
			board.print();
		}

		state.printMessage();
		board.clear();
	}

	private Player getNextPlayer() {
		currentPlayer = currentPlayer == 0 ? 1 : 0;
		return players[currentPlayer];
	}
}
