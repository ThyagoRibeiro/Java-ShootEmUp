package game.entities;

import java.awt.Color;
import java.util.ArrayList;

import game.entities.Entity.EntityType;
import game.entities.collision.Collidable;
import game.entities.player.Player;
import game.util.Draw;

public class EntityManager {

	private Player _player;

	private ArrayList<Entity> _collideableEntities = new ArrayList<Entity>();
	private ArrayList<Entity> _normalEntities = new ArrayList<Entity>();
	private ArrayList<Entity> _hudEntities = new ArrayList<Entity>();

	private ArrayList<Entity> _toremove = new ArrayList<Entity>();
	private ArrayList<Entity> _toadd = new ArrayList<Entity>();

	public EntityManager() {
		_player = null;
		_collideableEntities = new ArrayList<Entity>();
		_normalEntities = new ArrayList<Entity>();
		_hudEntities = new ArrayList<Entity>();

		_toremove = new ArrayList<Entity>();
		_toadd = new ArrayList<Entity>();
	}

	public void addEntity(Entity entity) {
		_toadd.add(entity);
	}

	public void Remove(Entity entity) {
		_toremove.add(entity);
	}

	public void setPlayer(Player player) {
		_player = player;
	}

	public void ClearAll() {
		_collideableEntities.clear();
		_normalEntities.clear();
		_hudEntities.clear();
		_toremove.clear();
		_player = null;
	}

	public void UpdateEntities() {
		while (_toadd.size() != 0) {
			Entity tmp = _toadd.get(_toadd.size() - 1);
			if (tmp instanceof Collidable) {
				_collideableEntities.add(tmp);
			} else if (tmp.getEntityType() == EntityType.HUD) {
				_hudEntities.add(tmp);
			} else {
				_normalEntities.add(tmp);
			}
			_toadd.remove(_toadd.size() - 1);
		}
		for (Entity entity : _collideableEntities) {
			entity.Update();
		}
		for (Entity entity : _normalEntities) {
			entity.Update();
		}
		for (Entity entity : _hudEntities) {
			entity.Update();
		}
		while (_toremove.size() != 0) {
			Entity tmp = _toremove.get(_toremove.size() - 1);
			if (_collideableEntities.remove(tmp) || _normalEntities.remove(tmp) || _hudEntities.remove(tmp)) {
				if (_player != null && tmp == _player) {
					_player = null;
				}
				_toremove.remove(_toremove.size() - 1);
			}
		}

	}

	public void CheckForCollisions() {
		for (Entity ent1 : _collideableEntities) {
			Collidable col1 = (Collidable) ent1;
			for (Entity ent2 : _collideableEntities) {
				if (col1.CheckCollision(ent2)) {
					if (ent1._collision != null)
						ent1._collision.OnCollision(ent2);
				}
			}
		}
	}

	public void RenderEntities() {
		for (Entity entity : _normalEntities) {
			entity.Render();
		}
		
		for (Entity entity : _collideableEntities) {
			entity.Render();
		}

		Draw.setColor(Color.BLACK);
		Draw.fillRect(0, 0, 480, 100);

		for (Entity entity : _hudEntities) {
			entity.Render();
		}
	}

	public boolean isPlayerDead() {
		return _player == null;
	}
}
