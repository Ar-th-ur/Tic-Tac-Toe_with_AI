package tictactoe.player.human;

import tictactoe.board.Board;
import tictactoe.board.Cell;
import tictactoe.board.Coordinate;
import tictactoe.player.Player;

import java.util.Scanner;

public class HumanPlayer implements Player {

	private final Scanner scanner = new Scanner(System.in);
	private Cell sign;

	@Override
	public Coordinate move(Board board) {
		board.print();
		while (true) {
			System.out.print("Enter the coordinates: > ");
			String[] input = scanner.nextLine().split(" ");

			try {
				int y = Integer.parseInt(input[0]) - 1;
				int x = Integer.parseInt(input[1]) - 1;

				if (isOutOfBounds(x) || isOutOfBounds(y)) {
					System.out.println("Coordinates should be from 1 to 3!");
					continue;
				}

				if (!board.atPosition(y, x).isEmpty()) {
					System.out.println("This cell is occupied! Choose another one!");
					continue;
				}

				return new Coordinate(y, x);
			} catch (NumberFormatException e) {
				System.out.println("You should enter numbers!");
			}
		}
	}

	@Override
	public void setSign(Cell mySign) {
		this.sign = mySign;
	}

	@Override
	public Cell getSign() {
		return sign;
	}

	private boolean isOutOfBounds(int number) {
		return number < 0 || number > 2;
	}
}
