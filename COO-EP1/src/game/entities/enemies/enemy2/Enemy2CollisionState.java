package game.entities.enemies.enemy2;

import game.entities.Entity;
import game.entities.Entity.EntityType;
import game.entities.collision.CollisionState;
import game.entities.projectiles.Projectile;
import game.entities.state.ExplodingState;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;

public class Enemy2CollisionState implements CollisionState {

	private Enemy2 context;

	public Enemy2CollisionState(Enemy2 context) {
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
