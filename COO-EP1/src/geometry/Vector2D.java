package geometry;

public class Vector2D {
	protected float coordX;
	protected float coordY;

	public Vector2D() {
		coordX = coordY = 0.0f;
	}

	public Vector2D(float x, float y) {
		this.coordX = x;
		this.coordY = y;
	}

	public Vector2D add(float addx, float addy) {
		return new Vector2D(this.coordX + addx, this.coordY + addy);
	}

	public Vector2D add(Vector2D toadd) {
		return new Vector2D(this.coordX + toadd.coordX, this.coordY
				+ toadd.coordY);
	}

	public double distance(Vector2D other) {
		double dx = other.getX() - this.getX();
		double dy = other.getY() - this.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);

		return dist;
	}

	public float getX() {
		return coordX;
	}

	public float getY() {
		return coordY;
	}

	public Vector2D multiply(float factor) {
		return new Vector2D(this.coordX * factor, this.coordY * factor);
	}

	public Vector2D multiply(float factorx, float factory) {
		return new Vector2D(this.coordX * factorx, this.coordY * factory);
	}

	public void setX(float _coordX) {
		this.coordX = _coordX;
	}

	public void setXY(float x, float y) {
		this.coordX = x;
		this.coordY = y;
	}

	public void setY(float _coordY) {
		this.coordY = _coordY;
	}

	public void setYToNegative() {
		if (this.coordY > 0)
			this.coordY = -this.coordY;
	}

	public void setYToPositive() {
		if (this.coordY < 0)
			this.coordY = -this.coordY;
	}

	@Override
	public String toString() {
		return "(" + this.coordX + ", " + this.coordY + ")";
	}

}
