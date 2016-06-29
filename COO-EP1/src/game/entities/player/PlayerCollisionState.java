package game.entities.player;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.state.ExplodingState;

public class PlayerCollisionState implements CollisionState {

	private Player context;

	public PlayerCollisionState(Player context) {
		this.context = context;
	}

	@Override
	public void OnCollision(Entity collider) {

		switch (collider.getEntityType()) {

		case Enemy:
		case EnemyProjectile:

			collider.Remove();
			context._lifeBar.decreaseCurrentHealthPoints();

			if (context.isDead()) {
				context.setState(new ExplodingState(context, 600));
			} else {
				context.getHit();
			}

			break;
		default:
			break;
		}
	}
}
