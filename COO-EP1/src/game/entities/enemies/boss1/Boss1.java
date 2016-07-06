package game.entities.enemies.boss1;

import game.Vector2D;
import game.entities.Entity;
import game.entities.collisions.CollisionChecker;
import game.entities.constants.EntityTypeEnum;
import game.entities.enemies.Enemy;
import game.entities.huds.LifeBar;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;

import java.awt.Color;

public class Boss1 extends Enemy {

	protected LifeBar lifeBar;
	protected Color normalColor = Color.YELLOW;
	protected Color getHitColor = Color.WHITE;
	protected Color currentColor;

	// Construtor

	public Boss1(Vector2D position, ScreenState screenState, int healthPoints,
			MainGameScreen mainGameScreen) {
		super(position, new Vector2D((float) (0.05 + Math.random() * 0.10),
				(float) (0.05 + Math.random() * 0.10)), 60.0, screenState,
				3 * Math.PI / 2, 0.0);
		lifeBar = new LifeBar(healthPoints, this);
		currentColor = normalColor;
		this.state = new Boss1State(this);
		this.collisionState = new Boss1CollisionState(this);
		this.entityType = EntityTypeEnum.ENEMY;
	}

	/* GETTERS E SETTERS - INICIO */

	public LifeBar getLifeBar() {
		return lifeBar;
	}

	public void setLifeBar(LifeBar lifeBar) {
		this.lifeBar = lifeBar;
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

	/* GETTERS E SETTERS - FIM */

	// Metodo que verifica e toma açao caso o chefe tenha sido atingido.

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

	// Verifica se o chefe esta morto.

	public boolean isDead() {
		return lifeBar.getCurrentHealthPoints() <= 0;
	}

	// Sobrescrita do metodo da interface Collidable, implementada na classe
	// Enemy, para checar colisoes.

	@Override
	public boolean checkCollision(Entity other) {
		return CollisionChecker.checkCollision(this, other);
	}

	// Sobrescrita dos metodos abstratos da classe Entity, para tratar a
	// renderização e a atualização do chefe.

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
