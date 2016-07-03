package game.entities.hud;

import game.entities.Entity;
import game.entities.player.Player;
import game.util.LocalTime;
import game.util.Time;;

public class Infos extends Entity {

	private LocalTime localTime = new LocalTime();
	private int stageNumber;
	private Player player;

	public Infos(Player player) {
		super(player.getPosition(), player.getVelocity(), player.getRadius(), player.getScreenState());
		this.player = player;
		this.stageNumber = player.getMainGameScreen().getStageNumber();
		this._state = new ActiveInfosState(this);
		this._type = EntityType.HUD;
	}

	public Player getPlayer() {
		return player;
	}

	public int getStageNumber() {
		return stageNumber;
	}

	public int getTime() {
		return ((int) (Time.getInstance().CurrentTime() - localTime.getStart())) / 1000;
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
