package game.entities.spawner;

import game.entities.Entity;
import game.entities.powerup.powerup2.PowerUp2;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class PowerUp2Spawner extends EntitySpawner {

	MainGameScreen mainGameScreen;

	public PowerUp2Spawner(SpawnManager _spawnManager, int when, int x, int y) {
		super(_spawnManager, when, x, y);
	}

	@Override
	protected void Spawn(int x, int y) {
		PowerUp2 powerUp = new PowerUp2(new Vector2D(x, y), _spawnManager.getScreenState(), mainGameScreen);
	}
}
