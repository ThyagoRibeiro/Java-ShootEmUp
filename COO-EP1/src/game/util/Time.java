package game.util;

public class Time {

	private static Time _INSTANCE = new Time();

	public static Time getInstance() {
		if (_INSTANCE == null) {
			_INSTANCE = new Time();
		}

		return _INSTANCE;
	}

	private long _currentTimeMilis;
	private long _delta;
	private double _timeScale;

	protected Time() {
		_currentTimeMilis = System.currentTimeMillis();
		_timeScale = 1.0;
	}

	public void UpdateDelta() {
		_delta = System.currentTimeMillis() - _currentTimeMilis;
		_currentTimeMilis = System.currentTimeMillis();
	}

	public void setTimeScale(double timeScale) {
		this._timeScale = timeScale;
	}

	public double getTimeScale() {
		return this._timeScale;
	}

	public long DeltaTime() {
		return (long) (_delta * _timeScale);
	}

	public long CurrentTime() {
		return System.currentTimeMillis();
	}
}
