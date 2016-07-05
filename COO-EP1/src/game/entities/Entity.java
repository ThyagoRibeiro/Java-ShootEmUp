package game.entities;

import game.Vector2D;
import game.entities.collisions.CollisionState;
import game.entities.constants.EntityTypeEnum;
import game.entities.states.EntityState;
import game.screenstate.ScreenState;
import game.utils.Time;

public abstract class Entity {

	protected Vector2D position;
	protected Vector2D velocity;
	protected double radius;
	protected ScreenState screenState;
	protected EntityState state;
	protected CollisionState collisionState;
	protected EntityTypeEnum entityType = EntityTypeEnum.NONE;
	
	// Construtor

	public Entity(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState) {
		this.position = position;
		this.velocity = velocity;
		this.radius = radius;
		this.screenState = screenState;
		screenState.getEntityManager().addEntity(this);
		state = null;
		collisionState = null;
	}

	/* GETTERS E SETTERS - INICIO */
	
	public Vector2D getPosition() {
		return this.position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public void setPositionX(float x) {
		this.position.setCoordX(x);
	}

	public void setPositionY(float y) {
		this.position.setCoordY(y);
	}

	public Vector2D getVelocity() {
		return this.velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public double getRadius() {
		return this.radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public ScreenState getScreenState() {
		return this.screenState;
	}

	public void setScreenState(ScreenState screenState) {
		this.screenState = screenState;
	}

	public EntityState getState() {
		return this.state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public CollisionState getCollissionState() {
		return this.collisionState;
	}

	public void setCollisionState(CollisionState collisionState) {
		this.collisionState = collisionState;
	}

	public EntityTypeEnum getEntityType() {
		return this.entityType;
	}

	public void setEntityType(EntityTypeEnum entityType) {
		this.entityType = entityType;
	}
	
	/* GETTERS E SETTERS - FIM */
	
	// Metodo que remove a entidade através do EntityManager.

	public void removeEntity() {
		screenState.getEntityManager().remove(this);
	}
	
	// Metodo que atualiza a posição da entidade.

	public void updateEntityPosition() {
		this.position = this.position.add(velocity.multiply(Time.getInstance()
				.deltaTime()));
	}

	// Metodos abstratos de renderização e atualização

	public abstract void render();

	public abstract void update();

	// Metodos sobrescritos da classe Object.

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

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + Float.floatToIntBits((float) radius);
		result = prime * result
				+ ((velocity == null) ? 0 : velocity.hashCode());
		return result;
	}

}
