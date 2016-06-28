package game.entities.enemies.enemy1;

import java.awt.Color;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Draw;
import game.util.LocalTime;
import game.util.Time;
import geometry.Vector2D;

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
		if (shootTime.hasEnded()) {
			context.Shoot();
			shootTime.Start(Math.random() * waitTime);
		}
	}

	@Override
	public void Render() {
		Draw.setColor(Color.CYAN);
		Draw.drawCircle(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
	}
}
