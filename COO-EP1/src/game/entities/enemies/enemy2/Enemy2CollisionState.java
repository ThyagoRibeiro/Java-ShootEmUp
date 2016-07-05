package game.entities.enemies.enemy2;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.constants.EntityTypeEnum;
import game.entities.constants.WeaponTypeEnum;
import game.entities.projectiles.Projectile;
import game.entities.state.ExplodingState;
import game.screenstate.MainGameScreen;

public class Enemy2CollisionState implements CollisionState {

	private Enemy2 context;

	// Construtor

	public Enemy2CollisionState(Enemy2 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public Enemy2 getContext() {
		return context;
	}

	public void setContext(Enemy2 context) {
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
