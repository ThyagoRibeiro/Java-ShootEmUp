package game.entities.enemies.enemy1;

import game.GameLib;
import game.Vector2D;
import game.entities.states.EntityState;
import game.utils.LocalTime;
import game.utils.Time;

import java.awt.Color;

public class ActiveEnemy1State implements EntityState {

	private Enemy1 context;
	private LocalTime shootTime;
	private double waitTime;

	// Construtor

	public ActiveEnemy1State(Enemy1 context) {
		this.context = context;
		shootTime = new LocalTime(Math.random() * waitTime);
		waitTime = 1000;
	}

	/* GETTERS E SETTERS - INICIO */

	public Enemy1 getContext() {
		return context;
	}

	public void setContext(Enemy1 context) {
		this.context = context;
	}

	public LocalTime getShootTime() {
		return shootTime;
	}

	public void setShootTime(LocalTime shootTime) {
		this.shootTime = shootTime;
	}

	public double getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o
	// inimigo de maneira especifica.

	@Override
	public void render() {
		GameLib.setColor(Color.CYAN);
		GameLib.drawCircle(context.getPosition().getCoordX(), context.getPosition()
				.getCoordY(), context.getRadius());
	}

	@Override
	public void update() {
		if (context.getPosition().getCoordY() > GameLib.HEIGHT + 10) {
			context.removeEntity();
		} else {
			float curX = context.getPosition().getCoordX();
			float curY = context.getPosition().getCoordY();
			double angle = context.getAngle();
			curX += context.getVelocity().getCoordX() * Math.cos(angle)
					* Time.getInstance().deltaTime();
			curY -= context.getVelocity().getCoordY() * Math.sin(angle)
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
