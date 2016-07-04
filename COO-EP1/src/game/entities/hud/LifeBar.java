package game.entities.hud;

import game.entities.Entity;

public class LifeBar extends Entity {

	private EntityType characterType;
	private double maxHealthPoints;
	private double currentHealthPoints;

	public LifeBar(int healthPoints, Entity character) {
		super(character.getPosition(), character.getVelocity(), character
				.getRadius(), character.getScreenState());
		this.characterType = character.getEntityType();
		this.maxHealthPoints = this.currentHealthPoints = healthPoints;
		this.state = new ActiveLifeBarState(this);
		this.entityType = EntityType.HUD;
	}

	public void decreaseCurrentHealthPoints() {
		currentHealthPoints--;
	}

	public EntityType getCharacterType() {
		return characterType;
	}

	public double getCurrentHealthPoints() {
		return currentHealthPoints;
	}

	public double getLifePointsPercent() {
		return currentHealthPoints / maxHealthPoints;
	}

	@Override
	public void render() {
		this.state.render();
	}

	@Override
	public void update() {
		this.state.update();
	}

}
