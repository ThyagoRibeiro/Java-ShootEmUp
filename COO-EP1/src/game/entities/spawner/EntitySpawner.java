package game.entities.spawner;

import game.util.LocalTime;

public abstract class EntitySpawner {

	protected LocalTime localTime;
	protected SpawnManager spawnManager;
	protected boolean spawned;
	protected int x;
	protected int y;

	public EntitySpawner(SpawnManager spawnManager, int when, int x, int y) {
		this.x = x;
		this.y = y;
		localTime = new LocalTime(when);
		this.spawnManager = spawnManager;
		this.spawnManager.add(this);
	}

	protected abstract void spawn(int x, int y);

	public void update() {
		if (localTime.hasEnded() && !spawned) {
			spawn(x, y);
			spawned = true;
		}
	}
}
