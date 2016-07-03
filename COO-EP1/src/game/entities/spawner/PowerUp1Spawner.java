package game.entities.spawner;

import game.entities.Entity;
import game.entities.powerup.powerup1.PowerUp1;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class PowerUp1Spawner extends EntitySpawner {

	MainGameScreen mainGameScreen;

	public PowerUp1Spawner(SpawnManager _spawnManager, int when, int x, int y) {
		super(_spawnManager, when, x, y);
	}

	@Override
	protected void Spawn(int x, int y) {
		PowerUp1 powerUp = new PowerUp1(new Vector2D(x, y), _spawnManager.getScreenState(), mainGameScreen);
	}
}
