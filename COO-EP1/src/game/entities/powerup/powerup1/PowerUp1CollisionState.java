package game.entities.powerup.powerup1;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.player.Player;

public class PowerUp1CollisionState implements CollisionState {

	private PowerUp1 context;

	// Construtor

	public PowerUp1CollisionState(PowerUp1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public PowerUp1 getContext() {
		return this.context;
	}

	public void setContext(PowerUp1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita da classe implementada CollisionState para tratar
	// especificamente o power up ao colidir.

	@Override
	public void onCollision(Entity collider) {
		switch (collider.getEntityType()) {
		case PLAYER:
			context.removeEntity();
			((Player) collider).addShield();
			break;
		default:
			break;
		}
	}
}
