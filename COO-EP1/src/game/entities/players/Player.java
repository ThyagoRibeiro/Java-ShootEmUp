package game.entities.players;

import game.GameLib;
import game.Vector2D;
import game.entities.Entity;
import game.entities.collisions.Collidable;
import game.entities.collisions.CollisionChecker;
import game.entities.constants.EntityTypeEnum;
import game.entities.constants.WeaponTypeEnum;
import game.entities.huds.Infos;
import game.entities.huds.LifeBar;
import game.entities.projectiles.Projectile;
import game.entities.weapons.Weapon;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;

import java.awt.Color;

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

	// Construtor

	public Player(Vector2D position, Vector2D velocity, double radius,
			ScreenState screenState, MainGameScreen mainGameScreen) {

		super(position, velocity, radius, screenState);
		this.mainGameScreen = mainGameScreen;
		position.setCoordXY((GameLib.WIDTH - (float) radius) / 2.0f,
				GameLib.HEIGHT * 0.9f);
		this.setState(new PlayerState(this));

		screenState.getEntityManager().setPlayer(this);
		this.collisionState = new PlayerCollisionState(this);

		currentColor = normalColor;
		this.entityType = EntityTypeEnum.PLAYER;
		lifeBar = new LifeBar(mainGameScreen.getPlayerHealthPoints(), this);
		infos = new Infos(this);

	}

	/* GETTERS E SETTERS - INICIO */

	public LifeBar getLifeBar() {
		return lifeBar;
	}

	public void setLifeBar(LifeBar lifeBar) {
		this.lifeBar = lifeBar;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public boolean isHasShield() {
		return hasShield;
	}

	public void setHasShield(boolean hasShield) {
		this.hasShield = hasShield;
	}

	public Infos getInfos() {
		return infos;
	}

	public void setInfos(Infos infos) {
		this.infos = infos;
	}

	public Color getNormalColor() {
		return normalColor;
	}

	public void setNormalColor(Color normalColor) {
		this.normalColor = normalColor;
	}

	public Color getGetHitColor() {
		return getHitColor;
	}

	public void setGetHitColor(Color getHitColor) {
		this.getHitColor = getHitColor;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public Projectile getShield() {
		return shield;
	}

	public void setShield(Projectile shield) {
		this.shield = shield;
	}

	public MainGameScreen getMainGameScreen() {
		return mainGameScreen;
	}

	public void setMainGameScreen(MainGameScreen mainGameScreen) {
		this.mainGameScreen = mainGameScreen;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodo que adiciona o power up de escudo.

	public void addShield() {
		shield = new Projectile(null, new Vector2D(0, 0), getScreenState(),
				true, WeaponTypeEnum.PLAYER_SHIELD, (int) getRadius(), this);
	}

	// Metodo que verifica e toma açao caso o jogador tenha sido atingido.

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

	// Verifica se o jogador esta morto.

	public boolean isDead() {
		return lifeBar.getCurrentHealthPoints() == 0;
	}

	// Metodo que faz o jogador atirar.

	public void shoot() {
		if (weapon == null) {
			return;
		}
		weapon.shoot();
	}

	// Sobrescrita do metodo da interface Collidable, implementada na classe
	// Enemy, para checar colisoes.

	@Override
	public boolean checkCollision(Entity other) {
		return CollisionChecker.checkCollision(this, other);
	}

	// Sobrescrita dos metodos abstratos da classe Entity, para tratar a
	// renderização e a atualização do jogador.

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
