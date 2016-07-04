package game.entities.hud;

import game.entities.Entity;
import game.entities.player.Player;
import game.util.LocalTime;
import game.util.Time;

;

public class Infos extends Entity {

	private LocalTime localTime = new LocalTime();
	private int stageNumber;
	private Player player;

	public Infos(Player player) {
		super(player.getPosition(), player.getVelocity(), player.getRadius(),
				player.getScreenState());
		this.player = player;
		this.stageNumber = player.getMainGameScreen().getStageNumber();
		this.state = new ActiveInfosState(this);
		this.entityType = EntityType.HUD;
	}

	public Player getPlayer() {
		return player;
	}

	public int getStageNumber() {
		return stageNumber;
	}

	public int getTime() {
		return ((int) (Time.getInstance().currentTime() - localTime.getStart())) / 1000;
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
