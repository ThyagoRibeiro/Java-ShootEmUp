package game.entities.collision;

import game.entities.Entity;

public interface CollisionState {

	public void onCollision(Entity collider);
}
