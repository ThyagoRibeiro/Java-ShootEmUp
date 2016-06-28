package game.entities.enemies.enemy2;

import java.awt.Color;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Draw;
import game.util.Time;

public class ActiveEnemy2State implements EntityState {

	private Enemy2 context;

	public ActiveEnemy2State(Enemy2 context) {
		this.context = context;
	}

	@Override
	public void Update() {

		if (context.getPosition().getX() < -10 || context.getPosition().getX() > GameLib.WIDTH + 10) {
			context.Remove();
		} else {
			boolean shootNow = false;
			double previousY = context.getPosition().getY();
			float curx = (float) (context.getVelocity().getX() * Math.cos(context.getAngle())
					* Time.getInstance().DeltaTime());
			float cury = (float) (context.getVelocity().getY() * Math.sin(context.getAngle())
					* Time.getInstance().DeltaTime() * (-1.0));
			context.setX(context.getPosition().getX() + curx);
			context.setY(context.getPosition().getY() + cury);
			context.setAngle(context.getAngle() + (context.getRV() * Time.getInstance().DeltaTime()));

			double threshold = GameLib.HEIGHT * 0.30;

			if (previousY < threshold && context.getPosition().getY() >= threshold) {

				if (context.getPosition().getX() < GameLib.WIDTH / 2)
					context.setRv(0.003);
				else
					context.setRv(-0.003);
			}

			if (context.getRV() > 0.0 && Math.abs(context.getAngle() - 3 * Math.PI) < 0.05) {

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
				context.Shoot();
			}
		}

	}

	@Override
	public void Render() {
		Draw.setColor(Color.yellow);
		Draw.drawDiamond(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
	}

}
