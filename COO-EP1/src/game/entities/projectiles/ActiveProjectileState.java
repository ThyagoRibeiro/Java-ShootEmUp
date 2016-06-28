package game.entities.projectiles;

import java.awt.Color;

import game.GameLib;
import game.entities.Entity.EntityType;
import game.entities.state.EntityState;
import game.util.Draw;

public class ActiveProjectileState implements EntityState {

	private Projectile context;

	public ActiveProjectileState(Projectile context) {
		this.context = context;
	}

	@Override
	public void Update() {
		context.UpdatePosition();
		if (context.getPosition().getY() < -5 || context.getPosition().getY() > GameLib.HEIGHT + 5)
			context.Remove();
	}

	@Override
	public void Render() {
		if (context.getEntityType() == EntityType.FriendlyProjectile) {
			Draw.setColor(Color.GREEN);
			Draw.drawLine(context.getPosition().getX(), context.getPosition().getY() - 5, context.getPosition().getX(),
					context.getPosition().getY() + 5);
			Draw.drawLine(context.getPosition().getX() - 1, context.getPosition().getY() - 3,
					context.getPosition().getX() - 1, context.getPosition().getY() + 3);
			Draw.drawLine(context.getPosition().getX() + 1, context.getPosition().getY() - 3,
					context.getPosition().getX() + 1, context.getPosition().getY() + 3);
		} else {
			Draw.setColor(Color.RED);
			Draw.drawCircle(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
		}
	}
}
