package game.entities.enemies;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.weapons.Weapon;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public abstract class Enemy extends Entity implements Collidable {

	protected double angle;
	protected double rv;
	protected Weapon weapon;

	public Enemy(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState, double angle, double rv) {
		super(position, velocity, radius, screenState);
		this.angle = angle;
		this.rv = rv;
		this.entityType = EntityType.ENEMY;
	}

	public double getAngle() {
		return this.angle;
	}

	public double getRV() {
		return this.rv;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setRv(double rv) {
		this.rv = rv;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void shoot() {
		if (weapon == null) {
			return;
		}
		weapon.shoot();
	}
}
