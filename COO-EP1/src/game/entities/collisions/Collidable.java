package game.entities.collisions;

import game.entities.Entity;

public interface Collidable {

	// Interface para ser implementada nas classes em que é necessario checar
	// colisoes.

	public boolean checkCollision(Entity other);

}
