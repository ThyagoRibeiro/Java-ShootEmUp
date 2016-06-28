package game.util;

public final class Input {

	private static Input _instance = new Input();

	public static Input getInstance() {
		if (_instance == null)
			_instance = new Input();
		return _instance;
	}

	private Input() {

	}

}
