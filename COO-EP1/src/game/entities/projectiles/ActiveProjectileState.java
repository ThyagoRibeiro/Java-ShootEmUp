package game.entities.projectiles;

import java.awt.Color;

import game.GameLib;
import game.entities.Entity.EntityType;
import game.entities.state.EntityState;
import game.entities.weapons.Weapon;
import game.entities.weapons.WeaponsFactory.WeaponType;
import game.util.Draw;

public class ActiveProjectileState implements EntityState {

	private Projectile context;
	private double angle = 1;

	public ActiveProjectileState(Projectile context) {
		this.context = context;
	}

	@Override
	public void Render() {
		if (context.getEntityType().equals(EntityType.FriendlyProjectile)) {
			Draw.setColor(Color.GREEN);
			if (context.getType().equals(WeaponType.PlayerDeafultShot)) {
				Draw.drawLine(context.getPosition().getX(), context.getPosition().getY() - 5,
						context.getPosition().getX(), context.getPosition().getY() + 5);
				Draw.drawLine(context.getPosition().getX() - 1, context.getPosition().getY() - 3,
						context.getPosition().getX() - 1, context.getPosition().getY() + 3);
				Draw.drawLine(context.getPosition().getX() + 1, context.getPosition().getY() - 3,
						context.getPosition().getX() + 1, context.getPosition().getY() + 3);
			}
			if (context.getType().equals(WeaponType.PlayerShield)) {

				float x = (float) (context.getPlayer().getPosition().getX()
						+ 8 * context.getPlayer().getRadius() * Math.sin(angle));
				float y = (float) (context.getPlayer().getPosition().getY()
						+ 8 * context.getPlayer().getRadius() * Math.cos(angle));

				angle = angle + 0.01;

				context.setX(x);
				context.setY(y);
				Draw.setColor(Color.GRAY);
				Draw.fillCircle(x, y, context.getRadius());

			} else {
				Draw.fillCircle(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
			}

		} else {
			Draw.setColor(Color.RED);
			Draw.fillCircle(context.getPosition().getX(), context.getPosition().getY(), context.getRadius());
		}
	}

	@Override
	public void Update() {
		context.UpdatePosition();
		if (context.getPosition().getY() < -5 || context.getPosition().getY() > GameLib.HEIGHT + 5)
			context.Remove();
	}
}
