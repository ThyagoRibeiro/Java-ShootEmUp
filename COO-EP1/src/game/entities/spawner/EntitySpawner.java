package game.entities.spawner;

import game.entities.Entity;
import game.util.LocalTime;

public abstract class EntitySpawner {

	protected LocalTime _localTime;
	protected double _respawnCooldown, _maxWait;
	protected SpawnManager _spawnManager;

	public EntitySpawner(SpawnManager spawnManager, double respawnCooldown, double maxWait, double initialWait) {
		_localTime = new LocalTime(initialWait);
		this._respawnCooldown = respawnCooldown;
		this._maxWait = maxWait;
		this._spawnManager = spawnManager;
		this._spawnManager.Add(this);
	}

	public void Update() {
		if (_localTime.hasEnded()) {
			Spawn();
			_localTime.Start(_respawnCooldown + (Math.random() * _maxWait));
		}
	}

	protected abstract Entity Spawn();
}
