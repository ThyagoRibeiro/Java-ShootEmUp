package game.entities.hud;

import game.GameLib;
import game.entities.state.EntityState;

public class ActiveInfosState implements EntityState {

	private Infos context;

	// Construtor

	public ActiveInfosState(Infos context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public Infos getContext() {
		return this.context;
	}

	public void setContext(Infos context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o chefe
	// de maneira especifica.

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
