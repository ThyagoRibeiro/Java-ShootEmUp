package game.entities.powerups;

import game.Vector2D;
import game.entities.Entity;
import game.entities.collisions.Collidable;
import game.entities.constants.EntityTypeEnum;
import game.screenstate.ScreenState;

public abstract class PowerUp extends Entity implements Collidable {

	protected double angle;
	protected double rv;

	// Construtor

	public PowerUp(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState, double angle, double rv) {
		super(null, velocity, radius, screenState);
		this.angle = angle;
		this.rv = rv;
		this.entityType = EntityTypeEnum.POWER_UP;
		setPosition(position);
	}

	/* GETTERS E SETTERS - INICIO */

	public double getAngle() {
		return this.angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getRV() {
		return this.rv;
	}

	public void setRV(double rv) {
		this.rv = rv;
	}

	/* GETTERS E SETTERS - FIM */

}
