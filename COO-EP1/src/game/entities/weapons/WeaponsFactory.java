package game.entities.weapons;

import game.entities.Entity;
import game.entities.constants.WeaponTypeEnum;
import geometry.Vector2D;

public abstract class WeaponsFactory {
	
	// Construtor

	public static Weapon createWeapon(WeaponTypeEnum weaponTypeEnum,
			Entity owner) {

		switch (weaponTypeEnum) {
		case ENEMY_SHOT:
			return new Weapon(owner, new Vector2D(0.0f, 0.25f), 870,
					weaponTypeEnum, 2);
		case PLAYER_DEFAULT_SHOT:
			return new Weapon(owner, new Vector2D(0.0f, 0.50f), 400,
					weaponTypeEnum, 0);
		case PLAYER_MEGA_SHOT:
			return new Weapon(owner, new Vector2D(0.0f, 0.35f), 1000,
					weaponTypeEnum, 8);
		default:
			return null;
		}

	}

}
