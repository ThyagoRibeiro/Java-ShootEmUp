package game.entities.powerups.powerup2;

import game.GameLib;
import game.Vector2D;
import game.entities.states.EntityState;
import game.utils.Time;

import java.awt.Color;

public class PowerUp2State implements EntityState {

	private PowerUp2 context;

	// Construtor

	public PowerUp2State(PowerUp2 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public PowerUp2 getContext() {
		return this.context;
	}

	public void setContext(PowerUp2 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o
	// power up de maneira especifica.

	@Override
	public void render() {
		GameLib.setColor(Color.PINK);
		GameLib.drawCircle(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), context.getRadius());
		GameLib.writeInCircle(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), "M", context.getRadius());
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
	}
}
