package game.entities.spawner;

import game.entities.enemies.enemy2.Enemy2;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;
import geometry.Vector2D;

public class Enemy2Spawner extends EntitySpawner {

	MainGameScreen mainGameScreen;

	public Enemy2Spawner(SpawnManager spawnManager, int when, int x, int y) {
		super(spawnManager, when, x, y);
	}

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
							WeaponType.ENEMY_SHOT, enemy));
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
