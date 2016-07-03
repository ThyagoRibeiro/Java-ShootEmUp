package game.entities.powerup.powerup2;

import java.awt.Color;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Draw;
import game.util.Time;
import geometry.Vector2D;

public class ActivePowerUp2State implements EntityState {

	private PowerUp2 context;

	public ActivePowerUp2State(PowerUp2 context) {
		this.context = context;
	}

	@Override
	public void Render() {
		Draw.setColor(Color.PINK);
		Draw.drawCircle(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
		Draw.writeInCircle(context.getPosition().getX(), context.getPosition().getY(), "M", context.getRadius());
	}

	@Override
	public void Update() {
		if (context.getPosition().getY() > GameLib.HEIGHT + 10) {
			context.Remove();
		} else {
			float curX = context.getPosition().getX();
			float curY = context.getPosition().getY();
			double angle = context.getAngle();
			curX += context.getVelocity().getX() * Math.cos(angle) * Time.getInstance().DeltaTime();
			curY -= context.getVelocity().getY() * Math.sin(angle) * Time.getInstance().DeltaTime();
			angle += context.getRV() * Time.getInstance().DeltaTime();
			context.setPosition(new Vector2D(curX, curY));
			context.setAngle(angle);
		}
	}
}
