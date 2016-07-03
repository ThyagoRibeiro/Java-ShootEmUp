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
	public void OnCollision(Entity collider) {
		if (collider.getEntityType() == EntityType.FriendlyProjectile) {

			if (((Projectile) collider).getType().equals(WeaponType.PlayerDeafultShot))
				collider.Remove();
			
			context.setState(new ExplodingState(context, 400));
			((MainGameScreen) context.getScreenState()).enemyDied();

		}
	}

}
