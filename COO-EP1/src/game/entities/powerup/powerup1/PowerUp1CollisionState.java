package game.entities.powerup.powerup1;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.player.Player;

public class PowerUp1CollisionState implements CollisionState {

	private PowerUp1 context;

	public PowerUp1CollisionState(PowerUp1 context) {
		this.context = context;
	}

	@Override
	public void onCollision(Entity collider) {
		switch (collider.getEntityType()) {
		case PLAYER:
			context.remove();
			((Player) collider).addShield();
			break;
		default:
			break;
		}
	}
}
