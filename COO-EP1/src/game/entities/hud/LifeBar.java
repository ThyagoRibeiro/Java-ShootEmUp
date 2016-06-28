package game.entities.hud;

import java.awt.Color;

import game.entities.Entity;;

public class LifeBar extends Entity {

	private double maxHealthPoints, currentHealthPoints;
	private EntityType characterType;

	public LifeBar(int healthPoints, Color lifeColor, Entity character) {
		super(character.getPosition(), character.getVelocity(), character.getRadius(), character.getScreenState());
		this.characterType = character.getEntityType();
		this.maxHealthPoints = healthPoints;
		this.currentHealthPoints = healthPoints;
		this._state = new ActiveLifeBarState(this);
		this._type = EntityType.HUD;
	}

	public void decreaseCurrentHealthPoints() {
		currentHealthPoints--;
	}

	public double getCurrentHealthPoints() {
		return currentHealthPoints;
	}

	public EntityType getCharacterType() {
		return characterType;
	}

	public double getLifePointsPercent() {
		return currentHealthPoints / maxHealthPoints;
	}

	@Override
	public void Update() {
		this._state.Update();
	}

	@Override
	public void Render() {
		this._state.Render();
	}

}
