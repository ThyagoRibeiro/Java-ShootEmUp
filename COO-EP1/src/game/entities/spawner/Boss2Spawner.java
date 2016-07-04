package game.entities.spawner;

import game.entities.enemies.boss2.Boss2;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class Boss2Spawner extends EntitySpawner {

	private int healthPoints;
	private MainGameScreen mainGameScreen;

	public Boss2Spawner(SpawnManager spawnManager, int when, int x, int y,
			int healthPoints) {
		super(spawnManager, when, x, y + 120);
		this.healthPoints = healthPoints;
	}

	@Override
	protected void spawn(int x, int y) {
		Boss2 boss2 = new Boss2(new Vector2D(x, y),
				spawnManager.getScreenState(), healthPoints, mainGameScreen);
		boss2.setWeapon(WeaponsFactory
				.createWeapon(WeaponType.ENEMY_SHOT, boss2));
	}

}
