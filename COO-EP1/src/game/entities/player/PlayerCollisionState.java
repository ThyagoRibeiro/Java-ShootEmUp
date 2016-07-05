package game.entities.player;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.state.ExplodingState;

public class PlayerCollisionState implements CollisionState {

	private Player context;

	// Construtor

	public PlayerCollisionState(Player context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public Player getContext() {
		return context;
	}

	public void setContext(Player context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita da classe implementada CollisionState para tratar
	// especificamente o jogador ao colidir.

	@Override
	public void onCollision(Entity collider) {

		switch (collider.getEntityType()) {

		case ENEMY:
		case ENEMY_PROJECTILE:

			collider.removeEntity();
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
