package game.entities.enemies;

import game.Vector2D;
import game.entities.Entity;
import game.entities.collisions.Collidable;
import game.entities.constants.EntityTypeEnum;
import game.entities.weapons.Weapon;
import game.screenstate.ScreenState;

public abstract class Enemy extends Entity implements Collidable {

	protected double angle;
	protected double rv;
	protected Weapon weapon;
	
	// Construtor

	public Enemy(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState, double angle, double rv) {
		super(position, velocity, radius, screenState);
		this.angle = angle;
		this.rv = rv;
		this.entityType = EntityTypeEnum.ENEMY;
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

	public void setRv(double rv) {
		this.rv = rv;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	/* GETTERS E SETTERS - INICIO */
	
	// Metodo que faz atirar.

	public void shoot() {
		if (weapon == null) {
			return;
		}
		weapon.shoot();
	}
}
