package stage;

public class EnemySpawn {

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