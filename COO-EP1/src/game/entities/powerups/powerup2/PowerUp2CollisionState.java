package game.entities.powerups.powerup2;

import game.entities.Entity;
import game.entities.collisions.CollisionState;
import game.entities.constants.WeaponTypeEnum;
import game.entities.players.Player;
import game.entities.weapons.WeaponsFactory;

public class PowerUp2CollisionState implements CollisionState {

	private PowerUp2 context;

	// Construtor

	public PowerUp2CollisionState(PowerUp2 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public PowerUp2 getContext() {
		return this.context;
	}

	public void setContext(PowerUp2 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita da classe implementada CollisionState para tratar
	// especificamente o power up ao colidir.

	@Override
	public void onCollision(Entity collider) {
		switch (collider.getEntityType()) {
		case PLAYER:
			context.removeEntity();
			((Player) collider).setWeapon(WeaponsFactory.createWeapon(
					WeaponTypeEnum.PLAYER_MEGA_SHOT,
					(Player) collider));
			break;
		default:
			break;
		}
	}
}
