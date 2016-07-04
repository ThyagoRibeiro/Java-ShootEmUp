package game.entities.hud;

import game.GameLib;
import game.entities.state.EntityState;

public class ActiveInfosState implements EntityState {

	private Infos context;

	public ActiveInfosState(Infos context) {
		this.context = context;
	}

	@Override
	public void render() {
		GameLib.drawString(
				"Fase: "
						+ Integer.toString(context.getPlayer()
								.getMainGameScreen().getStageNumber() + 1),
				180, 70);
		GameLib.drawString("Inimigos: "
				+ context.getPlayer().getMainGameScreen().getDeadEnemies()
				+ "/"
				+ context.getPlayer().getMainGameScreen().getNumberOfEnemies(),
				180, 100);
	}

	@Override
	public void update() {

	}
}
