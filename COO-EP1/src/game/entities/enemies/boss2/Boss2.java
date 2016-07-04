package game.entities.enemies.boss2;

import java.awt.Color;

import game.entities.Entity;
import game.entities.collision.CollisionChecker;
import game.entities.enemies.Enemy;
import game.entities.hud.LifeBar;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Boss2 extends Enemy {

	protected LifeBar lifeBar;
	protected Color normalColor = Color.RED;
	protected Color getHitColor = Color.WHITE;
	protected Color currentColor;

	public Boss2(Vector2D position, ScreenState screenState, int healthPoints,
			MainGameScreen mainGameScreen) {
		super(position, new Vector2D((float) (0.05 + Math.random() * 0.10),
				(float) (0.05 + Math.random() * 0.10)), 30.0, screenState,
				3 * Math.PI / 2, 0.0);

		this.state = new ActiveBoss2State(this);
		this.collision = new Boss2CollisionState(this);
		currentColor = normalColor;
		this.entityType = EntityType.ENEMY;
		lifeBar = new LifeBar(healthPoints, this);
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
		return lifeBar.getCurrentHealthPoints() <= 0;
	}

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
