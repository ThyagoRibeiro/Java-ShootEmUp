package game.entities.huds;

import game.entities.Entity;
import game.entities.constants.EntityTypeEnum;

public class LifeBar extends Entity {

	private EntityTypeEnum characterType;
	private double maxHealthPoints;
	private double currentHealthPoints;

	// Construtor

	public LifeBar(int healthPoints, Entity character) {
		super(character.getPosition(), character.getVelocity(), character
				.getRadius(), character.getScreenState());
		this.characterType = character.getEntityType();
		this.maxHealthPoints = this.currentHealthPoints = healthPoints;
		this.state = new LifeBarState(this);
		this.entityType = EntityTypeEnum.HUD;
	}

	/* GETTERS E SETTERS - INICIO */

	public EntityTypeEnum getCharacterType() {
		return characterType;
	}

	public void setCharacterType(EntityTypeEnum characterType) {
		this.characterType = characterType;
	}

	public double getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(double maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	public double getCurrentHealthPoints() {
		return currentHealthPoints;
	}

	public void setCurrentHealthPoints(double currentHealthPoints) {
		this.currentHealthPoints = currentHealthPoints;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodo que diminui a vida.
	
	public void decreaseCurrentHealthPoints() {
		currentHealthPoints--;
	}

	// Metodo que retorna a porcentagem da barra de vida.
	
	public double getLifePointsPercent() {
		return currentHealthPoints / maxHealthPoints;
	}

	// Sobrescrita dos metodos abstratos da classe Entity, para tratar a
	// renderização e a atualização das barras de vida.

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
