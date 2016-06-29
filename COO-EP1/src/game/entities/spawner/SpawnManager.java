package game.entities.spawner;

import java.util.ArrayList;

import game.screenstate.ScreenState;

public class SpawnManager {

	private ScreenState _screenState;
	private ArrayList<EntitySpawner> _spawner;

	private ArrayList<EntitySpawner> _toadd;

	public SpawnManager(ScreenState screenState) {
		_spawner = new ArrayList<EntitySpawner>();
		_toadd = new ArrayList<EntitySpawner>();
		this._screenState = screenState;
	}

	public void Add(EntitySpawner spawner) {
		_toadd.add(spawner);
	}

	public void ClearAll() {
		_spawner.clear();
		_toadd.clear();
	}

	public ScreenState getScreenState() {
		return this._screenState;
	}

	public void Update() {
		while (_toadd.size() > 0) {
			EntitySpawner es = _toadd.get(_toadd.size() - 1);
			if (_spawner.add(es)) {
				_toadd.remove(_toadd.size() - 1);
			}
		}
		for (EntitySpawner es : _spawner) {
			es.Update();
		}
	}
}
