package geometry;

public class Vector2D {
	protected float _coordX, _coordY;

	public Vector2D() {
		_coordX = _coordY = 0.0f;
	}

	public Vector2D(float x, float y) {
		this._coordX = x;
		this._coordY = y;
	}

	public Vector2D Add(float addx, float addy) {
		return new Vector2D(this._coordX + addx, this._coordY + addy);
	}

	public Vector2D Add(Vector2D toadd) {
		return new Vector2D(this._coordX + toadd._coordX, this._coordY + toadd._coordY);
	}

	public double Distance(Vector2D other) {
		double dx = other.getX() - this.getX();
		double dy = other.getY() - this.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);

		return dist;
	}

	public float getX() {
		return _coordX;
	}

	public float getY() {
		return _coordY;
	}

	public Vector2D Multiply(float factor) {
		return new Vector2D(this._coordX * factor, this._coordY * factor);
	}

	public Vector2D Multiply(float factorx, float factory) {
		return new Vector2D(this._coordX * factorx, this._coordY * factory);
	}

	public void setX(float _coordX) {
		this._coordX = _coordX;
	}

	public void setXY(float x, float y) {
		this._coordX = x;
		this._coordY = y;
	}

	public void setY(float _coordY) {
		this._coordY = _coordY;
	}

	public void setYToNegative() {
		if (this._coordY > 0)
			this._coordY = -this._coordY;
	}

	public void setYToPositive() {
		if (this._coordY < 0)
			this._coordY = -this._coordY;
	}

	@Override
	public String toString() {
		return "(" + this._coordX + ", " + this._coordY + ")";
	}

}
