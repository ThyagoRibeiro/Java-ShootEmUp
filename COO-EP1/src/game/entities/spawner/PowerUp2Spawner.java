package game.entities.spawner;

import game.entities.powerup.powerup2.PowerUp2;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class PowerUp2Spawner extends EntitySpawner {

	private MainGameScreen mainGameScreen;

	// Construtor

	public PowerUp2Spawner(SpawnManager spawnManager, int when, int x, int y) {
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
		new PowerUp2(new Vector2D(x, y), spawnManager.getScreenState(),
				mainGameScreen);
	}
}
