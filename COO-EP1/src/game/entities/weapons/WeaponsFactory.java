package game.entities.weapons;

import game.entities.Entity;
import geometry.Vector2D;

public abstract class WeaponsFactory {

	public enum WeaponType {
		PLAYER_DEFAULT_SHOT, PLAYER_MEGA_SHOT, PLAYER_SHIELD, ENEMY_SHOT
	};

	public static Weapon createWeapon(WeaponType weaponType, Entity owner) {

		switch (weaponType) {
		case ENEMY_SHOT:
			return new Weapon(owner, new Vector2D(0.0f, 0.25f), 870,
					weaponType, 2);
		case PLAYER_DEFAULT_SHOT:
			return new Weapon(owner, new Vector2D(0.0f, 0.50f), 400,
					weaponType, 0);
		case PLAYER_MEGA_SHOT:
			return new Weapon(owner, new Vector2D(0.0f, 0.35f), 1000,
					weaponType, 8);
		default:
			return null;
		}

	}

}
