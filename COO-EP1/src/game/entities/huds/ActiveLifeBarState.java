package game.entities.huds;

import game.GameLib;
import game.entities.constants.EntityTypeEnum;
import game.entities.states.EntityState;

import java.awt.Color;

public class ActiveLifeBarState implements EntityState {

	private LifeBar context;

	// Construtor

	public ActiveLifeBarState(LifeBar context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public LifeBar getContext() {
		return this.context;
	}

	public void setContext(LifeBar context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o chefe
	// de maneira especifica.

	@Override
	public void render() {
		if (context.getCharacterType().equals(EntityTypeEnum.PLAYER)) {
			GameLib.setColor(Color.BLUE);
			GameLib.drawLifeBar(10, 70, 150, 30,
					context.getLifePointsPercent(), "Player");
		} else {
			GameLib.setColor(Color.RED);
			GameLib.drawLifeBar(320, 70, 150, 30,
					context.getLifePointsPercent(), "Chefe");
		}
	}

	@Override
	public void update() {
	}
}
