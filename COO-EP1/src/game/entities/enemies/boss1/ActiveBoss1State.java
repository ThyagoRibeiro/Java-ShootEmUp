package game.entities.enemies.boss1;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.LocalTime;
import game.util.Time;
import geometry.Vector2D;

public class ActiveBoss1State implements EntityState {

	private Boss1 context;
	private int counterGoes;
	private LocalTime shootTime;

	private double waitTime;

	public ActiveBoss1State(Boss1 context) {
		waitTime = 1000;
		this.context = context;
		shootTime = new LocalTime(Math.random() * waitTime);
		counterGoes = 0;
	}

	@Override
	public void render() {
		GameLib.setColor(context.getColor());
		GameLib.drawHourglass(context.getPosition().getX(), context
				.getPosition().getY(), context.getRadius());
	}

	@Override
	public void update() {
		if (context.getPosition().getY() <= 0) {
			context.setPosition(new Vector2D(1, 150));
		} else {
			float curX = context.getPosition().getX();
			float curY = context.getPosition().getY();
			double angle = context.getAngle();

			if (context.getPosition().getX() <= 0) {
				counterGoes++;
			}
			if ((context.getPosition().getX() + context.getRadius()) >= GameLib.WIDTH) {
				counterGoes++;
			}

			if (counterGoes % 2 == 1) {
				//curX += context.getVelocity().getX() * Math.sin(angle)
				//		* Time.getInstance().deltaTime();
				curX += context.getVelocity().getX() * Math.sin(angle)
						* Time.getInstance().deltaTime();
				curY = 150 + (float)(100*Math.sin((curX % 6*Math.PI)));
			} else {
				//curX -= context.getVelocity().getX() * Math.sin(angle)
				//		* Time.getInstance().deltaTime();
				curX -= context.getVelocity().getX() * Math.sin(angle)
						* Time.getInstance().deltaTime();
				curY =  150 + (float)(100*Math.sin((curX % 6*Math.PI)));
			}

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
