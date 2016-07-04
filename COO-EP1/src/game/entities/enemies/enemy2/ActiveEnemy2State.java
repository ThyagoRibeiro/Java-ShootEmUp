package game.entities.enemies.enemy2;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Time;

import java.awt.Color;

public class ActiveEnemy2State implements EntityState {

	private Enemy2 context;

	public ActiveEnemy2State(Enemy2 context) {
		this.context = context;
	}

	@Override
	public void render() {
		GameLib.setColor(Color.MAGENTA);
		GameLib.drawDiamond(context.getPosition().getX(), context.getPosition()
				.getY(), context.getRadius());
	}

	@Override
	public void update() {

		if (context.getPosition().getX() < -10
				|| context.getPosition().getX() > GameLib.WIDTH + 10) {
			context.remove();
		} else {
			boolean shootNow = false;
			double previousY = context.getPosition().getY();
			float curx = (float) (context.getVelocity().getX()
					* Math.cos(context.getAngle()) * Time.getInstance()
					.deltaTime());
			float cury = (float) (context.getVelocity().getY()
					* Math.sin(context.getAngle())
					* Time.getInstance().deltaTime() * (-1.0));
			context.setX(context.getPosition().getX() + curx);
			context.setY(context.getPosition().getY() + cury);
			context.setAngle(context.getAngle()
					+ (context.getRV() * Time.getInstance().deltaTime()));

			double threshold = GameLib.HEIGHT * 0.30;

			if (previousY < threshold
					&& context.getPosition().getY() >= threshold) {

				if (context.getPosition().getX() < GameLib.WIDTH / 2)
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
