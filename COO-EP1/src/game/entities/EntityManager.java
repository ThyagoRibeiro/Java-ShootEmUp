package game.entities;

import game.GameLib;
import game.entities.collisions.Collidable;
import game.entities.constants.EntityTypeEnum;
import game.entities.players.Player;

import java.awt.Color;
import java.util.ArrayList;

public class EntityManager {

	private Player player;
	private ArrayList<Entity> hudEntities = new ArrayList<Entity>();
	private ArrayList<Entity> normalEntities = new ArrayList<Entity>();
	private ArrayList<Entity> collidableEntities = new ArrayList<Entity>();
	private ArrayList<Entity> toAdd = new ArrayList<Entity>();
	private ArrayList<Entity> toRemove = new ArrayList<Entity>();

	// Construtor

	public EntityManager() {
		player = null;
		hudEntities = new ArrayList<Entity>();
		normalEntities = new ArrayList<Entity>();
		collidableEntities = new ArrayList<Entity>();
		toAdd = new ArrayList<Entity>();
		toRemove = new ArrayList<Entity>();
	}

	/* GETTERS E SETTERS - INICIO */

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getHudEntities() {
		return hudEntities;
	}

	public void setHudEntities(ArrayList<Entity> hudEntities) {
		this.hudEntities = hudEntities;
	}

	public ArrayList<Entity> getNormalEntities() {
		return normalEntities;
	}

	public void setNormalEntities(ArrayList<Entity> normalEntities) {
		this.normalEntities = normalEntities;
	}

	public ArrayList<Entity> getCollidableEntities() {
		return collidableEntities;
	}

	public void setCollidableEntities(ArrayList<Entity> collidableEntities) {
		this.collidableEntities = collidableEntities;
	}

	public ArrayList<Entity> getToAdd() {
		return toAdd;
	}

	public void setToAdd(ArrayList<Entity> toAdd) {
		this.toAdd = toAdd;
	}

	public ArrayList<Entity> getToRemove() {
		return toRemove;
	}

	public void setToRemove(ArrayList<Entity> toRemove) {
		this.toRemove = toRemove;
	}

	/* GETTERS E SETTERS - FIM */

	// Método que chega colisão entre todas as entidades que tem a possibilidade
	// de colidir.

	public void checkForCollisions() {
		for (Entity entity1 : collidableEntities) {
			Collidable collidable = (Collidable) entity1;
			for (Entity entity2 : collidableEntities) {
				if (collidable.checkCollision(entity2)) {
					if (entity1.collisionState != null)
						entity1.collisionState.onCollision(entity2);
				}
			}
		}
	}

	// Metodo que limpa todas as variáveis do EntityManager.

	public void clearAll() {
		player = null;
		hudEntities.clear();
		normalEntities.clear();
		collidableEntities.clear();
		toAdd.clear();
		toRemove.clear();
	}

	// Metodo que verifica se o usuário está morto.

	public boolean isPlayerDead() {
		return player == null;
	}

	// Metodo que adiciona uma entidade a lista de entidades que devem ser
	// adicionadas.

	public void addEntity(Entity entity) {
		toAdd.add(entity);
	}

	// Metodo que remove uma entidade do jogo, adicionando-a a lista de
	// entidades que devem ser removidas.

	public void remove(Entity entity) {
		toRemove.add(entity);
	}

	// Metodo que renderiza todas as entidades nas listas.

	public void renderEntities() {
		for (Entity entity : normalEntities) {
			entity.render();
		}
		for (Entity entity : collidableEntities) {
			entity.render();
		}
		GameLib.setColor(Color.BLACK);
		GameLib.fillRect(0, 0, 480, 100);
		for (Entity entity : hudEntities) {
			entity.render();
		}
	}

	// Metodo que atualiza o estado das entidades e as adiciona em suas
	// respectivas listas.

	public void updateEntities() {
		while (!toAdd.isEmpty()) {
			Entity tmp = toAdd.get(toAdd.size() - 1);
			if (tmp instanceof Collidable) {
				collidableEntities.add(tmp);
			} else if (tmp.getEntityType() == EntityTypeEnum.HUD) {
				hudEntities.add(tmp);
			} else {
				normalEntities.add(tmp);
			}
			toAdd.remove(toAdd.size() - 1);
		}
		for (Entity entity : collidableEntities) {
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
			if (collidableEntities.remove(tmp) || normalEntities.remove(tmp)
					|| hudEntities.remove(tmp)) {
				if (player != null && tmp == player) {
					player = null;
				}
				toRemove.remove(toRemove.size() - 1);
			}
		}
	}
	
}
