package game.entities.projectiles;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.collision.CollisionChecker;
import game.entities.player.Player;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Projectile extends Entity implements Collidable {

	private WeaponType weaponType;
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public Projectile(Vector2D position, Vector2D velocity,
			ScreenState screenState, boolean playerMissile,
			WeaponType weaponType, int radius, Player player) {

		super(position, velocity, radius, screenState);

		if (player != null) {
			setPosition(player.getPosition());
			this.player = player;
		}

		this.weaponType = weaponType;
		if (playerMissile) {
			this.entityType = EntityType.FRIENDLY_PROJECTILE;
			velocity.setYToNegative();
		} else {
			this.entityType = EntityType.ENEMY_PROJECTILE;
			velocity.setYToPositive();
		}
		this.state = new ActiveProjectileState(this);
	}

	@Override
	public boolean checkCollision(Entity other) {
		return CollisionChecker.checkCollision(this, other);
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
