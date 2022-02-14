package tictactoe.player.ai;

import tictactoe.board.Board;
import tictactoe.board.Cell;
import tictactoe.board.Coordinate;

import java.util.List;

public class AIMedium extends AIPlayer {
	private final List<List<Coordinate>> winningLines = List.of(
		List.of(new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2)),
		List.of(new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2)),
		List.of(new Coordinate(2, 0), new Coordinate(2, 1), new Coordinate(2, 2)),
		List.of(new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0)),
		List.of(new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(2, 1)),
		List.of(new Coordinate(0, 2), new Coordinate(1, 2), new Coordinate(2, 2)),
		List.of(new Coordinate(0, 0), new Coordinate(1, 1), new Coordinate(2, 2)),
		List.of(new Coordinate(2, 0), new Coordinate(1, 1), new Coordinate(0, 2))
	);

	@Override
	public Coordinate move(Board board) {
		System.out.println("Making move level \"medium\"");

		Cell opponentSign = mySign == Cell.X ? Cell.O : Cell.X;

		Coordinate posToWin = tryToComplete(board, mySign);
		if (posToWin != null) {
			return posToWin;
		}
		Coordinate posToBlock = tryToComplete(board, opponentSign);
		if (posToBlock != null) {
			return posToBlock;
		}
		return randomMove(board);
	}

	/**
	 * Finds the position that is needed to complete the line
	 **/
	private Coordinate tryToComplete(Board board, Cell my) {
		return winningLines.stream()
			.filter(winningLine -> winningLine.stream()
				.filter(position -> board.atPosition(position) != my)
				.count() == 1)
			.flatMap(line -> line.stream()
				.filter(position -> board.atPosition(position) == Cell.EMPTY))
			.findAny()
			.orElse(null);
	}
}
