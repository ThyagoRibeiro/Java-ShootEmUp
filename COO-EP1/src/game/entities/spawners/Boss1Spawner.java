package game.entities.spawners;

import game.Vector2D;
import game.entities.constants.WeaponTypeEnum;
import game.entities.enemies.boss1.Boss1;
import game.entities.weapons.WeaponsFactory;
import game.screenstate.MainGameScreen;

public class Boss1Spawner extends EntitySpawner {

	private int healthPoints;
	private MainGameScreen mainGameScreen;

	// Construtor

	public Boss1Spawner(SpawnManager spawnManager, int when, int x, int y,
			int healthPoints) {
		super(spawnManager, when, x, y + 120);
		this.healthPoints = healthPoints;
	}

	// Sobrescrita do metodo da classe abstrata EntitySpawner para inicializar o
	// chefe especificamente;

	@Override
	protected void spawn(int x, int y) {
		Boss1 boss1 = new Boss1(new Vector2D(x, y),
				spawnManager.getScreenState(), healthPoints, mainGameScreen);
		boss1.setWeapon(WeaponsFactory.createWeapon(WeaponTypeEnum.ENEMY_SHOT,
				boss1));
	}

}
