package game.entities.collision;

import game.entities.Entity;

public interface Collidable {

	public boolean checkCollision(Entity other);
}
