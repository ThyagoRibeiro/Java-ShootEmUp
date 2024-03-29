package game.entities.projectiles;

import game.Vector2D;
import game.entities.Entity;
import game.entities.collisions.Collidable;
import game.entities.collisions.CollisionChecker;
import game.entities.constants.EntityTypeEnum;
import game.entities.constants.WeaponTypeEnum;
import game.entities.players.Player;
import game.screenstate.ScreenState;

public class Projectile extends Entity implements Collidable {

	private Player player;
	private WeaponTypeEnum weaponType;

	// Construtor

	public Projectile(Vector2D position, Vector2D velocity,
			ScreenState screenState, boolean playerMissile,
			WeaponTypeEnum weaponType, int radius, Player player) {

		super(position, velocity, radius, screenState);

		if (player != null) {
			setPosition(player.getPosition());
			this.player = player;
		}

		this.weaponType = weaponType;
		if (playerMissile) {
			this.entityType = EntityTypeEnum.FRIENDLY_PROJECTILE;
			velocity.setCoordYToNegative();
		} else {
			this.entityType = EntityTypeEnum.ENEMY_PROJECTILE;
			velocity.setCoordYToPositive();
		}
		this.state = new ProjectileState(this);
	}

	/* GETTERS E SETTERS - INICIO */

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public WeaponTypeEnum getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponTypeEnum weaponType) {
		this.weaponType = weaponType;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita do metodo da interface Collidable, implementada na classe
	// Enemy, para checar colisoes.

	@Override
	public boolean checkCollision(Entity other) {
		return CollisionChecker.checkCollision(this, other);
	}

	// Sobrescrita dos metodos abstratos da classe Entity, para tratar a
	// renderização e a atualização do projetil

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
