package game.entities;

import game.entities.collision.CollisionState;
import game.entities.state.EntityState;
import game.screenstate.ScreenState;
import game.util.Time;
import geometry.Vector2D;

public abstract class Entity {

	public enum EntityType {
		NONE, HUD, PLAYER, FRIENDLY_PROJECTILE, POWER_UP, ENEMY, ENEMY_PROJECTILE
	}

	protected CollisionState collision;
	protected Vector2D position;
	protected Vector2D velocity;
	protected double radius;
	protected ScreenState screenState;
	protected EntityState state;

	protected EntityType entityType;;

	public Entity(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState) {
		this.screenState = screenState;
		screenState.getEntityManager().addEntity(this);
		state = null;
		collision = null;
		this.position = position;
		this.velocity = velocity;
		this.radius = radius;
		this.entityType = EntityType.NONE;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Entity other = (Entity) obj;
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		if (Float.floatToIntBits((float) radius) != Float
				.floatToIntBits((float) other.radius)) {
			return false;
		}
		if (velocity == null) {
			if (other.velocity != null) {
				return false;
			}
		} else if (!velocity.equals(other.velocity)) {
			return false;
		}
		return true;
	}

	public EntityType getEntityType() {
		return this.entityType;
	}

	public Vector2D getPosition() {
		return this.position;
	}

	public double getRadius() {
		return this.radius;
	}

	public ScreenState getScreenState() {
		return this.screenState;
	}

	public EntityState getState() {
		return this.state;
	}

	public Vector2D getVelocity() {
		return this.velocity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + Float.floatToIntBits((float) radius);
		result = prime * result
				+ ((velocity == null) ? 0 : velocity.hashCode());
		return result;
	}

	public void remove() {
		screenState.getEntityManager().remove(this);
	}

	public abstract void render();

	public void setCollisionState(CollisionState collisionState) {
		this.collision = collisionState;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public void setState(EntityState state) {

		this.state = state;
	}

	public void setX(float x) {
		this.position.setX(x);
	}

	public void setY(float y) {
		this.position.setY(y);
	}

	public abstract void update();

	public void updatePosition() {
		this.position = this.position.add(velocity.multiply(Time.getInstance()
				.deltaTime()));
	}

}
