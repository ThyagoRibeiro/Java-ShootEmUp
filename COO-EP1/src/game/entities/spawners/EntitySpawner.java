package game.entities.spawners;

import game.utils.LocalTime;

public abstract class EntitySpawner {

	protected LocalTime localTime;
	protected SpawnManager spawnManager;
	protected boolean spawned;
	protected int x;
	protected int y;

	// Construtor

	public EntitySpawner(SpawnManager spawnManager, int when, int x, int y) {
		localTime = new LocalTime(when);
		this.spawnManager = spawnManager;
		this.spawnManager.addSpawner(this);
		this.x = x;
		this.y = y;
	}

	/* GETTERS E SETTERS - INICIO */

	public LocalTime getLocalTime() {
		return this.localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}

	public SpawnManager getSpawmManager() {
		return this.spawnManager;
	}

	public void setSpawnManager(SpawnManager spawnManager) {
		this.spawnManager = spawnManager;
	}

	public boolean isSpawned() {
		return spawned;
	}

	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodo abstrato para quando as outras classes extenderem a
	// EntitySpawner elas serem obrigadas a sobrescreverem esse metodo.

	protected abstract void spawn(int x, int y);

	// Metodo que inicializa a entitdade a ser colocada na tela.

	public void spawn() {
		if (localTime.hasEnded() && !spawned) {
			spawn(x, y);
			spawned = true;
		}
	}
}
