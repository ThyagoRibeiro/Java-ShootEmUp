package game.stages;

public class BossSpawn {

	private int type;
	private int healthPoints;
	private int when;
	private int x;
	private int y;

	// Construtor

	public BossSpawn(int type, int healthPoints, int when, int x, int y) {
		this.type = type;
		this.healthPoints = healthPoints;
		this.when = when;
		this.x = x;
		this.y = y;
	}

	/* GETTERS E SETTERS - INICIO */

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWhen() {
		return when;
	}

	public void setWhen(int when) {
		this.when = when;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/* GETTERS E SETTERS - FIM */

}