package stage;

public class BossSpawn {

	private int healthPoints;
	private int type;
	private int when;
	private int x;
	private int y;

	public BossSpawn(int type, int when, int x, int y, int healthPoints) {

		this.type = type;
		this.when = when;
		this.x = x;
		this.y = y;
		this.healthPoints = healthPoints;
	}

	public int getHealthPoints() {
		return healthPoints;
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