package game.entities.collision;

import game.entities.Entity;

public abstract class CollisionChecker {

	// Verifica colisao. Se o objeto for o mesmo, retorna falso, se nao, calcula
	// a distancia entre o diametro das entidades. se forem iguais, significa
	// que teve colisao, se nao, elas nao colidiram.

	public static boolean checkCollision(Entity e1, Entity e2) {
		if (e1.equals(e2)) {
			return false;
		}
		return e1.getPosition().distance(e2.getPosition()) < (e1.getRadius() + e2
				.getRadius());
	}

}
