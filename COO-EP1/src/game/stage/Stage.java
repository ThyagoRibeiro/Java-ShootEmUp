package game.stage;

import java.util.ArrayList;

public class Stage {

	protected BossSpawn bossSpawn;
	protected ArrayList<EnemySpawn> enemiesSpawn = new ArrayList<>();
	protected ArrayList<PowerUpSpawn> powerUpsSpawn = new ArrayList<>();
	protected int stageNumber;

	// Construtor

	public Stage(int stageNumber) {
		this.stageNumber = stageNumber;
	}

	/* GETTERS E SETTERS - INICIO */

	public BossSpawn getBossSpawn() {
		return bossSpawn;
	}

	public void setBossSpawn(BossSpawn bossSpawn) {
		this.bossSpawn = bossSpawn;
	}

	public ArrayList<EnemySpawn> getEnemiesSpawn() {
		return enemiesSpawn;
	}

	public void setEnemiesSpawn(ArrayList<EnemySpawn> enemiesSpawn) {
		this.enemiesSpawn = enemiesSpawn;
	}

	public ArrayList<PowerUpSpawn> getPowerUpsSpawn() {
		return powerUpsSpawn;
	}

	public void setPowerUpsSpawn(ArrayList<PowerUpSpawn> powerUpsSpawn) {
		this.powerUpsSpawn = powerUpsSpawn;
	}

	public int getStageNumber() {
		return stageNumber;
	}

	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodo que adiciona chefe na fase.

	public void addBoss(int type, int when, int x, int y, int healthPoints) {
		bossSpawn = new BossSpawn(type, when, x, y, healthPoints);
	}

	// Metodo que adiciona inimigo na fase.

	public void addEnemy(int type, int when, int x, int y) {
		enemiesSpawn.add(new EnemySpawn(type, when, x, y));
	}

	// Metodo que adiciona power up na fase.

	public void addPowerup(int type, int when, int x, int y) {
		powerUpsSpawn.add(new PowerUpSpawn(type, when, x, y));
	}

	// Metodo que remove o incializador do chefe da fase.

	public void removeBossSpawn() {
		bossSpawn = null;
	}

}