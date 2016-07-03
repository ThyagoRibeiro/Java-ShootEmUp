package game.entities.hud;

import java.awt.Color;

import game.entities.Entity.EntityType;
import game.entities.state.EntityState;
import game.util.Draw;

public class ActiveLifeBarState implements EntityState {

	private LifeBar context;

	public ActiveLifeBarState(LifeBar context) {
		this.context = context;
	}

	@Override
	public void Render() {
		if (context.getCharacterType().equals(EntityType.Player)) {
			Draw.setColor(Color.BLUE);
			Draw.drawLifeBar(10, 70, 150, 30, context.getLifePointsPercent(), "Player");
		} else {
			Draw.setColor(Color.RED);
			Draw.drawLifeBar(320, 70, 150, 30, context.getLifePointsPercent(), "Chefe");
		}
	}

	@Override
	public void Update() {

	}
}
