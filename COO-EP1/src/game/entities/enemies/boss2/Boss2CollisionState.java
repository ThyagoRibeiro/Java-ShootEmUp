package game.entities.enemies.boss2;

import game.entities.Entity;
import game.entities.Entity.EntityType;
import game.entities.collision.CollisionState;
import game.entities.state.ExplodingState;
import game.screenstate.MainGameScreen;

public class Boss2CollisionState implements CollisionState {

	private Boss2 _context;

	public Boss2CollisionState(Boss2 context) {
		this._context = context;
	}

	@Override
	public void OnCollision(Entity collider) {
		if (collider.getEntityType() == EntityType.FriendlyProjectile) {
			collider.Remove();

			_context._lifeBar.decreaseCurrentHealthPoints();

			if (_context.isDead()) {
				_context.setState(new ExplodingState(_context, 400));
				((MainGameScreen) _context.getScreenState()).enemyDied();
				((MainGameScreen) _context.getScreenState()).setBossIsDead(true);
			} else {
				_context.getHit();
			}
		}
	}
}
