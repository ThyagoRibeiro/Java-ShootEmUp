package game.entities.hud;

import game.entities.Entity;
import game.util.LocalTime;
import game.util.Time;;

public class Infos extends Entity {

	private LocalTime localTime = new LocalTime();
	private int stageNumber;

	public Infos(Entity character, int stageNumber) {
		super(character.getPosition(), character.getVelocity(), character.getRadius(), character.getScreenState());
		this.stageNumber = stageNumber;
		this._state = new ActiveInfosState(this);
		this._type = EntityType.HUD;
	}

	public int getTime() {
		return ((int) (Time.getInstance().CurrentTime() - localTime.getStart()))/ 1000;
	}
	
	public int getStageNumber() {
		return stageNumber;
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
