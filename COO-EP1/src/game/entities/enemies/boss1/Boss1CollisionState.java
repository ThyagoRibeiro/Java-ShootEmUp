package game.entities.enemies.boss1;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.constants.EntityTypeEnum;
import game.entities.constants.WeaponTypeEnum;
import game.entities.projectiles.Projectile;
import game.entities.state.ExplodingState;
import game.screenstate.MainGameScreen;

public class Boss1CollisionState implements CollisionState {

	private Boss1 context;

	// Construtor

	public Boss1CollisionState(Boss1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public Boss1 getContext() {
		return context;
	}

	public void setContext(Boss1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita da classe implementada CollisionState para tratar
	// especificamente o chefe ao colidir.

	@Override
	public void onCollision(Entity collider) {
		if (collider.getEntityType() == EntityTypeEnum.FRIENDLY_PROJECTILE) {

			if (((Projectile) collider).getWeaponType().equals(
					WeaponTypeEnum.PLAYER_MEGA_SHOT)) {
				context.lifeBar.decreaseCurrentHealthPoints();
			}

			context.lifeBar.decreaseCurrentHealthPoints();
			collider.removeEntity();

			if (context.isDead()) {
				context.setState(new ExplodingState(context, 400));
				((MainGameScreen) context.getScreenState()).enemyDied();
				((MainGameScreen) context.getScreenState()).setBossIsDead(true);
			} else {
				context.getHit();
			}
		}
	}
}
