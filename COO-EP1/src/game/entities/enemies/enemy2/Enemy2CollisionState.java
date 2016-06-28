package game.entities.enemies.enemy2;

import game.entities.Entity;
import game.entities.Entity.EntityType;
import game.entities.collision.CollisionState;
import game.entities.state.ExplodingState;

public class Enemy2CollisionState implements CollisionState {

	private Enemy2 context;

	public Enemy2CollisionState(Enemy2 context) {
		this.context = context;
	}

	@Override
	public void OnCollision(Entity collider) {
		if (collider.getEntityType() == EntityType.FriendlyProjectile) {
			collider.Remove();
			context.setState(new ExplodingState(context, 400));
		}
	}

}
