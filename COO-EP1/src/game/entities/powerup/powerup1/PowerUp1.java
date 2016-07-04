package game.entities.powerup.powerup1;

import game.entities.Entity;
import game.entities.collision.CollisionChecker;
import game.entities.powerup.PowerUp;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class PowerUp1 extends PowerUp {

	public PowerUp1(Vector2D position, ScreenState screenState,
			MainGameScreen mainGameScreen) {
		super(position, new Vector2D((float) (0.05 + Math.random() * 0.10),
				(float) (0.05 + Math.random() * 0.10)), 9.0, screenState,
				3 * Math.PI / 2, 0.0);
		this.state = new ActivePowerUp1State(this);
		this.collision = new PowerUp1CollisionState(this);
	}

	@Override
	public boolean checkCollision(Entity other) {
		return CollisionChecker.checkCollision(this, other);
	}

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
