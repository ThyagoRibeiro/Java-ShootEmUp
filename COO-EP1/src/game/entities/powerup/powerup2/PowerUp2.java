package game.entities.powerup.powerup2;

import game.entities.Entity;
import game.entities.collision.CollisionChecker;
import game.entities.powerup.PowerUp;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class PowerUp2 extends PowerUp {

	public PowerUp2(Vector2D position, ScreenState screenState, MainGameScreen mainGameScreen) {
		super(position, new Vector2D((float) (0.05 + Math.random() * 0.10), (float) (0.05 + Math.random() * 0.10)), 9.0,
				screenState, 3 * Math.PI / 2, 0.0);
		this._state = new ActivePowerUp2State(this);
		this._collision = new PowerUp2CollisionState(this);
	}

	@Override
	public boolean CheckCollision(Entity other) {
		return CollisionChecker.CheckCollision(this, other);
	}

	@Override
	public void Render() {
		this._state.Render();
	}

	@Override
	public void Update() {
		this._state.Update();
	}

}
