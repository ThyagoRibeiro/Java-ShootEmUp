package game.entities.weapons;

import game.entities.Entity;
import game.entities.constants.WeaponTypeEnum;
import game.entities.player.Player;
import game.entities.projectiles.Projectile;
import game.util.LocalTime;
import geometry.Vector2D;

public class Weapon implements Cloneable {

	private Entity owner;
	private WeaponTypeEnum weaponTypeEnum;
	private Vector2D projectileSpeed;
	private int radius;
	private boolean playerUsing;
	private double cooldown;
	private LocalTime localTime;

	// Construtor

	public Weapon(Entity owner, Vector2D missilespeed, double cooldown,
			WeaponTypeEnum type, int radius) {
		this.owner = owner;
		this.projectileSpeed = missilespeed;
		this.cooldown = cooldown;
		this.weaponTypeEnum = type;
		this.radius = radius;
		localTime = new LocalTime(cooldown);
		this.playerUsing = owner instanceof Player;
		if (playerUsing)
			this.cooldown -= 200;
	}

	/* GETTERS E SETTERS - INICIO */

	public Entity getOwner() {
		return this.owner;
	}

	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public WeaponTypeEnum getWeaponType() {
		return this.weaponTypeEnum;
	}

	public void setWeaponType(WeaponTypeEnum weaponType) {
		this.weaponTypeEnum = weaponType;
	}

	public Vector2D getProjectileSpeed() {
		return this.projectileSpeed;
	}

	public void setProjectileSpeed(Vector2D projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}

	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isPlayerUsing() {
		return this.playerUsing;
	}

	public void setPlayerUsing(boolean playerUsing) {
		this.playerUsing = playerUsing;
	}

	public double getCooldown() {
		return this.cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public LocalTime getLocalTime() {
		return this.localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodo que faz a arma atirar.

	public void shoot() {
		if (localTime.hasEnded()) {
			float centeredX = (float) (owner.getPosition().getCoordX() + owner
					.getRadius() / 2);
			float centeredY = (float) (owner.getPosition().getCoordY() + owner
					.getRadius() / 2);
			new Projectile(new Vector2D(centeredX, centeredY), projectileSpeed,
					owner.getScreenState(), playerUsing, weaponTypeEnum,
					radius, null);
			localTime.start(cooldown);
		}
	}

	// Sobrescrita do metodo clone da classe Object.

	@Override
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return clone;
	}
}
