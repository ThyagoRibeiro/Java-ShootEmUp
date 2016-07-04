package game.entities.powerup;

import game.entities.Entity;
import game.entities.collision.Collidable;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public abstract class PowerUp extends Entity implements Collidable {

	protected double angle;
	protected double rv;

	public PowerUp(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState, double angle, double rv) {
		super(null, velocity, radius, screenState);
		this.angle = angle;
		this.rv = rv;
		this.entityType = EntityType.POWER_UP;
		setPosition(position);
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

}
