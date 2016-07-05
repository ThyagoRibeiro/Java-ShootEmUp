package game.entities.collisions;

import game.entities.Entity;

public interface CollisionState {

	// Interface para ser implementada pela classe referente a ação ao colidir.
	// A sobrescrita do método é o que trata a colisao para cada entidade
	// específica.

	public void onCollision(Entity collider);
}
