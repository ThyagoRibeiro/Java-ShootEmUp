package game.entities.state;

import game.GameLib;
import game.entities.Entity;
import game.util.LocalTime;
import game.util.Time;

public class ExplodingState implements EntityState {

	private Entity context;

	private LocalTime localTime;

	public ExplodingState(Entity context, double duration) {
		this.context = context;
		localTime = new LocalTime(duration);
		context.setCollisionState(null);
	}

	@Override
	public void render() {
		double alpha = (Time.getInstance().currentTime() - localTime.getStart())
				/ (localTime.getEnd() - localTime.getStart());
		GameLib.drawExplosion(context.getPosition().getX(), context
				.getPosition().getY(), alpha);
	}

	@Override
	public void update() {
		if (localTime.hasEnded())
			context.remove();
	}

}
