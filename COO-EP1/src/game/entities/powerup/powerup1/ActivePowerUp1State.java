package game.entities.powerup.powerup1;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Time;
import geometry.Vector2D;

import java.awt.Color;

public class ActivePowerUp1State implements EntityState {

	private PowerUp1 context;

	public ActivePowerUp1State(PowerUp1 context) {
		this.context = context;
	}

	@Override
	public void render() {
		GameLib.setColor(Color.GRAY);
		GameLib.drawCircle(context.getPosition().getX(), context.getPosition()
				.getY(), context.getRadius());
		GameLib.writeInCircle(context.getPosition().getX(), context
				.getPosition().getY(), "S", context.getRadius());
	}

	@Override
	public void update() {
		if (context.getPosition().getY() > GameLib.HEIGHT + 10) {
			context.remove();
		} else {
			float curX = context.getPosition().getX();
			float curY = context.getPosition().getY();
			double angle = context.getAngle();
			curX += context.getVelocity().getX() * Math.cos(angle)
					* Time.getInstance().deltaTime();
			curY -= context.getVelocity().getY() * Math.sin(angle)
					* Time.getInstance().deltaTime();
			angle += context.getRV() * Time.getInstance().deltaTime();
			context.setPosition(new Vector2D(curX, curY));
			context.setAngle(angle);
		}
	}
}
