package game.entities.spawners;

import game.Vector2D;
import game.entities.powerups.powerup1.PowerUp1;
import game.screenstate.MainGameScreen;

public class PowerUp1Spawner extends EntitySpawner {

	private MainGameScreen mainGameScreen;

	// Construtor

	public PowerUp1Spawner(SpawnManager spawnManager, int when, int x, int y) {
		super(spawnManager, when, x, y);
	}

	/* GETTERS E SETTERS - INICIO */

	public MainGameScreen getMainGameScreen() {
		return this.mainGameScreen;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita do metodo da classe abstrata EntitySpawner para inicializar o
	// power up especificamente;

	@Override
	protected void spawn(int x, int y) {
		new PowerUp1(new Vector2D(x, y), spawnManager.getScreenState(),
				mainGameScreen);
	}
}
