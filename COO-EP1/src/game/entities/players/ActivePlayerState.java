package game.entities.players;

import game.GameLib;
import game.entities.states.EntityState;
import game.utils.Time;

public class ActivePlayerState implements EntityState {

	private Player context;

	// Construtor

	public ActivePlayerState(Player context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INCIO */

	public Player getContext() {
		return this.context;
	}

	public void setContext(Player context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o
	// jogador de maneira especifica.

	@Override
	public void render() {
		GameLib.setColor(context.getCurrentColor());
		GameLib.drawPlayer(context.getPosition().getCoordX(), context.getPosition()
				.getCoordY(), context.getRadius());
	}

	@Override
	public void update() {
		float lx;
		float ly;

		if (GameLib.iskeyPressed(GameLib.KEY_UP)) {
			ly = -1.0f;
		} else if (GameLib.iskeyPressed(GameLib.KEY_DOWN)) {
			ly = 1.0f;
		} else {
			ly = 0.0f;
		}
		if (GameLib.iskeyPressed(GameLib.KEY_LEFT)) {
			lx = -1.0f;
		} else if (GameLib.iskeyPressed(GameLib.KEY_RIGHT)) {
			lx = 1.0f;
		} else {
			lx = 0.0f;
		}
		context.setPosition(context.getPosition().add(
				context.getVelocity().multiply(lx, ly)
						.multiply(Time.getInstance().deltaTime())));

		if (context.getPosition().getCoordX() - context.getRadius() < 0) {
			context.setPositionX((float) context.getRadius());
		} else if (context.getPosition().getCoordX() > GameLib.WIDTH
				- context.getRadius()) {
			context.setPositionX(GameLib.WIDTH - (float) context.getRadius());
		}
		if (context.getPosition().getCoordY() - context.getRadius() < GameLib.HEIGHT / 2.0f) {
			context.setPositionY((GameLib.HEIGHT / 2.0f)
					+ (float) context.getRadius());
		} else if (context.getPosition().getCoordY() > GameLib.HEIGHT
				- context.getRadius()) {
			context.setPositionY(GameLib.HEIGHT - (float) context.getRadius());
		}
		if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
			context.shoot();
		}
		if (GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) {
			System.exit(0);
		}
	}

}
