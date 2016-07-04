package game.screenstate;

import java.util.ArrayList;

import game.GameLib;
import stage.Stage;

public class ScreenContext {

	private ScreenState state = null;

	public ScreenContext(int playerHealthPoints, ArrayList<Stage> stages) {
		setState(new MainGameScreen(playerHealthPoints, stages, 0));
	}

	public void draw() {
		state.draw(this);
		GameLib.display();
	}

	public ScreenState getState() {
		return this.state;
	}

	public void setState(ScreenState state) {
		if (this.state != null)
			this.state.onLeave(this);
		this.state = state;
		this.state.onEnter(this);
	}

	public void update() {
		state.update(this);
	}
}
