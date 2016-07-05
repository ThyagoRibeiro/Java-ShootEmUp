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

	// Construtor

	public ActiveBoss1State(Boss1 context) {
		this.context = context;
		counterGoes = 0;
		shootTime = new LocalTime(Math.random() * waitTime);
		waitTime = 1000;
	}

	/* GETTERS E SETTES - INICIO */

	public Boss1 getContext() {
		return context;
	}

	public void setContext(Boss1 context) {
		this.context = context;
	}

	public int getCounterGoes() {
		return counterGoes;
	}

	public void setCounterGoes(int counterGoes) {
		this.counterGoes = counterGoes;
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

	// Sobrescrita dos metodos da interface para renderizar e atualizar o chefe
	// de maneira unica.

	@Override
	public void render() {
		GameLib.setColor(context.getCurrentColor());
		GameLib.drawHourglass(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), context.getRadius());
	}

	@Override
	public void update() {
		if (context.getPosition().getCoordY() <= 0) {
			context.setPosition(new Vector2D(1, 150));
		} else {
			float curX = context.getPosition().getCoordX();
			float curY = context.getPosition().getCoordY();
			double angle = context.getAngle();

			if (context.getPosition().getCoordX() <= 0) {
				counterGoes++;
			}
			if ((context.getPosition().getCoordX() + context.getRadius()) >= GameLib.WIDTH) {
				counterGoes++;
			}

			if (counterGoes % 2 == 1) {
				curX += context.getVelocity().getCoordX() * Math.sin(angle)
						* Time.getInstance().deltaTime();
				curY = 150 + (float) (100 * Math.sin((curX % 6 * Math.PI)));
			} else {
				curX -= context.getVelocity().getCoordX() * Math.sin(angle)
						* Time.getInstance().deltaTime();
				curY = 150 + (float) (100 * Math.sin((curX % 6 * Math.PI)));
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
