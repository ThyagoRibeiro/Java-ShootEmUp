package game.entities.projectiles;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.collision.CollisionChecker;
import game.entities.player.Player;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Projectile extends Entity implements Collidable {

	private WeaponType type;
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public Projectile(Vector2D position, Vector2D velocity, ScreenState screenState, boolean playerMissile,
			WeaponType type, int radius, Player player) {

		super(position, velocity, radius, screenState);

		if(player != null){
			setPosition(player.getPosition());
			this.player = player;
		}
		
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
