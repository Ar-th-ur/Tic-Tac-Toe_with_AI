package tictactoe.player.ai;

import tictactoe.board.Board;
import tictactoe.board.Coordinate;

public class AIEasy extends AIPlayer {
	@Override
	public Coordinate move(Board board) {
		System.out.println("Making move level \"easy\"");
		return randomMove(board);
	}
}
