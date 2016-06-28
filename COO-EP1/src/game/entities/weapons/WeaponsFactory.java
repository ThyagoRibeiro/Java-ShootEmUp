package game.entities.weapons;

import game.entities.Entity;
import geometry.Vector2D;

public abstract class WeaponsFactory {

	public enum WeaponType {
		Basic, Basic2
	};

	public static Weapon CreateWeapon(WeaponType type, Entity owner) {
		switch (type) {
		case Basic:
			return new Weapon(owner, new Vector2D(0.0f, 0.25f), 870);

		case Basic2:
			return new Weapon(owner, new Vector2D(0.0f, 0.35f), 650);
		}

		return null;
	}

}
