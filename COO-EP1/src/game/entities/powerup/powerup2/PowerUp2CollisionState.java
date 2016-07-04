package game.entities.powerup.powerup2;

import game.entities.Entity;
import game.entities.collision.CollisionState;
import game.entities.player.Player;
import game.entities.weapons.WeaponsFactory;

public class PowerUp2CollisionState implements CollisionState {

	private PowerUp2 context;

	public PowerUp2CollisionState(PowerUp2 context) {
		this.context = context;
	}

	@Override
	public void onCollision(Entity collider) {
		switch (collider.getEntityType()) {
		case PLAYER:
			context.remove();
			((Player) collider).setWeapon(WeaponsFactory
					.createWeapon(WeaponsFactory.WeaponType.PLAYER_MEGA_SHOT,
							(Player) collider));
			break;
		default:
			break;
		}
	}
}
