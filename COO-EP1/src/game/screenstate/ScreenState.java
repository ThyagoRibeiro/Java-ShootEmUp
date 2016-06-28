package game.screenstate;

import game.entities.EntityManager;

public abstract class ScreenState {

	protected EntityManager _entityManager;

	public ScreenState() {
		this._entityManager = new EntityManager();
	}

	public EntityManager getEntityManager() {
		return this._entityManager;
	}

	public abstract void OnEnter(ScreenContext context);

	public abstract void Update(ScreenContext context);

	public abstract void Draw(ScreenContext context);

	public abstract void OnLeave(ScreenContext context);

}
