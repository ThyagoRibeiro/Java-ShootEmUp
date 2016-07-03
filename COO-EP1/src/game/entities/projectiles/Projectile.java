package game.entities.projectiles;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.collision.CollisionChecker;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Projectile extends Entity implements Collidable {

	private WeaponType type;

	public Projectile(Vector2D position, Vector2D velocity, ScreenState screenState, boolean playerMissile,
			WeaponType type, int radius) {

		super(position, velocity, radius, screenState);

		this.type = type;
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

	public WeaponType getType() {
		return type;
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
