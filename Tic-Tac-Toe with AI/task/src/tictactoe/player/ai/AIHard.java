package tictactoe.player.ai;

import tictactoe.board.Board;
import tictactoe.board.Cell;
import tictactoe.board.Coordinate;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AIHard extends AIPlayer {

	@Override
	public Coordinate move(Board board) {
		System.out.println("Making move level \"hard\"");
		int bestScore = Integer.MIN_VALUE;
		Cell[][] boardCopy = board.getBoard();
		int[] move = {0, 0};
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (boardCopy[i][j] == Cell.EMPTY) {
					boardCopy[i][j] = mySign;
					int score = minimax(boardCopy, false);
					boardCopy[i][j] = Cell.EMPTY;
					if (score > bestScore) {
						bestScore = score;
						move[0]   = i;
						move[1]   = j;
					}
				}
			}
		}
		return new Coordinate(move[0], move[1]);
	}

	private int minimax(Cell[][] board, boolean isMaximizing) {
		if (Board.isWon(board, mySign)) {
			return 10;
		}
		if (Board.isWon(board, opponentSign)) {
			return -10;
		}
		if (isDraw(board)) {
			return 0;
		}

		int bestScore;
		if (isMaximizing) {
			bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == Cell.EMPTY) {
						board[i][j] = mySign;
						int score = minimax(board, false);
						board[i][j] = Cell.EMPTY;
						              bestScore = max(score, bestScore);
					}
				}
			}
		} else {
			bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == Cell.EMPTY) {
						board[i][j] = opponentSign;
						int score = minimax(board, true);
						board[i][j] = Cell.EMPTY;
						              bestScore = min(score, bestScore);
					}
				}
			}
		}
		return bestScore;
	}

	private boolean isDraw(Cell[][] matrix) {
		return Arrays.stream(matrix)
			.flatMap(Arrays::stream)
			.noneMatch(Cell::isEmpty);
	}
}
