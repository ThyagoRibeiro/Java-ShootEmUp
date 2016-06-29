package game.screenstate;

import java.util.ArrayList;

import game.GameLib;
import stage.Stage;

public class ScreenContext {

	private ScreenState _state = null;

	public ScreenContext(int playerHealthPoints, ArrayList<Stage> stages) {
		setState(new MainGameScreen(playerHealthPoints, stages, 0));
	}

	public void Draw() {
		_state.Draw(this);
		GameLib.display();
	}

	public ScreenState getState() {
		return this._state;
	}

	public void setState(ScreenState state) {
		if (this._state != null)
			this._state.OnLeave(this);
		this._state = state;
		this._state.OnEnter(this);
	}

	public void Update() {
		_state.Update(this);
	}
}
