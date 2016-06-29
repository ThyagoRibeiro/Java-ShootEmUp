package game.entities;

import game.entities.collision.CollisionState;
import game.entities.state.EntityState;
import game.screenstate.ScreenState;
import game.util.Time;
import geometry.Vector2D;

public abstract class Entity {

	public enum EntityType {
		Enemy, EnemyProjectile, FriendlyProjectile, HUD, None, Player
	}

	protected CollisionState _collision;
	protected Vector2D _position, _velocity;
	protected double _radius;
	protected ScreenState _screenState;
	protected EntityState _state;

	protected EntityType _type;;

	public Entity(Vector2D position, Vector2D velocity, double radius, ScreenState screenState) {
		this._screenState = screenState;
		_screenState.getEntityManager().addEntity(this);
		_state = null;
		_collision = null;
		this._position = position;
		this._velocity = velocity;
		this._radius = radius;
		this._type = EntityType.None;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (_position == null) {
			if (other._position != null)
				return false;
		} else if (!_position.equals(other._position))
			return false;
		if (Float.floatToIntBits((float) _radius) != Float.floatToIntBits((float) other._radius))
			return false;
		if (_velocity == null) {
			if (other._velocity != null)
				return false;
		} else if (!_velocity.equals(other._velocity))
			return false;
		return true;
	}

	public EntityType getEntityType() {
		return this._type;
	}

	public Vector2D getPosition() {
		return this._position;
	}

	public double getRadius() {
		return this._radius;
	}

	public ScreenState getScreenState() {
		return this._screenState;
	}

	public EntityState getState() {
		return this._state;
	}

	public Vector2D getVelocity() {
		return this._velocity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_position == null) ? 0 : _position.hashCode());
		result = prime * result + Float.floatToIntBits((float) _radius);
		result = prime * result + ((_velocity == null) ? 0 : _velocity.hashCode());
		return result;
	}

	public void Remove() {
		_screenState.getEntityManager().Remove(this);
	}

	public abstract void Render();

	public void setCollisionState(CollisionState collisionState) {
		this._collision = collisionState;
	}

	public void setPosition(Vector2D position) {
		this._position = position;
	}

	public void setState(EntityState state) {

		this._state = state;
	}

	public void setX(float x) {
		this._position.setX(x);
	}

	public void setY(float y) {
		this._position.setY(y);
	}

	public abstract void Update();

	public void UpdatePosition() {
		this._position = this._position.Add(_velocity.Multiply(Time.getInstance().DeltaTime()));
	}

}
