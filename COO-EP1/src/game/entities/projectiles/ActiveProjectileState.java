package game.entities.projectiles;

import game.GameLib;
import game.entities.constants.EntityTypeEnum;
import game.entities.constants.WeaponTypeEnum;
import game.entities.state.EntityState;

import java.awt.Color;

public class ActiveProjectileState implements EntityState {

	private Projectile context;
	private double angle = 1;

	// Construtor

	public ActiveProjectileState(Projectile context) {
		this.context = context;
	}

	/* GETTERS E SETTERS - INICIO */

	public Projectile getContext() {
		return this.context;
	}

	public void setContext(Projectile context) {
		this.context = context;
	}

	public double getAngle() {
		return this.angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	/* GETTERS E SETTERS - FIM */

	// Sobrescrita dos metodos da interface para renderizar e atualizar o
	// projetil de maneira especifica.

	@Override
	public void render() {
		if (context.getEntityType().equals(EntityTypeEnum.FRIENDLY_PROJECTILE)) {
			GameLib.setColor(Color.GREEN);
			if (context.getWeaponType().equals(
					WeaponTypeEnum.PLAYER_DEFAULT_SHOT)) {
				GameLib.drawLine(context.getPosition().getCoordX(), context
						.getPosition().getCoordY() - 5, context.getPosition()
						.getCoordX(), context.getPosition().getCoordY() + 5);
				GameLib.drawLine(context.getPosition().getCoordX() - 1, context
						.getPosition().getCoordY() - 3, context.getPosition()
						.getCoordX() - 1, context.getPosition().getCoordY() + 3);
				GameLib.drawLine(context.getPosition().getCoordX() + 1, context
						.getPosition().getCoordY() - 3, context.getPosition()
						.getCoordX() + 1, context.getPosition().getCoordY() + 3);
			} else if (context.getWeaponType().equals(
					WeaponTypeEnum.PLAYER_SHIELD)) {

				float x = (float) (context.getPlayer().getPosition()
						.getCoordX() + 8 * context.getPlayer().getRadius()
						* Math.sin(angle));
				float y = (float) (context.getPlayer().getPosition()
						.getCoordY() + 8 * context.getPlayer().getRadius()
						* Math.cos(angle));

				angle = angle + 0.01;

				context.setPositionX(x);
				context.setPositionY(y);
				GameLib.setColor(Color.GRAY);
				GameLib.fillCircle(x, y, context.getRadius());

			} else if (context.getWeaponType().equals(
					WeaponTypeEnum.PLAYER_MEGA_SHOT)) {
				GameLib.fillCircle(context.getPosition().getCoordX(), context
						.getPosition().getCoordY(), context.getRadius());
			}

		} else if (context.getEntityType().equals(
				EntityTypeEnum.ENEMY_PROJECTILE)) {
			GameLib.setColor(Color.RED);
			GameLib.fillCircle(context.getPosition().getCoordX(), context
					.getPosition().getCoordY(), context.getRadius());
		}
	}

	@Override
	public void update() {
		context.updateEntityPosition();
		if (context.getPosition().getCoordY() < -5
				|| context.getPosition().getCoordY() > GameLib.HEIGHT + 5) {
			context.removeEntity();
		}
	}
}
