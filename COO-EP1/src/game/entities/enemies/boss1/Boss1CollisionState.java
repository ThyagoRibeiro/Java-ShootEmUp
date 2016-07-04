package game.entities.enemies.boss1;

import game.entities.Entity;
import game.entities.Entity.EntityType;
import game.entities.collision.CollisionState;
import game.entities.projectiles.Projectile;
import game.entities.state.ExplodingState;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;

public class Boss1CollisionState implements CollisionState {

	private Boss1 context;

	public Boss1CollisionState(Boss1 context) {
		this.context = context;
	}

	@Override
	public void onCollision(Entity collider) {
		if (collider.getEntityType() == EntityType.FRIENDLY_PROJECTILE) {

			if (((Projectile) collider).getWeaponType().equals(
					WeaponType.PLAYER_MEGA_SHOT)) {
				context.lifeBar.decreaseCurrentHealthPoints();
			}

			context.lifeBar.decreaseCurrentHealthPoints();
			collider.remove();

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
