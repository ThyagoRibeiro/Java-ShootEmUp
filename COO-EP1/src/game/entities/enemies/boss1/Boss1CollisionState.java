package game.entities.enemies.boss1;

import game.entities.Entity;
import game.entities.Entity.EntityType;
import game.entities.collision.CollisionState;
import game.entities.state.ExplodingState;
import game.screenstate.MainGameScreen;

public class Boss1CollisionState implements CollisionState {

	private Boss1 _context;

	public Boss1CollisionState(Boss1 context) {
		this._context = context;
	}

	@Override
	public void OnCollision(Entity collider) {
		if (collider.getEntityType() == EntityType.FriendlyProjectile) {
			collider.Remove();

			_context._lifeBar.decreaseCurrentHealthPoints();

			if (_context.isDead()) {
				_context.setState(new ExplodingState(_context, 400));
				((MainGameScreen)_context.getScreenState()).enemyDied();
				((MainGameScreen)_context.getScreenState()).setBossIsDead(true);
			} else {
				_context.getHit();
			}
		}
	}
}
