package game.entities.enemies.boss1;

import game.entities.Entity;
import game.entities.collision.CollisionChecker;
import game.entities.enemies.Enemy;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Boss1 extends Enemy {

	public Boss1(Vector2D position, ScreenState screenState) {
		super(position, new Vector2D((float) (0.05 + Math.random() * 0.10), (float) (0.05 + Math.random() * 0.10)), 30.0,
				screenState, 3 * Math.PI / 2, 0.0);

		this._state = new ActiveBoss1State(this);
		this._collision = new Boss1CollisionState(this);
	}

	@Override
	public void Update() {
		this._state.Update();
	}

	@Override
	public void Render() {
		this._state.Render();
	}

	@Override
	public boolean CheckCollision(Entity other) {
		return CollisionChecker.CheckCollision(this, other);
	}

}
