package game.entities.backgrounds;

import game.GameLib;
import game.Vector2D;
import game.entities.Entity;
import game.screenstate.ScreenState;

import java.awt.Color;

public class Background extends Entity {

	protected int level;

	// Construtor

	public Background(int level, ScreenState screenState) {
		super(new Vector2D((float) Math.random() * GameLib.WIDTH,
				(float) (Math.random() * GameLib.HEIGHT)),
				level == 1 ? new Vector2D(0.0f, 0.07f) : new Vector2D(0.0f,
						0.035f), 2.0, screenState);
		this.level = level;
	}

	/* GETTERS E SETTERS - INICIO */

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodos de sobrescrita da classe abstrata Entity para ser renderizado na
	// tela e atualizado da maneira específica desta classe.

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
		GameLib.fillRect(this.position.getCoordX(), this.position.getCoordY(),
				radius, radius);
	}

	@Override
	public void update() {
		updateEntityPosition();
		if (this.position.getCoordY() >= GameLib.HEIGHT - this.radius) {
			this.position.setCoordXY((float) ((float) Math.random()
					* GameLib.WIDTH + this.radius), (float) -this.radius);
		}

	}

}
