package game.entities.spawner;

import game.entities.powerup.powerup2.PowerUp2;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class PowerUp2Spawner extends EntitySpawner {

	MainGameScreen mainGameScreen;

	public PowerUp2Spawner(SpawnManager spawnManager, int when, int x, int y) {
		super(spawnManager, when, x, y);
	}

	@Override
	protected void spawn(int x, int y) {
		new PowerUp2(new Vector2D(x, y),
				spawnManager.getScreenState(), mainGameScreen);
	}
}
