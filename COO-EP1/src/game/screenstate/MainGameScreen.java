package game.screenstate;

import game.Vector2D;
import game.entities.backgrounds.Background;
import game.entities.constants.WeaponTypeEnum;
import game.entities.players.Player;
import game.entities.spawners.Boss1Spawner;
import game.entities.spawners.Boss2Spawner;
import game.entities.spawners.Enemy1Spawner;
import game.entities.spawners.Enemy2Spawner;
import game.entities.spawners.PowerUp1Spawner;
import game.entities.spawners.PowerUp2Spawner;
import game.entities.spawners.SpawnManager;
import game.entities.weapons.WeaponsFactory;
import game.stages.BossSpawn;
import game.stages.EnemySpawn;
import game.stages.PowerUpSpawn;
import game.stages.Stage;
import game.utils.LocalTime;

import java.util.ArrayList;

public class MainGameScreen extends ScreenState {

	private Player player;
	private SpawnManager spawnManager;
	private boolean bossIsDead;
	private LocalTime localTime = null;
	private int playerHealthPoints = 0;
	private int stageNumber = 0;
	private int numberOfEnemies = 0;
	private int deadEnemies = 0;
	private ArrayList<Stage> stages;
	
	// Contrutor

	public MainGameScreen(int playerHealthPoints, ArrayList<Stage> stages,
			int stageNumber) {
		super();
		this.playerHealthPoints = playerHealthPoints;
		this.stages = stages;
		this.stageNumber = stageNumber;
	}

	@Override
	public void draw(ScreenContext context) {
		entityManager.renderEntities();
	}

	public void enemyDied() {
		deadEnemies++;
	}

	public SpawnManager getSpawnManager() {
		return spawnManager;
	}

	@Override
	public void onEnter(ScreenContext context) {
		spawnManager = new SpawnManager(this);
		player = new Player(new Vector2D(25.0f, 600.0f), new Vector2D(0.3f,
				0.2f), 9.0f, this, this);
		player.setWeapon(WeaponsFactory.createWeapon(
				WeaponTypeEnum.PLAYER_DEFAULT_SHOT, player));

		for (EnemySpawn enemy : stages.get(stageNumber).getEnemiesSpawn()) {
			if (enemy.getType() == 1) {
				new Enemy1Spawner(spawnManager, enemy.getWhen(), enemy.getX(),
						enemy.getY());
				numberOfEnemies++;
			} else {
				new Enemy2Spawner(spawnManager, enemy.getWhen(), enemy.getX(),
						enemy.getY());
				numberOfEnemies += 10;
			}
		}

		if (stages.get(stageNumber).getBossSpawn() != null) {

			BossSpawn boss = stages.get(stageNumber).getBossSpawn();

			if (boss.getType() == 1)
				new Boss1Spawner(spawnManager, boss.getWhen(), boss.getX(),
						boss.getY(), boss.getHealthPoints());
			else
				new Boss2Spawner(spawnManager, boss.getWhen(), boss.getX(),
						boss.getY(), boss.getHealthPoints());

			numberOfEnemies++;
		}

		for (PowerUpSpawn powerup : stages.get(stageNumber).getPowerUpsSpawn()) {

			if (powerup.getType() == 1)
				new PowerUp1Spawner(spawnManager, powerup.getWhen(),
						powerup.getX(), powerup.getY());
			else
				new PowerUp2Spawner(spawnManager, powerup.getWhen(),
						powerup.getX(), powerup.getY());

		}
		setupBackground();
	}

	@Override
	public void onLeave(ScreenContext context) {
		entityManager.clearAll();
		spawnManager.clearAll();
	}

	public void setBossIsDead(boolean bossIsDead) {
		this.bossIsDead = bossIsDead;
	}

	private void setupBackground() {
		for (int i = 0; i < 25; i++) {
			new Background(Math.random() > 0.3 ? 2 : 1, this);
		}
	}

	@Override
	public void update(ScreenContext context) {
		spawnManager.updateSpawners();
		if (entityManager.isPlayerDead() && localTime == null) {
			localTime = new LocalTime(1200);
		}
		if (localTime != null && localTime.hasEnded()) {
			context.setState(new MainGameScreen(playerHealthPoints, stages,
					stageNumber));
		}

		if (bossIsDead) {
			if (stages.size() > ++stageNumber) {
				context.setState(new MainGameScreen(playerHealthPoints, stages,
						stageNumber));
			} else {
				System.exit(0);
			}
		}

		entityManager.checkForCollisions();
		entityManager.updateEntities();

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