package game.entities.enemies.boss2;

import game.GameLib;
import game.Vector2D;
import game.entities.states.EntityState;
import game.utils.LocalTime;

public class ActiveBoss2State implements EntityState {

	private Boss2 context;
	private boolean goingRight;
	private LocalTime shootTime;
	private double waitTime;

	// Construtor

	public ActiveBoss2State(Boss2 context) {
		this.context = context;
		goingRight = true;
		shootTime = new LocalTime(Math.random() * waitTime);
		waitTime = 100;
	}

	/* GETTERS E SETTES - INICIO */

	public Boss2 getContext() {
		return context;
	}

	public void setContext(Boss2 context) {
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

	// Sobrescrita dos metodos da interface para renderizar e atualizar o chefe
	// de maneira unica.

	@Override
	public void render() {
		GameLib.setColor(context.getCurrentColor());
		GameLib.drawDiamond(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), context.getRadius());
		GameLib.drawCircle(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), context.getRadius());
	}

	@Override
	public void update() {
		float curX = context.getPosition().getCoordX();
		float curY = context.getPosition().getCoordY();

		// Se chegar no limite direito da tela
		if ((curX + context.getRadius()) >= GameLib.WIDTH) {
			goingRight = false;
		}
		// Se chegar no limite esquerdo da tela
		if ((curX - context.getRadius()) <= 0) {
			goingRight = true;
		}

		if (goingRight) {
			curX += context.getVelocity().getCoordX() * 9;
		} else {
			curX -= context.getVelocity().getCoordX() * 9;
		}

		context.setPosition(new Vector2D(curX, curY));

		if (shootTime.hasEnded()) {
			context.shoot();
			shootTime.start(Math.random() * waitTime);
		}
	}
}
