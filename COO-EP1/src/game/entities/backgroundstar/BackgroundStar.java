package game.entities.backgroundstar;

import java.awt.Color;

import game.GameLib;
import game.entities.Entity;
import game.screenstate.ScreenState;
import game.util.Draw;
import geometry.Vector2D;

public class BackgroundStar extends Entity {

	protected int _level;

	public BackgroundStar(int level, ScreenState screenState) {
		super(new Vector2D((float) Math.random() * GameLib.WIDTH, (float) (Math.random() * GameLib.HEIGHT)),
				level == 1 ? new Vector2D(0.0f, 0.07f) : new Vector2D(0.0f, 0.035f), 2.0, screenState);
		this._level = level;
	}

	public int getLevel() {
		return this._level;
	}

	@Override
	public void Update() {
		UpdatePosition();
		if (this._position.getY() >= GameLib.HEIGHT - this._radius) {
			this._position.setXY((float) ((float) Math.random() * GameLib.WIDTH + this._radius), (float) -this._radius);
		}

	}

	@Override
	public void Render() {
		switch (_level) {
		case 1:
			Draw.setColor(Color.GRAY);
			break;

		case 2:
			Draw.setColor(Color.DARK_GRAY);
			break;
		}
		Draw.fillRect(this._position.getX(), this._position.getY(), _radius, _radius);
	}

}
