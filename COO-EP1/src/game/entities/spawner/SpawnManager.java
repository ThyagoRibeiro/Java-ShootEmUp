package game.entities.spawner;

import java.util.ArrayList;

import game.screenstate.ScreenState;

public class SpawnManager {

	private ScreenState screenState;
	private ArrayList<EntitySpawner> spawner = new ArrayList<EntitySpawner>();
	private ArrayList<EntitySpawner> toAdd = new ArrayList<EntitySpawner>();

	// Construtor

	public SpawnManager(ScreenState screenState) {
		this.screenState = screenState;
	}

	/* GETTERS E SETTERS - INICIO */

	public ScreenState getScreenState() {
		return this.screenState;
	}

	public void setScreenState(ScreenState screenState) {
		this.screenState = screenState;
	}

	public ArrayList<EntitySpawner> getSpawner() {
		return this.spawner;
	}

	public void setSpawner(ArrayList<EntitySpawner> spawner) {
		this.spawner = spawner;
	}

	public ArrayList<EntitySpawner> getToAdd() {
		return this.toAdd;
	}

	public void setToAdd(ArrayList<EntitySpawner> toAdd) {
		this.toAdd = toAdd;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodo que adiciona um inicializador a lista de inicializadores que devem
	// ser adicionadas.

	public void addSpawner(EntitySpawner spawner) {
		toAdd.add(spawner);
	}

	// Metodo que limpa todas as variáveis do SpawnManager.

	public void clearAll() {
		spawner.clear();
		toAdd.clear();
	}

	// Metodo que atualiza o estado dos inicializadores e as adiciona em suas
	// respectivas listas.

	public void updateSpawners() {
		while (toAdd.size() > 0) {
			EntitySpawner es = toAdd.get(toAdd.size() - 1);
			if (spawner.add(es)) {
				toAdd.remove(toAdd.size() - 1);
			}
		}
		for (EntitySpawner es : spawner) {
			es.spawn();
		}
	}
}
