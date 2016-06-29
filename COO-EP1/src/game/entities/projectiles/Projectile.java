package game.entities.projectiles;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.collision.CollisionChecker;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Projectile extends Entity implements Collidable {

	public Projectile(Vector2D position, Vector2D velocity, ScreenState screenState, boolean playerMissile) {
		super(position, velocity, 2.0, screenState);
		if (playerMissile) {
			this._type = EntityType.FriendlyProjectile;
			velocity.setYToNegative();
		} else {
			this._type = EntityType.EnemyProjectile;
			velocity.setYToPositive();
		}
		this._state = new ActiveProjectileState(this);
	}

	@Override
	public boolean CheckCollision(Entity other) {
		return CollisionChecker.CheckCollision(this, other);
	}

	@Override
	public void Render() {
		this._state.Render();
	}

	@Override
	public void Update() {
		this._state.Update();
	}

}
