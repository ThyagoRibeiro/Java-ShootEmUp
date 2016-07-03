package game.entities.weapons;

import game.entities.Entity;
import game.entities.player.Player;
import game.entities.projectiles.Projectile;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.util.LocalTime;
import geometry.Vector2D;

public class Weapon implements Cloneable {

	private Vector2D _missilespeed;
	private boolean _playerUsing;
	private double cooldown;
	private LocalTime localTime;
	private Entity owner;
	private int radius;
	private WeaponType type;

	public Weapon(Entity owner, Vector2D missilespeed, double cooldown, WeaponType type, int radius) {
		this.owner = owner;
		this._missilespeed = missilespeed;
		this.cooldown = cooldown;
		this.type = type;
		this.radius = radius;
		localTime = new LocalTime(cooldown);
		this._playerUsing = owner instanceof Player;
		if (_playerUsing)
			this.cooldown -= 200;
	}

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

	public void setPlayerUsing(boolean playerUsing) {
		this._playerUsing = playerUsing;
	}

	public void Shoot() {
		if (localTime.hasEnded()) {
			float centeredX = (float) (owner.getPosition().getX() + owner.getRadius() / 2);
			float centeredY = (float) (owner.getPosition().getY() + owner.getRadius() / 2);
			new Projectile(new Vector2D(centeredX, centeredY), _missilespeed, owner.getScreenState(), _playerUsing, type, radius, null);
			localTime.Start(cooldown);
		}
	}
}
