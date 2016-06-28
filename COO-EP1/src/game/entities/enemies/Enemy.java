package game.entities.enemies;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.weapons.Weapon;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public abstract class Enemy extends Entity implements Collidable {

	protected double _angle, _rv;
	protected Weapon _weapon;

	public Enemy(Vector2D position, Vector2D velocity, double radius, ScreenState screenState, double angle,
			double rv) {
		super(position, velocity, radius, screenState);
		this._angle = angle;
		this._rv = rv;
		this._type = EntityType.Enemy;
	}

	public double getAngle() {
		return this._angle;
	}

	public double getRV() {
		return this._rv;
	}

	public void setRv(double rv) {
		this._rv = rv;
	}

	public void setAngle(double angle) {
		this._angle = angle;
	}

	public void setWeapon(Weapon weapon) {
		this._weapon = weapon;
	}

	public void Shoot() {
		if (_weapon == null)
			return;
		_weapon.Shoot();
	}
}
