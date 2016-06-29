package game.entities.spawner;

import game.entities.Entity;
import game.util.LocalTime;

public abstract class EntitySpawner {

	protected LocalTime _localTime;
	protected SpawnManager _spawnManager;
	protected boolean spawned;
	protected int x, y;

	public EntitySpawner(SpawnManager spawnManager, int when, int x, int y) {
		this.x = x;
		this.y = y;
		_localTime = new LocalTime(when);
		this._spawnManager = spawnManager;
		this._spawnManager.Add(this);
	}

	protected abstract Entity Spawn(int x, int y);

	public void Update() {
		if (_localTime.hasEnded() && !spawned) {
			Spawn(x, y);
			spawned = true;
		}
	}
}
