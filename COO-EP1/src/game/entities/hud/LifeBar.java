package game.entities.hud;

import game.entities.Entity;;

public class LifeBar extends Entity {

	private EntityType characterType;
	private double maxHealthPoints, currentHealthPoints;

	public LifeBar(int healthPoints, Entity character) {
		super(character.getPosition(), character.getVelocity(), character.getRadius(), character.getScreenState());
		this.characterType = character.getEntityType();
		this.maxHealthPoints = this.currentHealthPoints = healthPoints;
		this._state = new ActiveLifeBarState(this);
		this._type = EntityType.HUD;
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
	public void Render() {
		this._state.Render();
	}

	@Override
	public void Update() {
		this._state.Update();
	}

}
