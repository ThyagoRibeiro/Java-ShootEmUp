package game.entities.spawner;

import game.entities.enemies.boss1.Boss1;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class Boss1Spawner extends EntitySpawner {

	private int healthPoints;
	private MainGameScreen mainGameScreen;

	public Boss1Spawner(SpawnManager spawnManager, int when, int x, int y,
			int healthPoints) {
		super(spawnManager, when, x, y + 120);
		this.healthPoints = healthPoints;
	}

	@Override
	protected void spawn(int x, int y) {
		Boss1 boss1 = new Boss1(new Vector2D(x, y),
				spawnManager.getScreenState(), healthPoints, mainGameScreen);
		boss1.setWeapon(WeaponsFactory
				.createWeapon(WeaponType.ENEMY_SHOT, boss1));
	}

}
