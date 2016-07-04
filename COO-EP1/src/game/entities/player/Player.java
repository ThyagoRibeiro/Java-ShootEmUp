package game.entities.player;

import java.awt.Color;

import game.GameLib;
import game.entities.Entity;
import game.entities.collision.Collidable;
import game.entities.collision.CollisionChecker;
import game.entities.hud.Infos;
import game.entities.hud.LifeBar;
import game.entities.projectiles.Projectile;
import game.entities.weapons.Weapon;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Player extends Entity implements Collidable {

	protected LifeBar lifeBar;
	protected Weapon weapon;
	protected boolean hasShield;
	protected Infos infos;
	protected Color normalColor = Color.BLUE;
	protected Color getHitColor = Color.WHITE;
	protected Color currentColor;
	protected Projectile shield;
	private MainGameScreen mainGameScreen;

	public MainGameScreen getMainGameScreen() {
		return mainGameScreen;
	}

	public Player(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState, MainGameScreen mainGameScreen) {

		super(position, velocity, radius, screenState);
		this.mainGameScreen = mainGameScreen;
		position.setXY((GameLib.WIDTH - (float) radius) / 2.0f,
				GameLib.HEIGHT * 0.9f);
		this.setState(new ActivePlayerState(this));

		screenState.getEntityManager().setPlayer(this);
		this.collision = new PlayerCollisionState(this);

		currentColor = normalColor;
		this.entityType = EntityType.PLAYER;
		lifeBar = new LifeBar(mainGameScreen.getPlayerHealthPoints(), this);
		infos = new Infos(this);

	}

	public void addShield() {
		shield = new Projectile(null, new Vector2D(0, 0), getScreenState(),
				true, WeaponType.PLAYER_SHIELD, (int) getRadius(), this);
	}

	@Override
	public boolean checkCollision(Entity other) {
		return CollisionChecker.checkCollision(this, other);
	}

	public Color getColor() {
		return currentColor;
	}

	public void getHit() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 4; i++) {
					if (i % 2 == 0)
						currentColor = getHitColor;
					else
						currentColor = normalColor;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public boolean isDead() {
		return lifeBar.getCurrentHealthPoints() == 0;
	}

	@Override
	public void render() {
		this.state.render();
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void tryShoot() {
		if (weapon == null)
			return;
		weapon.shoot();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
