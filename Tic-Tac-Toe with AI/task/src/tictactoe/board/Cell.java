package tictactoe.board;

public enum Cell {
	X("X"),
	O("O"),
	EMPTY(" ");

	private final String sign;

	Cell(String sign) {
		this.sign = sign;
	}

	public boolean isEmpty() {
		return this == EMPTY;
	}

	@Override
	public String toString() {
		return sign;
	}
}
