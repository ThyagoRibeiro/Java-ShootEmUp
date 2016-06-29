package game.screenstate;

import java.util.ArrayList;

import game.entities.backgroundstar.BackgroundStar;
import game.entities.player.Player;
import game.entities.spawner.Boss1Spawner;
import game.entities.spawner.Enemy1Spawner;
import game.entities.spawner.Enemy2Spawner;
import game.entities.spawner.SpawnManager;
import game.entities.weapons.WeaponsFactory;
import game.util.LocalTime;
import geometry.Vector2D;
import stage.BossSpawn;
import stage.EnemySpawn;
import stage.PowerUpSpawn;
import stage.Stage;

public class MainGameScreen extends ScreenState {

	Player _player;
	private SpawnManager _spawnManager;
	private LocalTime ltime = null;
	private int playerHealthPoints, numberOfEnemies, stageNumber;
	private ArrayList<Stage> stages;
	private boolean bossIsDead;

	public void setBossIsDead(boolean bossIsDead) {
		this.bossIsDead = bossIsDead;
	}

	public MainGameScreen(int playerHealthPoints, ArrayList<Stage> stages, int stageNumber) {
		super();
		this.playerHealthPoints = playerHealthPoints;
		this.stages = stages;
		this.stageNumber = stageNumber;
	}

	@Override
	public void Draw(ScreenContext context) {

		_entityManager.RenderEntities();
	}

	@Override
	public void OnEnter(ScreenContext context) {

		_spawnManager = new SpawnManager(this);
		_player = new Player(new Vector2D(25.0f, 600.0f), new Vector2D(0.3f, 0.2f), 9.0f, this, playerHealthPoints);
		_player.setWeapon(WeaponsFactory.CreateWeapon(WeaponsFactory.WeaponType.Basic2, _player));

		for (EnemySpawn enemy : stages.get(stageNumber).getEnemiesSpawn()) {

			if (enemy.getType() == 1)
				new Enemy1Spawner(_spawnManager, enemy.getWhen(), enemy.getX(), enemy.getY());
			else
				new Enemy2Spawner(_spawnManager, enemy.getWhen(), enemy.getX(), enemy.getY());

			numberOfEnemies++;
		}

		if (stages.get(stageNumber).getBossSpawn() != null) {

			BossSpawn boss = stages.get(stageNumber).getBossSpawn();

			if (boss.getType() == 1)
				new Boss1Spawner(_spawnManager, boss.getWhen(), boss.getX(), boss.getY(), boss.getHealthPoints());
			// else
			// new Boss2Spawner(_spawnManager, boss.getWhen(), boss.getX(),
			// boss.getY());

			numberOfEnemies++;
		}

		for (PowerUpSpawn powerup : stages.get(stageNumber).getPowerUpsSpawn()) {

			// if (powerup.getType() == 1)
			// new Enemy2Spawner(_spawnManager, powerup.getWhen(),
			// powerup.getX(), powerup.getY());
			// else
			// new Enemy2Spawner(_spawnManager, powerup.getWhen(),
			// powerup.getX(), powerup.getY());

		}

		System.out.println("numero de inimigos: " + numberOfEnemies);
		SetupBackground();
	}

	public SpawnManager getSpawnManager() {

		return _spawnManager;

	}

	public void enemyDied() {
		numberOfEnemies--;
		System.out.println(numberOfEnemies);
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

	@Override
	public void Update(ScreenContext context) {
		_spawnManager.Update();
		if (_entityManager.isPlayerDead() && ltime == null) {
			ltime = new LocalTime(1200);
		}

		if (ltime != null && ltime.hasEnded()) {
			context.setState(new MainGameScreen(playerHealthPoints, stages, stageNumber));
		}

		if (bossIsDead) {
			if (stages.size() > ++stageNumber) {
				System.out.println("Proxima fase");
				context.setState(new MainGameScreen(playerHealthPoints, stages, stageNumber));
			} else {
				System.out.println("Fim de jogo");
				System.exit(0);
			}
		}

		_entityManager.CheckForCollisions();
		_entityManager.UpdateEntities();

	}

}