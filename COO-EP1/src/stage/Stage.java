package stage;

import java.util.ArrayList;

public class Stage {

	protected BossSpawn bossSpawn;

	protected ArrayList<EnemySpawn> enemiesSpawn = new ArrayList<>();
	protected ArrayList<PowerUpSpawn> powerUpsSpawn = new ArrayList<>();
	protected int stageNumber;

	public Stage(int stageNumber) {
		this.stageNumber = stageNumber;
	}

	public void addBoss(int type, int when, int x, int y, int healthPoints) {

		bossSpawn = new BossSpawn(type, when, x, y, healthPoints);
	}

	public void addEnemy(int type, int when, int x, int y) {

		enemiesSpawn.add(new EnemySpawn(type, when, x, y));
	}

	public void addPowerup(int type, int when, int x, int y) {

		powerUpsSpawn.add(new PowerUpSpawn(type, when, x, y));
	}

	public BossSpawn getBossSpawn() {
		return bossSpawn;
	}

	public ArrayList<EnemySpawn> getEnemiesSpawn() {
		return enemiesSpawn;
	}

	public ArrayList<PowerUpSpawn> getPowerUpsSpawn() {
		return powerUpsSpawn;
	}

	public int getStageNumber() {
		return stageNumber;
	}
	
	public void removeBossSpawn(){
		bossSpawn = null;
	}

}