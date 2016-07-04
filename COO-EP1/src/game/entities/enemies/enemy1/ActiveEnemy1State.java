package game.entities.enemies.enemy1;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.LocalTime;
import game.util.Time;
import geometry.Vector2D;

import java.awt.Color;

public class ActiveEnemy1State implements EntityState {

	private Enemy1 context;
	private LocalTime shootTime;

	private double waitTime;

	public ActiveEnemy1State(Enemy1 context) {
		waitTime = 1000;
		this.context = context;
		shootTime = new LocalTime(Math.random() * waitTime);
	}

	@Override
	public void render() {
		GameLib.setColor(Color.CYAN);
		GameLib.drawCircle(context.getPosition().getX(), context.getPosition()
				.getY(), context.getRadius());
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
		if (shootTime.hasEnded()) {
			context.shoot();
			shootTime.start(Math.random() * waitTime);
		}
	}
}
