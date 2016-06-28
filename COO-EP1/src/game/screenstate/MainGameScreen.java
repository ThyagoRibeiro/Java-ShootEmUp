package game.screenstate;

import game.entities.backgroundstar.BackgroundStar;
import game.entities.player.Player;
import game.entities.spawner.Enemy1Spawner;
import game.entities.spawner.Enemy2Spawner;
import game.entities.spawner.SpawnManager;
import game.entities.weapons.WeaponsFactory;
import game.util.LocalTime;
import geometry.Vector2D;

public class MainGameScreen extends ScreenState {

	Player _player;
	private LocalTime ltime = null;
	private SpawnManager _spawnManager;

	public MainGameScreen() {
		super();
	}

	@Override
	public void Update(ScreenContext context) {
		_spawnManager.Update();
		if (_entityManager.isPlayerDead() && ltime == null) {
			ltime = new LocalTime(1200);
		}
		if (ltime != null && ltime.hasEnded()) {
			context.setState(new MainGameScreen());
		}
		_entityManager.CheckForCollisions();
		_entityManager.UpdateEntities();
		
	}

	@Override
	public void Draw(ScreenContext context) {
		_entityManager.RenderEntities();
	}

	@Override
	public void OnEnter(ScreenContext context) {
		_spawnManager = new SpawnManager(this);
		_player = new Player(new Vector2D(25.0f, 600.0f), new Vector2D(0.3f, 0.2f), 9.0f, this);
		_player.setWeapon(WeaponsFactory.CreateWeapon(WeaponsFactory.WeaponType.Basic2, _player));
		new Enemy1Spawner(_spawnManager, 1600, 400, 600);
		new Enemy2Spawner(_spawnManager, 2200, 450, 200);
		SetupBackground();
	}

	@Override
	public void OnLeave(ScreenContext context) {
		_entityManager.ClearAll();
		_spawnManager.ClearAll();
	}

	private void SetupBackground() {
		for (int i = 0; i < 25; i++) {
			new BackgroundStar(Math.random() > 0.3 ? 2 : 1, this);
		}
	}

}
