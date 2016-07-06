package game.entities.powerups.powerup1;

import game.GameLib;
import game.Vector2D;
import game.entities.states.EntityState;
import game.utils.Time;

import java.awt.Color;

public class PowerUp1State implements EntityState {

	private PowerUp1 context;

	// Construtor

	public PowerUp1State(PowerUp1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public PowerUp1 getContext() {
		return this.context;
	}

	public void setContext(PowerUp1 context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o
	// power up de maneira especifica.

	@Override
	public void render() {
		GameLib.setColor(Color.GRAY);
		GameLib.drawCircle(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), context.getRadius());
		GameLib.writeInCircle(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), "S", context.getRadius());
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
