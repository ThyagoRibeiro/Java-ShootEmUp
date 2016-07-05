package game.entities.enemies.enemy1;

import game.entities.Entity;
import game.entities.collisions.CollisionState;
import game.entities.constants.EntityTypeEnum;
import game.entities.constants.WeaponTypeEnum;
import game.entities.projectiles.Projectile;
import game.entities.states.ExplodingState;
import game.screenstate.MainGameScreen;

public class Enemy1CollisionState implements CollisionState {

	private Enemy1 context;

	// Construtor

	public Enemy1CollisionState(Enemy1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public Enemy1 getContext() {
		return context;
	}

	public void setContext(Enemy1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita da classe implementada CollisionState para tratar
	// especificamente o inimigo ao colidir.

	@Override
	public void onCollision(Entity collider) {

		if (collider.getEntityType() == EntityTypeEnum.FRIENDLY_PROJECTILE) {

			if (((Projectile) collider).getWeaponType().equals(
					WeaponTypeEnum.PLAYER_DEFAULT_SHOT))
				collider.removeEntity();

			context.setState(new ExplodingState(context, 400));
			((MainGameScreen) context.getScreenState()).enemyDied();

		}
	}
}
