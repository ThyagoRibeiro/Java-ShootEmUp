package game.entities.spawner;

import game.entities.Entity;
import game.entities.enemies.enemy2.Enemy2;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class Enemy2Spawner extends EntitySpawner {

	MainGameScreen mainGameScreen;

	public Enemy2Spawner(SpawnManager _spawnManager, int when, int x, int y) {
		super(_spawnManager, when, x, y);
	}

	@Override
	protected Entity Spawn(int x, int y) {
		Enemy2 enemy = new Enemy2(new Vector2D(x, y), _spawnManager.getScreenState(), mainGameScreen);
		enemy.setWeapon(WeaponsFactory.CreateWeapon(WeaponType.EnemyShot, enemy));
		return enemy;
	}

}
