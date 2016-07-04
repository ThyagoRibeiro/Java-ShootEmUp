package game.entities.hud;

import game.GameLib;
import game.entities.Entity.EntityType;
import game.entities.state.EntityState;

import java.awt.Color;

public class ActiveLifeBarState implements EntityState {

	private LifeBar context;

	public ActiveLifeBarState(LifeBar context) {
		this.context = context;
	}

	@Override
	public void render() {
		if (context.getCharacterType().equals(EntityType.PLAYER)) {
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
