package game.entities.enemies.enemy2;

import game.GameLib;
import game.entities.states.EntityState;
import game.utils.Time;

import java.awt.Color;

public class ActiveEnemy2State implements EntityState {

	private Enemy2 context;

	// Construtor

	public ActiveEnemy2State(Enemy2 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public Enemy2 getContext() {
		return context;
	}

	public void setContext(Enemy2 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o
	// inimigo de maneira especifica.

	@Override
	public void render() {
		GameLib.setColor(Color.MAGENTA);
		GameLib.drawDiamond(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), context.getRadius());
	}

	@Override
	public void update() {
		if (context.getPosition().getCoordX() < -10
				|| context.getPosition().getCoordX() > GameLib.WIDTH + 10) {
			context.removeEntity();
		} else {
			boolean shootNow = false;
			double previousY = context.getPosition().getCoordY();
			float curx = (float) (context.getVelocity().getCoordX()
					* Math.cos(context.getAngle()) * Time.getInstance()
					.deltaTime());
			float cury = (float) (context.getVelocity().getCoordY()
					* Math.sin(context.getAngle())
					* Time.getInstance().deltaTime() * (-1.0));
			context.setPositionX(context.getPosition().getCoordX() + curx);
			context.setPositionY(context.getPosition().getCoordY() + cury);
			context.setAngle(context.getAngle()
					+ (context.getRV() * Time.getInstance().deltaTime()));

			double threshold = GameLib.HEIGHT * 0.30;

			if (previousY < threshold
					&& context.getPosition().getCoordY() >= threshold) {

				if (context.getPosition().getCoordX() < GameLib.WIDTH / 2)
					context.setRv(0.003);
				else
					context.setRv(-0.003);
			}

			if (context.getRV() > 0.0
					&& Math.abs(context.getAngle() - 3 * Math.PI) < 0.05) {

				context.setRv(0.0);
				context.setAngle(3 * Math.PI);

				shootNow = true;
			}

			if (context.getRV() < 0.0 && Math.abs(context.getAngle()) < 0.05) {

				context.setRv(0.0);
				context.setAngle(0.0);
				shootNow = true;
			}

			if (shootNow) {
				context.shoot();
			}
		}

	}

}
