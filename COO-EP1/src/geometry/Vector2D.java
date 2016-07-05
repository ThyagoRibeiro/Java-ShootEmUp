package geometry;

public class Vector2D {

	protected float coordX;
	protected float coordY;

	// Construtores

	public Vector2D() {
		coordX = coordY = 0.0f;
	}

	public Vector2D(float x, float y) {
		this.coordX = x;
		this.coordY = y;
	}

	/* GETTERS E SETTERS - INICIO */

	public float getCoordX() {
		return coordX;
	}

	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}

	public float getCoordY() {
		return coordY;
	}

	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}

	public void setCoordXY(float coordX, float coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public void setCoordYToNegative() {
		if (this.coordY > 0)
			this.coordY = -this.coordY;
	}

	public void setCoordYToPositive() {
		if (this.coordY < 0)
			this.coordY = -this.coordY;
	}

	/* GETTERS E SETTERS - FIM */

	// Metodos que adicionam as coordenadas

	public Vector2D add(float x, float y) {
		return new Vector2D(this.coordX + x, this.coordY + y);
	}

	public Vector2D add(Vector2D coord) {
		return new Vector2D(this.coordX + coord.coordX, this.coordY
				+ coord.coordY);
	}

	// Metodo que calcula a distancia entre a instancia atual da coordenada e
	// outro recebida por parametro

	public double distance(Vector2D coord) {
		double dx = coord.getCoordX() - this.getCoordX();
		double dy = coord.getCoordY() - this.getCoordY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		return dist;
	}

	// Metodos que multiplica as coordenadas por um fator ou fatores diferentes.

	public Vector2D multiply(float factor) {
		return new Vector2D(this.coordX * factor, this.coordY * factor);
	}

	public Vector2D multiply(float factorX, float factorY) {
		return new Vector2D(this.coordX * factorX, this.coordY * factorY);
	}
	
}
