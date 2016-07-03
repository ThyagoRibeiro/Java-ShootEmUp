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
		Draw.drawString("Fase: " + Integer.toString(context.getPlayer().getMainGameScreen().getStageNumber() + 1), 180, 70);
		Draw.drawString("Inimigos: " + context.getPlayer().getMainGameScreen().getDeadEnemies() + "/" + context.getPlayer().getMainGameScreen().getNumberOfEnemies(), 180, 100);
	}

	@Override
	public void Update() {

	}
}
