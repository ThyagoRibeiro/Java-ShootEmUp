package game;

import java.util.ArrayList;

public class Stage {

	protected int stageNumber;

	protected BossSpawn bossSpawn;

	protected ArrayList<EnemySpawn> enemiesSpawn;
	protected ArrayList<PowerUpSpawn> powerUpsSpawn;

	public Stage(int stageNumber) {
		this.stageNumber = stageNumber;
		enemiesSpawn = new ArrayList<>();
		powerUpsSpawn = new ArrayList<>();
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

}

class EnemySpawn {

	private int type;
	private int when;
	private int x;
	private int y;

	public EnemySpawn(int type, int when, int x, int y) {

		this.type = type;
		this.when = when;
		this.x = x;
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public int getWhen() {
		return when;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}

class BossSpawn {

	private int type;
	private int when;
	private int x;
	private int y;
	private int healthPoints;

	public BossSpawn(int type, int when, int x, int y, int healthPoints) {

		this.type = type;
		this.when = when;
		this.x = x;
		this.y = y;
		this.healthPoints = healthPoints;
	}

	public int getType() {
		return type;
	}

	public int getWhen() {
		return when;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHealthPoints() {
		return healthPoints;
	}
}

class PowerUpSpawn {

	private int type;
	private int when;
	private int x;
	private int y;

	public PowerUpSpawn(int type, int when, int x, int y) {

		this.type = type;
		this.when = when;
		this.x = x;
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public int getWhen() {
		return when;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}