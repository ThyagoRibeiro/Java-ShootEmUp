package game.entities.spawner;

import java.util.ArrayList;

import game.screenstate.ScreenState;

public class SpawnManager {

	private ScreenState screenState;
	private ArrayList<EntitySpawner> spawner;

	private ArrayList<EntitySpawner> toAdd;

	public SpawnManager(ScreenState screenState) {
		spawner = new ArrayList<EntitySpawner>();
		toAdd = new ArrayList<EntitySpawner>();
		this.screenState = screenState;
	}

	public void add(EntitySpawner spawner) {
		toAdd.add(spawner);
	}

	public void clearAll() {
		spawner.clear();
		toAdd.clear();
	}

	public ScreenState getScreenState() {
		return this.screenState;
	}

	public void update() {
		while (toAdd.size() > 0) {
			EntitySpawner es = toAdd.get(toAdd.size() - 1);
			if (spawner.add(es)) {
				toAdd.remove(toAdd.size() - 1);
			}
		}
		for (EntitySpawner es : spawner) {
			es.update();
		}
	}
}
