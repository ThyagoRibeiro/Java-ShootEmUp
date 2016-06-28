package game.entities.hud;

import game.entities.state.EntityState;
import game.util.Draw;

public class ActiveTimeState implements EntityState {

	private Time context;

	public ActiveTimeState(Time context) {
		this.context = context;
	}

	@Override
	public void Update() {

	}

	@Override
	public void Render() {
		Draw.drawTime(180, 70, context.getTime());
	}
}
