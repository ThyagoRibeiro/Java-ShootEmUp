package game.screenstate;

import java.util.ArrayList;

import game.entities.backgroundstar.BackgroundStar;
import game.entities.player.Player;
import game.entities.spawner.Boss1Spawner;
import game.entities.spawner.Boss2Spawner;
import game.entities.spawner.Enemy1Spawner;
import game.entities.spawner.Enemy2Spawner;
import game.entities.spawner.PowerUp1Spawner;
import game.entities.spawner.PowerUp2Spawner;
import game.entities.spawner.SpawnManager;
import game.entities.weapons.WeaponsFactory;
import game.util.LocalTime;
import geometry.Vector2D;
import stage.BossSpawn;
import stage.EnemySpawn;
import stage.PowerUpSpawn;
import stage.Stage;

public class MainGameScreen extends ScreenState {

	private Player _player;
	private SpawnManager _spawnManager;
	private boolean bossIsDead;
	private LocalTime ltime = null;
	private int playerHealthPoints, stageNumber, numberOfEnemies, deadEnemies = 0;
	private ArrayList<Stage> stages;

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

	public void enemyDied() {
		deadEnemies++;
	}

	public SpawnManager getSpawnManager() {

		return _spawnManager;

	}

	@Override
	public void OnEnter(ScreenContext context) {

		_spawnManager = new SpawnManager(this);
		_player = new Player(new Vector2D(25.0f, 600.0f), new Vector2D(0.3f, 0.2f), 9.0f, this, this);
		_player.setWeapon(WeaponsFactory.CreateWeapon(WeaponsFactory.WeaponType.PlayerDeafultShot, _player));

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
			else
				new Boss2Spawner(_spawnManager, boss.getWhen(), boss.getX(), boss.getY(), boss.getHealthPoints());

			numberOfEnemies++;
		}

		for (PowerUpSpawn powerup : stages.get(stageNumber).getPowerUpsSpawn()) {

			if (powerup.getType() == 1)
				new PowerUp1Spawner(_spawnManager, powerup.getWhen(), powerup.getX(), powerup.getY());
			else
				new PowerUp2Spawner(_spawnManager, powerup.getWhen(), powerup.getX(), powerup.getY());

		}

		System.out.println("numero de inimigos: " + numberOfEnemies);
		SetupBackground();
	}

	@Override
	public void OnLeave(ScreenContext context) {
		_entityManager.ClearAll();
		_spawnManager.ClearAll();
	}

	public void setBossIsDead(boolean bossIsDead) {
		this.bossIsDead = bossIsDead;
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

	public int getPlayerHealthPoints() {
		return playerHealthPoints;
	}

	public int getStageNumber() {
		return stageNumber;
	}

	public int getNumberOfEnemies() {
		return numberOfEnemies;
	}

	public int getDeadEnemies() {
		return deadEnemies;
	}

	

}