package game.entities.player;

import java.awt.Color;

import game.GameLib;
import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.collision.CollisionChecker;
import game.entities.hud.LifeBar;
import game.entities.weapons.Weapon;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Player extends Entity implements Collidable {

	protected Weapon _weapon;
	protected LifeBar _lifeBar;
	protected Color playerColor = Color.BLUE;

	public Player(Vector2D position, Vector2D velocity, double radius, ScreenState screenState) {

		super(position, velocity, radius, screenState);
		position.setXY((GameLib.WIDTH - (float) radius) / 2.0f, GameLib.HEIGHT * 0.9f);
		this.setState(new ActivePlayerState(this));
		_screenState.getEntityManager().setPlayer(this);
		this._collision = new PlayerCollisionState(this);
		this._type = EntityType.Player;
		_lifeBar = new LifeBar(10, Color.BLUE, this);
	}

	@Override
	public void Update() {
		this._state.Update();
	}

	@Override
	public void Render() {
		this._state.Render();
	}

	@Override
	public boolean CheckCollision(Entity other) {
		return CollisionChecker.CheckCollision(this, other);
	}

	public void setWeapon(Weapon weapon) {
		this._weapon = weapon;
	}

	public void TryShoot() {
		if (_weapon == null)
			return;
		_weapon.Shoot();
	}

	public boolean isDead() {
		return _lifeBar.getCurrentHealthPoints() == 0;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public void getHit() {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 4; i++) {
					if(i%2==0)
						playerColor = Color.WHITE;
					else
						playerColor = Color.BLUE;
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();
	}
	
}
