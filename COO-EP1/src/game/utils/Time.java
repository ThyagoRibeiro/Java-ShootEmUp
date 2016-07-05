package game.utils;

public class Time {

	private static Time INSTANCE = new Time();

	public static Time getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Time();
		}
		return INSTANCE;
	}

	private long currentTimeMilis;
	private long delta;
	private double timeScale;

	// Construtor

	protected Time() {
		currentTimeMilis = System.currentTimeMillis();
		timeScale = 1.0;
	}

	/* GETTERS E SETTERS - INICIO */

	public long currentTime() {
		return System.currentTimeMillis();
	}

	public long getCurrentTimeMilis() {
		return currentTimeMilis;
	}

	public void setCurrentTimeMilis(long currentTimeMilis) {
		this.currentTimeMilis = currentTimeMilis;
	}

	public long getDelta() {
		return delta;
	}

	public void setDelta(long delta) {
		this.delta = delta;
	}

	public double getTimeScale() {
		return timeScale;
	}

	public void setTimeScale(double timeScale) {
		this.timeScale = timeScale;
	}

	/* GETTERS E SETTERS - FIM */
	
	public long deltaTime() {
		return (long) (delta * timeScale);
	}

	public void updateDelta() {
		delta = System.currentTimeMillis() - currentTimeMilis;
		currentTimeMilis = System.currentTimeMillis();
	}

}
