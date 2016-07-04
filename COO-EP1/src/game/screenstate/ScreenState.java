package game.screenstate;

import game.entities.EntityManager;

public abstract class ScreenState {

	protected EntityManager entityManager;

	public ScreenState() {
		this.entityManager = new EntityManager();
	}

	public abstract void draw(ScreenContext context);

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public abstract void onEnter(ScreenContext context);

	public abstract void onLeave(ScreenContext context);

	public abstract void update(ScreenContext context);

}
