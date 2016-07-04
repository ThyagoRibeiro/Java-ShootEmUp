package game.entities.enemies.enemy1;

import game.entities.Entity;
import game.entities.Entity.EntityType;
import game.entities.collision.CollisionState;
import game.entities.projectiles.Projectile;
import game.entities.state.ExplodingState;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;

public class Enemy1CollisionState implements CollisionState {

	private Enemy1 context;

	public Enemy1CollisionState(Enemy1 context) {
		this.context = context;
	}

	@Override
	public void onCollision(Entity collider) {

		if (collider.getEntityType() == EntityType.FRIENDLY_PROJECTILE) {

			if (((Projectile) collider).getWeaponType().equals(
					WeaponType.PLAYER_DEFAULT_SHOT))
				collider.remove();

			context.setState(new ExplodingState(context, 400));
			((MainGameScreen) context.getScreenState()).enemyDied();

		}
	}
}
