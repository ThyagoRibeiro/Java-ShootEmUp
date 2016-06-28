package game.entities.collision;

import game.entities.Entity;

public abstract class CollisionChecker {

	public static boolean CheckCollision(Entity e1, Entity e2) {
		if (e1 == e2)
			return false;
		return e1.getPosition().Distance(e2.getPosition()) < (e1.getRadius() + e2.getRadius());
	}
}