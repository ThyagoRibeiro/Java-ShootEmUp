package game.entities.enemies.boss1;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Draw;
import game.util.LocalTime;
import game.util.Time;
import geometry.Vector2D;

public class ActiveBoss1State implements EntityState {

	private Boss1 context;
	private LocalTime shootTime;

	private double waitTime;

	public ActiveBoss1State(Boss1 context) {
		waitTime = 1000;
		this.context = context;
		shootTime = new LocalTime(Math.random() * waitTime);
	}

	@Override
	public void Render() {
		Draw.setColor(context.getColor());
		Draw.drawHourglass(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
	}

	@Override
	public void Update() {

		if (context.getPosition().getY() <= 0) {
			context.setPosition(new Vector2D(0, 150));
		} else {
			float curX = context.getPosition().getX();
			float curY = context.getPosition().getY();
			double angle = context.getAngle();

			boolean flagLeftWay = false;

			if (context.getPosition().getX() <= 0) {
				flagLeftWay = false;
			} else {
				if (context.getPosition().getX() + context.getRadius() >= GameLib.WIDTH) {
					flagLeftWay = true;
				}
			}

			if (flagLeftWay) {
				// System.out.println("indo p/ esq");
				curX += context.getVelocity().getX() * Math.sin(angle) * Time.getInstance().DeltaTime();
			} else {
				// System.out.println("indo p/ dir");
				curX -= context.getVelocity().getX() * Math.sin(angle) * Time.getInstance().DeltaTime();
			}

			angle += context.getRV() * Time.getInstance().DeltaTime();
			context.setPosition(new Vector2D(curX, curY));
			context.setAngle(angle);
		}
		if (shootTime.hasEnded()) {
			context.Shoot();
			shootTime.Start(Math.random() * waitTime);
		}
	}
}