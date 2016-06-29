package game.entities.enemies.boss1;

import java.awt.Color;

import game.entities.Entity;
import game.entities.collision.CollisionChecker;
import game.entities.enemies.Enemy;
import game.entities.hud.LifeBar;
import game.screenstate.MainGameScreen;
import game.screenstate.ScreenState;
import geometry.Vector2D;

public class Boss1 extends Enemy {

	protected LifeBar _lifeBar;
	protected Color normalColor = Color.YELLOW, getHitColor = Color.WHITE, currentColor;

	public Boss1(Vector2D position, ScreenState screenState, int healthPoints, MainGameScreen mainGameScreen) {
		super(position, new Vector2D((float) (0.05 + Math.random() * 0.10), (float) (0.05 + Math.random() * 0.10)),
				30.0, screenState, 3 * Math.PI / 2, 0.0);

		this._state = new ActiveBoss1State(this);
		this._collision = new Boss1CollisionState(this);
		currentColor = normalColor;
		this._type = EntityType.Enemy;
		_lifeBar = new LifeBar(healthPoints, this);
	}

	@Override
	public boolean CheckCollision(Entity other) {
		return CollisionChecker.CheckCollision(this, other);
	}

	public Color getColor() {
		return currentColor;
	}

	public void getHit() {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 4; i++) {
					if (i % 2 == 0)
						currentColor = getHitColor;
					else
						currentColor = normalColor;
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

	public boolean isDead() {
		return _lifeBar.getCurrentHealthPoints() == 0;
	}

	@Override
	public void Render() {
		this._state.Render();
	}

	@Override
	public void Update() {
		this._state.Update();
	}

}
