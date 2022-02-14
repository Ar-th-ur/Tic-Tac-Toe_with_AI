package tictactoe.game;

public enum GameState {
	X_WON("X wins"),
	O_WON("O wins"),
	DRAW("Draw"),
	NOT_FINISHED("Game not finished");

	private final String message;

	GameState(String message) {
		this.message = message;
	}

	public void printMessage() {
		System.out.println(message);
	}
}
