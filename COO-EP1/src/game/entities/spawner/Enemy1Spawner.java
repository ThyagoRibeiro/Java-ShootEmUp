package game.entities.spawner;

import game.entities.Entity;
import game.entities.enemies.enemy1.Enemy1;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class Enemy1Spawner extends EntitySpawner {

	MainGameScreen mainGameScreen;

	public Enemy1Spawner(SpawnManager _spawnManager, int when, int x, int y) {
		super(_spawnManager, when, x, y);
	}

	@Override
	protected Entity Spawn(int x, int y) {
		Enemy1 enemy = new Enemy1(new Vector2D(x, y), _spawnManager.getScreenState(), mainGameScreen);
		enemy.setWeapon(WeaponsFactory.CreateWeapon(WeaponType.Basic, enemy));
		return enemy;
	}
}
