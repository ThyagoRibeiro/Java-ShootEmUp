package game.entities.spawner;

import game.GameLib;
import game.entities.Entity;
import game.entities.enemies.enemy1.Enemy1;
import game.entities.weapons.WeaponsFactory;
import game.entities.weapons.WeaponsFactory.WeaponType;
import geometry.Vector2D;

public class Enemy1Spawner extends EntitySpawner {

	public Enemy1Spawner(SpawnManager spawnManager, double respawnCooldown, double maxWait, double initialWait) {
		super(spawnManager, respawnCooldown, maxWait, initialWait);
	}

	@Override
	protected Entity Spawn() {
		Enemy1 enemy = new Enemy1(new Vector2D((float) (Math.random() * (GameLib.WIDTH - 100.0) + 50.0f), -10.0f),
				_spawnManager.getScreenState());
		enemy.setWeapon(WeaponsFactory.CreateWeapon(WeaponType.Basic, enemy));
		return enemy;

	}

}
