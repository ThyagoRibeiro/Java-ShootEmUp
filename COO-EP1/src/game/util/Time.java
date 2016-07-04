package game.util;

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

	protected Time() {
		currentTimeMilis = System.currentTimeMillis();
		timeScale = 1.0;
	}

	public long currentTime() {
		return System.currentTimeMillis();
	}

	public long deltaTime() {
		return (long) (delta * timeScale);
	}

	public double getTimeScale() {
		return this.timeScale;
	}

	public void setTimeScale(double timeScale) {
		this.timeScale = timeScale;
	}

	public void updateDelta() {
		delta = System.currentTimeMillis() - currentTimeMilis;
		currentTimeMilis = System.currentTimeMillis();
	}
}
