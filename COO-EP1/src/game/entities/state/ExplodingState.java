package game.entities.state;

import game.entities.Entity;
import game.util.Draw;
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
	public void Render() {
		double alpha = (Time.getInstance().CurrentTime() - localTime.getStart())
				/ (localTime.getEnd() - localTime.getStart());
		Draw.drawExplosion(context.getPosition().getX(), context.getPosition().getY(), alpha);
	}

	@Override
	public void Update() {
		if (localTime.hasEnded())
			context.Remove();
	}

}
