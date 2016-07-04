package game.entities.powerup.powerup2;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Time;
import geometry.Vector2D;

import java.awt.Color;

public class ActivePowerUp2State implements EntityState {

	private PowerUp2 context;

	public ActivePowerUp2State(PowerUp2 context) {
		this.context = context;
	}

	@Override
	public void render() {
		GameLib.setColor(Color.PINK);
		GameLib.drawCircle(context.getPosition().getX(), context.getPosition()
				.getY(), context.getRadius());
		GameLib.writeInCircle(context.getPosition().getX(), context
				.getPosition().getY(), "M", context.getRadius());
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
