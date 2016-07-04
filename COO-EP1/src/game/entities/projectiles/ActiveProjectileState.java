package game.entities.projectiles;

import game.GameLib;
import game.entities.Entity.EntityType;
import game.entities.state.EntityState;
import game.entities.weapons.WeaponsFactory.WeaponType;

import java.awt.Color;

public class ActiveProjectileState implements EntityState {

	private Projectile context;
	private double angle = 1;

	public ActiveProjectileState(Projectile context) {
		this.context = context;
	}

	@Override
	public void render() {
		if (context.getEntityType().equals(EntityType.FRIENDLY_PROJECTILE)) {
			GameLib.setColor(Color.GREEN);
			if (context.getWeaponType().equals(WeaponType.PLAYER_DEFAULT_SHOT)) {
				GameLib.drawLine(context.getPosition().getX(), context
						.getPosition().getY() - 5,
						context.getPosition().getX(), context.getPosition()
								.getY() + 5);
				GameLib.drawLine(context.getPosition().getX() - 1, context
						.getPosition().getY() - 3,
						context.getPosition().getX() - 1, context.getPosition()
								.getY() + 3);
				GameLib.drawLine(context.getPosition().getX() + 1, context
						.getPosition().getY() - 3,
						context.getPosition().getX() + 1, context.getPosition()
								.getY() + 3);
			} else if (context.getWeaponType().equals(WeaponType.PLAYER_SHIELD)) {

				float x = (float) (context.getPlayer().getPosition().getX() + 8
						* context.getPlayer().getRadius() * Math.sin(angle));
				float y = (float) (context.getPlayer().getPosition().getY() + 8
						* context.getPlayer().getRadius() * Math.cos(angle));

				angle = angle + 0.01;

				context.setX(x);
				context.setY(y);
				GameLib.setColor(Color.GRAY);
				GameLib.fillCircle(x, y, context.getRadius());

			} else if (context.getWeaponType().equals(WeaponType.PLAYER_MEGA_SHOT)) {
				GameLib.fillCircle(context.getPosition().getX(), context
						.getPosition().getY(), context.getRadius());
			}

		} else if (context.getEntityType().equals(EntityType.ENEMY_PROJECTILE)) {
			GameLib.setColor(Color.RED);
			GameLib.fillCircle(context.getPosition().getX(), context
					.getPosition().getY(), context.getRadius());
		}
	}

	@Override
	public void update() {
		context.updatePosition();
		if (context.getPosition().getY() < -5
				|| context.getPosition().getY() > GameLib.HEIGHT + 5) {
			context.remove();
		}
	}
}
