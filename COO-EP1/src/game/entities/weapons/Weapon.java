package game.entities.weapons;

import game.entities.Entity;
import game.entities.player.Player;
import game.entities.projectiles.Projectile;
import game.util.LocalTime;
import geometry.Vector2D;

public class Weapon implements Cloneable {

	private Vector2D _missilespeed;
	private boolean _playerUsing;
	private double cooldown;
	private LocalTime localTime;
	private Entity owner;

	public Weapon(Entity owner, Vector2D missilespeed, double cooldown) {
		this.owner = owner;
		this._missilespeed = missilespeed;
		this.cooldown = cooldown;
		localTime = new LocalTime(cooldown);
		this._playerUsing = owner instanceof Player;
		if (_playerUsing)
			this.cooldown -= 200;
	}

	public void Shoot() {
		if (localTime.hasEnded()) {
			new Projectile(owner.getPosition(), _missilespeed, owner.getScreenState(), _playerUsing);
			localTime.Start(cooldown);
		}
	}

	public void setPlayerUsing(boolean playerUsing) {
		this._playerUsing = playerUsing;
	}

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
