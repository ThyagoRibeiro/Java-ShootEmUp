package game.entities.backgroundstar;

import game.GameLib;
import game.entities.Entity;
import game.screenstate.ScreenState;
import geometry.Vector2D;

import java.awt.Color;

public class BackgroundStar extends Entity {

	protected int level;

	public BackgroundStar(int level, ScreenState screenState) {
		super(new Vector2D((float) Math.random() * GameLib.WIDTH,
				(float) (Math.random() * GameLib.HEIGHT)),
				level == 1 ? new Vector2D(0.0f, 0.07f) : new Vector2D(0.0f,
						0.035f), 2.0, screenState);
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}

	@Override
	public void render() {
		switch (level) {
		case 1:
			GameLib.setColor(Color.GRAY);
			break;

		case 2:
			GameLib.setColor(Color.DARK_GRAY);
			break;
		}
		GameLib.fillRect(this.position.getX(), this.position.getY(), radius,
				radius);
	}

	@Override
	public void update() {
		updatePosition();
		if (this.position.getY() >= GameLib.HEIGHT - this.radius) {
			this.position
					.setXY((float) ((float) Math.random() * GameLib.WIDTH + this.radius),
							(float) -this.radius);
		}

	}

}
