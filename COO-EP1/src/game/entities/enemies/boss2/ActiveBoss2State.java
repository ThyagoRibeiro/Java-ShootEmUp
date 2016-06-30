package game.entities.enemies.boss2;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Draw;
import game.util.LocalTime;
import game.util.Time;
import geometry.Vector2D;

public class ActiveBoss2State implements EntityState {

	private Boss2 context;
	private int counterGoes;
	private LocalTime shootTime;

	private double waitTime;

	public ActiveBoss2State(Boss2 context) {
		waitTime = 750;
		this.context = context;
		shootTime = new LocalTime(Math.random() * waitTime);
		counterGoes = 0;
	}

	@Override
	public void Render() {
		Draw.setColor(context.getColor());
		Draw.drawDiamond(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
		Draw.drawCircle(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
	}

	@Override
	public void Update() {
		if (context.getPosition().getY() <= 0) {
			context.setPosition(new Vector2D(1, 150));
		} else {
			float curX = context.getPosition().getX();
			float curY = context.getPosition().getY();
			double angle = context.getAngle();

			if (context.getPosition().getX() <= 0) {
//				System.out.println("\tAtualizou o sentido: " + counterGoes);
				counterGoes++;
			}
			if ((context.getPosition().getX() + context.getRadius()) >= GameLib.WIDTH) {
//				System.out.println("\tAtualizou o sentido: " + counterGoes);
				counterGoes++;
			}

			if (counterGoes % 2 == 1) {
				curX += context.getVelocity().getX() * Math.sin(angle) * Time.getInstance().DeltaTime();
			} else {
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
