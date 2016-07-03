package game.entities.powerup.powerup2;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.player.Player;
import game.entities.weapons.WeaponsFactory;

public class PowerUp2CollisionState implements CollisionState {

	private PowerUp2 _context;

	public PowerUp2CollisionState(PowerUp2 context) {
		this._context = context;
	}

	@Override
	public void OnCollision(Entity collider) {
		switch (collider.getEntityType()) {
		case Player:
			_context.Remove();
			((Player) collider).setWeapon(WeaponsFactory.CreateWeapon(WeaponsFactory.WeaponType.PlayerMegaShot, (Player) collider));
			break;
		default:
			break;
		}
	}
}
