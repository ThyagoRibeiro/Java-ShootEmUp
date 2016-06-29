package game.entities.hud;

import game.entities.state.EntityState;
import game.util.Draw;

public class ActiveInfosState implements EntityState {

	private Infos context;

	public ActiveInfosState(Infos context) {
		this.context = context;
	}

	@Override
	public void Render() {
//		System.out.println("time: " + context.getTime() + 1);	

//		Draw.drawTwoStrings("Time", Integer.toString(context.getTime()), 170, 70);
		Draw.drawTwoStrings("Stage", Integer.toString(context.getStageNumber()), 180, 80);
	}

	@Override
	public void Update() {

	}
}
