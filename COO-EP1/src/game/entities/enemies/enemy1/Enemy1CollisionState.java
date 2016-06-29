package game.entities.enemies.enemy1;

import game.entities.Entity;
import game.entities.Entity.EntityType;
import game.entities.collision.CollisionState;
import game.entities.state.ExplodingState;
import game.screenstate.MainGameScreen;

public class Enemy1CollisionState implements CollisionState {

	private Enemy1 _context;

	public Enemy1CollisionState(Enemy1 context) {
		this._context = context;
	}

	@Override
	public void OnCollision(Entity collider) {
		if (collider.getEntityType() == EntityType.FriendlyProjectile) {
			collider.Remove();
			_context.setState(new ExplodingState(_context, 400));
			((MainGameScreen) _context.getScreenState()).enemyDied();

		}
	}
}
