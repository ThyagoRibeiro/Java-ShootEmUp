package game.entities.hud;

import java.awt.Color;

import game.entities.Entity;;

public class Time extends Entity {

	private int secs;

	public Time(int healthPoints, Color lifeColor, Entity character) {
		super(character.getPosition(), character.getVelocity(), character.getRadius(), character.getScreenState());

		this._type = EntityType.HUD;
	}

	public int getTime() {
		return secs;
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
