package game.entities.player;

import game.GameLib;
import game.entities.state.EntityState;
import game.util.Draw;
import game.util.Time;

public class ActivePlayerState implements EntityState {

	private Player context;

	public ActivePlayerState(Player context) {
		this.context = context;
	}

	@Override
	public void Render() {

		Draw.setColor(context.getColor());
		Draw.drawPlayer(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
	}

	@Override
	public void Update() {
		float lx, ly;

		if (GameLib.iskeyPressed(GameLib.KEY_UP))
			ly = -1.0f;
		else if (GameLib.iskeyPressed(GameLib.KEY_DOWN))
			ly = 1.0f;
		else
			ly = 0.0f;

		if (GameLib.iskeyPressed(GameLib.KEY_LEFT))
			lx = -1.0f;
		else if (GameLib.iskeyPressed(GameLib.KEY_RIGHT))
			lx = 1.0f;
		else
			lx = 0.0f;

		context.setPosition(context.getPosition()
				.Add(context.getVelocity().Multiply(lx, ly).Multiply(Time.getInstance().DeltaTime())));

		if (context.getPosition().getX() - context.getRadius() < 0)
			context.setX((float) context.getRadius());
		else if (context.getPosition().getX() > GameLib.WIDTH - context.getRadius())
			context.setX(GameLib.WIDTH - (float) context.getRadius());
		if (context.getPosition().getY() - context.getRadius() < GameLib.HEIGHT / 2.0f)
			context.setY((GameLib.HEIGHT / 2.0f) + (float) context.getRadius());
		else if (context.getPosition().getY() > GameLib.HEIGHT - context.getRadius())
			context.setY(GameLib.HEIGHT - (float) context.getRadius());

		if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
			context.TryShoot();
		}
	}

}
