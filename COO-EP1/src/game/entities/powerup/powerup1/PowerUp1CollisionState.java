package game.entities.powerup.powerup1;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.player.Player;

public class PowerUp1CollisionState implements CollisionState {

	private PowerUp1 _context;

	public PowerUp1CollisionState(PowerUp1 context) {
		this._context = context;
	}

	@Override
	public void OnCollision(Entity collider) {
		switch (collider.getEntityType()) {
		case Player:
			_context.Remove();
			((Player) collider).addShield();
			break;
		default:
			break;
		}
	}
}
