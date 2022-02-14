package tictactoe.player.ai;

import tictactoe.board.Board;
import tictactoe.board.Cell;
import tictactoe.board.Coordinate;
import tictactoe.player.Player;

import java.util.List;
import java.util.Random;

public abstract class AIPlayer implements Player {
	private final Random random = new Random();
	protected Cell mySign;
	protected Cell opponentSign;

	protected Coordinate randomMove(Board board) {
		List<Coordinate> emptyCells = board.getEmptyCells();
		return emptyCells.get(random.nextInt(emptyCells.size()));
	}

	@Override
	public void setSign(Cell mySign) {
		this.mySign  = mySign;
		opponentSign = mySign == Cell.X ? Cell.O : Cell.X;
	}

	@Override
	public Cell getSign() {
		return mySign;
	}
}
