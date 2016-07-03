package game.entities.powerup;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public abstract class PowerUp extends Entity implements Collidable {

	protected double _angle, _rv;

	public PowerUp(Vector2D position, Vector2D velocity, double radius, ScreenState screenState, double angle,
			double rv) {
		super(null, velocity, radius, screenState);
		this._angle = angle;
		this._rv = rv;
		this._type = EntityType.PowerUp;
		setPosition(position);
	}

	public double getAngle() {
		return this._angle;
	}

	public double getRV() {
		return this._rv;
	}

	public void setAngle(double angle) {
		this._angle = angle;
	}

	public void setRv(double rv) {
		this._rv = rv;
	}

}
