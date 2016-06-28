package game.entities.spawner;

import game.GameLib;
import game.entities.Entity;
import game.entities.enemies.enemy2.Enemy2;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import geometry.Vector2D;

public class Enemy2Spawner extends EntitySpawner {

	public Enemy2Spawner(SpawnManager spawnManager, double respawnCooldown, double maxWait, double initialWait) {
		super(spawnManager, respawnCooldown, maxWait, initialWait);
	}

	@Override
	protected Entity Spawn() {
		Enemy2 enemy2 = new Enemy2(new Vector2D((float) (Math.random() * (GameLib.WIDTH - 100.0) + 50.0f), -10.0f),
				_spawnManager.getScreenState());
		enemy2.setWeapon(WeaponsFactory.CreateWeapon(WeaponType.Basic, enemy2));
		return enemy2;
	}

}
