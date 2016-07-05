package game.utils;

public class LocalTime {

	protected double start;
	protected double end;
	protected double current;

	// Construtores

	public LocalTime() {
		end = start = current = -1.0;
	}

	public LocalTime(double durationFromNow) {
		start(durationFromNow * (1.0 / Time.getInstance().getTimeScale()));
	}

	public LocalTime(double start, double end) {
		this.start = start;
		this.start = end;
	}

	/* GETTERS E SETTERS - INICIO */

	public double getStart() {
		return this.start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return this.end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public double getCurrent() {
		return this.current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	/* GETTERS E SETTERS - FIM */
	
	// Metodo que verifica se o tempo ja acabou.

	public boolean hasEnded() {
		return Time.getInstance().currentTime() >= end;
	}
	
	// Metodo que inicia a contagem do tempo.

	public void start(double durationFromNow) {
		start = Time.getInstance().currentTime();
		end = start
				+ (durationFromNow * (1.0 / Time.getInstance().getTimeScale()));
	}
}
