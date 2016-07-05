package game.entities.spawners;

import game.Vector2D;
import game.entities.constants.WeaponTypeEnum;
import game.entities.enemies.enemy2.Enemy2;
import game.entities.weapons.WeaponsFactory;
import game.screenstate.MainGameScreen;

public class Enemy2Spawner extends EntitySpawner {

	MainGameScreen mainGameScreen;
	
	// Construtor

	public Enemy2Spawner(SpawnManager spawnManager, int when, int x, int y) {
		super(spawnManager, when, x, y);
	}

	// Sobrescrita do metodo da classe abstrata EntitySpawner para inicializar o
	// inimigo especificamente;

	@Override
	protected void spawn(int x, int y) {

		Vector2D velocity = new Vector2D((float) (0.15 + Math.random() * 0.10),
				(float) (0.15 + Math.random() * 0.10));

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					Enemy2 enemy = new Enemy2(new Vector2D(x, y), velocity,
							spawnManager.getScreenState(), mainGameScreen);
					enemy.setWeapon(WeaponsFactory.createWeapon(
							WeaponTypeEnum.ENEMY_SHOT, enemy));
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

}
