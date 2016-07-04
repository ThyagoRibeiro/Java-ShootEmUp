package game.entities;

import game.GameLib;
import game.entities.Entity.EntityType;
import game.entities.collision.Collidable;
import game.entities.player.Player;

import java.awt.Color;
import java.util.ArrayList;

public class EntityManager {

	private ArrayList<Entity> collideableEntities = new ArrayList<Entity>();

	private ArrayList<Entity> hudEntities = new ArrayList<Entity>();
	private ArrayList<Entity> normalEntities = new ArrayList<Entity>();
	private Player player;

	private ArrayList<Entity> toAdd = new ArrayList<Entity>();
	private ArrayList<Entity> toRemove = new ArrayList<Entity>();

	public EntityManager() {
		player = null;
		collideableEntities = new ArrayList<Entity>();
		normalEntities = new ArrayList<Entity>();
		hudEntities = new ArrayList<Entity>();
		toRemove = new ArrayList<Entity>();
		toAdd = new ArrayList<Entity>();
	}

	public void addEntity(Entity entity) {
		toAdd.add(entity);
	}

	public void checkForCollisions() {
		for (Entity entity1 : collideableEntities) {
			Collidable collidable = (Collidable) entity1;
			for (Entity entity2 : collideableEntities) {
				if (collidable.checkCollision(entity2)) {
					if (entity1.collision != null)
						entity1.collision.onCollision(entity2);
				}
			}
		}
	}

	public void clearAll() {
		collideableEntities.clear();
		normalEntities.clear();
		hudEntities.clear();
		toRemove.clear();
		player = null;
	}

	public boolean isPlayerDead() {
		return player == null;
	}

	public void remove(Entity entity) {
		toRemove.add(entity);
	}

	public void renderEntities() {
		for (Entity entity : normalEntities) {
			entity.render();
		}

		for (Entity entity : collideableEntities) {
			entity.render();
		}

		GameLib.setColor(Color.BLACK);
		GameLib.fillRect(0, 0, 480, 100);

		for (Entity entity : hudEntities) {
			entity.render();
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void updateEntities() {
		while (toAdd.size() != 0) {
			Entity tmp = toAdd.get(toAdd.size() - 1);
			if (tmp instanceof Collidable) {
				collideableEntities.add(tmp);
			} else if (tmp.getEntityType() == EntityType.HUD) {
				hudEntities.add(tmp);
			} else {
				normalEntities.add(tmp);
			}
			toAdd.remove(toAdd.size() - 1);
		}
		for (Entity entity : collideableEntities) {
			entity.update();
		}
		for (Entity entity : normalEntities) {
			entity.update();
		}
		for (Entity entity : hudEntities) {
			entity.update();
		}
		while (toRemove.size() != 0) {
			Entity tmp = toRemove.get(toRemove.size() - 1);
			if (collideableEntities.remove(tmp) || normalEntities.remove(tmp)
					|| hudEntities.remove(tmp)) {
				if (player != null && tmp == player) {
					player = null;
				}
				toRemove.remove(toRemove.size() - 1);
			}
		}

	}
}
