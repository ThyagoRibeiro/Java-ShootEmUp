package game.entities.states;

import game.GameLib;
import game.entities.Entity;
import game.utils.LocalTime;
import game.utils.Time;

public class ExplodingState implements EntityState {

	private Entity context;
	private LocalTime localTime;

	// Construtor

	public ExplodingState(Entity context, double duration) {
		this.context = context;
		context.setCollisionState(null);
		localTime = new LocalTime(duration);
	}

	/* GETTERS E SETTERS - INICIO */

	public Entity getContext() {
		return context;
	}

	public void setContext(Entity context) {
		this.context = context;
	}

	public LocalTime getLocalTime() {
		return this.localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar a
	// explosao de maneira especifica.

	@Override
	public void render() {
		double alpha = (Time.getInstance().currentTime() - localTime.getStart())
				/ (localTime.getEnd() - localTime.getStart());
		GameLib.drawExplosion(context.getPosition().getCoordX(), context
				.getPosition().getCoordY(), alpha);
	}

	@Override
	public void update() {
		if (localTime.hasEnded())
			context.removeEntity();
	}

}
