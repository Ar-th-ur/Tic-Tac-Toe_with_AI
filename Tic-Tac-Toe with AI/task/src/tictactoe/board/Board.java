package tictactoe.board;

import tictactoe.game.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	private final Cell[][] matrix;
	private int countOfOccurredCells = 0;

	public Board() {
		this.matrix = new Cell[3][3];
		createBoard();
	}

	public void clear() {
		countOfOccurredCells = 0;
		createBoard();
	}

	public Cell[][] getBoard() {
		return Arrays.copyOf(matrix, matrix.length);
	}

	public void print() {
		System.out.println("---------");
		System.out.printf("| %s %s %s |\n", matrix[0][0], matrix[0][1], matrix[0][2]);
		System.out.printf("| %s %s %s |\n", matrix[1][0], matrix[1][1], matrix[1][2]);
		System.out.printf("| %s %s %s |\n", matrix[2][0], matrix[2][1], matrix[2][2]);
		System.out.println("---------");
	}

	public Cell atPosition(int y, int x) {
		return matrix[y][x];
	}

	public Cell atPosition(Coordinate coordinate) {
		return matrix[coordinate.getY()][coordinate.getX()];
	}

	public GameState put(Coordinate c, Cell cell) {
		int y = c.getY();
		int x = c.getX();

		if (!matrix[y][x].isEmpty()) {
			throw new IllegalArgumentException("This cell is occupied! Choose another one!");
		}

		matrix[y][x] = cell;
		countOfOccurredCells++;
		return getGameState();
	}

	private GameState getGameState() {
		if (isWon(matrix, Cell.X)) {
			return GameState.X_WON;
		}
		if (isWon(matrix, Cell.O)) {
			return GameState.O_WON;
		}
		if (isDraw()) {
			return GameState.DRAW;
		}
		return GameState.NOT_FINISHED;
	}

	public boolean isDraw() {
		return countOfOccurredCells == 9;
	}

	public List<Coordinate> getEmptyCells() {
		List<Coordinate> coordinates = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (matrix[i][j].isEmpty()) {
					coordinates.add(new Coordinate(i, j));
				}
			}
		}
		return coordinates;
	}

	/**
	 * @return true if present winning position with given cell
	 */
	public static boolean isWon(Cell[][] matrix, Cell cell) {
		return matrix[0][0] == cell && matrix[0][1] == cell && matrix[0][2] == cell ||
			matrix[1][0] == cell && matrix[1][1] == cell && matrix[1][2] == cell ||
			matrix[2][0] == cell && matrix[2][1] == cell && matrix[2][2] == cell ||
			matrix[0][0] == cell && matrix[1][0] == cell && matrix[2][0] == cell ||
			matrix[0][1] == cell && matrix[1][1] == cell && matrix[2][1] == cell ||
			matrix[0][2] == cell && matrix[1][2] == cell && matrix[2][2] == cell ||
			matrix[0][0] == cell && matrix[1][1] == cell && matrix[2][2] == cell ||
			matrix[2][0] == cell && matrix[1][1] == cell && matrix[0][2] == cell;
	}

	/**
	 * Creates board of empty cells
	 */
	private void createBoard() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = Cell.EMPTY;
			}
		}
	}
}
