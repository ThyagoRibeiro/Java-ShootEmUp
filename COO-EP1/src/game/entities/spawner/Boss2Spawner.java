package game.entities.spawner;

import game.entities.Entity;
import game.entities.enemies.boss2.Boss2;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class Boss2Spawner extends EntitySpawner {

	private int healthPoints;
	private MainGameScreen mainGameScreen;

	public Boss2Spawner(SpawnManager _spawnManager, int when, int x, int y, int healthPoints) {
		super(_spawnManager, when, x, y);
		this.healthPoints = healthPoints;
	}

	@Override
	protected Entity Spawn(int x, int y) {
		Boss2 boss2 = new Boss2(new Vector2D(x, y), _spawnManager.getScreenState(), healthPoints, mainGameScreen);
		boss2.setWeapon(WeaponsFactory.CreateWeapon(WeaponType.Basic, boss2));
		return boss2;
	}

}
