package game.entities.weapons;

import game.entities.Entity;
import geometry.Vector2D;

public abstract class WeaponsFactory {

	public enum WeaponType {
		EnemyShot, PlayerDeafultShot, PlayerMegaShot, PlayerShield
	};

	public static Weapon CreateWeapon(WeaponType type, Entity owner) {

		switch (type) {

		case EnemyShot:
			return new Weapon(owner, new Vector2D(0.0f, 0.25f), 870, type, 2);

		case PlayerDeafultShot:
			return new Weapon(owner, new Vector2D(0.0f, 0.35f), 650, type, 0);

		case PlayerMegaShot:
			return new Weapon(owner, new Vector2D(0.0f, 0.35f), 1000, type, 8);
			
		}

		return null;
	}

}
