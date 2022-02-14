package tictactoe.player;

import tictactoe.board.Board;
import tictactoe.board.Cell;
import tictactoe.board.Coordinate;

public interface Player {

	Coordinate move(Board board);

	void setSign(Cell mySign);

	Cell getSign();
}
