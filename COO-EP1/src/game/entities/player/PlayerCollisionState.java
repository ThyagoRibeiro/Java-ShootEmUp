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
	public void onCollision(Entity collider) {

		switch (collider.getEntityType()) {

		case ENEMY:
		case ENEMY_PROJECTILE:

			collider.remove();
			context.lifeBar.decreaseCurrentHealthPoints();

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
