package game.screenstate;

import game.GameLib;

public class ScreenContext {

	private ScreenState _state = null;

	public ScreenContext() {
		setState(new MainGameScreen());
	}

	public void Update() {
		_state.Update(this);
	}

	public void Draw() {
		_state.Draw(this);
		GameLib.display();
	}

	public void setState(ScreenState state) {
		if (this._state != null)
			this._state.OnLeave(this);
		this._state = state;
		this._state.OnEnter(this);
	}

	public ScreenState getState() {
		return this._state;
	}
}
