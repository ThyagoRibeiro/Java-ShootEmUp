package game.entities.enemies.boss2;

import game.entities.Entity;
import game.entities.collisions.CollisionState;
import game.entities.constants.EntityTypeEnum;
import game.entities.constants.WeaponTypeEnum;
import game.entities.projectiles.Projectile;
import game.entities.states.ExplodingState;
import game.screenstate.MainGameScreen;

public class Boss2CollisionState implements CollisionState {

	private Boss2 context;
	
	// Construtor

	public Boss2CollisionState(Boss2 context) {
		this.context = context;
	}
	
	/* GETTERS E SETTERS - INICIO */

	public Boss2 getContext() {
		return context;
	}

	public void setContext(Boss2 context) {
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
