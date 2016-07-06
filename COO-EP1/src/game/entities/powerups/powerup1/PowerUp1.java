package game.entities.powerups.powerup1;

import game.Vector2D;
import game.entities.Entity;
import game.entities.collisions.CollisionChecker;
import game.entities.powerups.PowerUp;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;

public class PowerUp1 extends PowerUp {

	// Construtor

	public PowerUp1(Vector2D position, ScreenState screenState,
			MainGameScreen mainGameScreen) {
		super(position, new Vector2D((float) (0.05 + Math.random() * 0.10),
				(float) (0.05 + Math.random() * 0.10)), 9.0, screenState,
				3 * Math.PI / 2, 0.0);
		this.state = new PowerUp1State(this);
		this.collisionState = new PowerUp1CollisionState(this);
	}

	// Sobrescrita do metodo da interface Collidable, implementada na classe
	// Enemy, para checar colisoes.

	@Override
	public boolean checkCollision(Entity other) {
		return CollisionChecker.checkCollision(this, other);
	}

	// Sobrescrita dos metodos abstratos da classe Entity, para tratar a
	// renderização e a atualização do power up.

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
