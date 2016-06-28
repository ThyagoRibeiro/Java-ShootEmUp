package game.util;

public class LocalTime {

	protected double _start, _end, _current;

	public LocalTime(double start, double end) {
		this._start = start;
		this._start = end;
	}

	public LocalTime(double durationFromNow) {
		Start(durationFromNow * (1.0 / Time.getInstance().getTimeScale()));
	}

	public LocalTime() {
		_end = _start = _current = -1.0;
	}

	public void Start(double durationFromNow) {
		_start = Time.getInstance().CurrentTime();
		_end = _start + (durationFromNow * (1.0 / Time.getInstance().getTimeScale()));
	}

	public boolean hasEnded() {
		return Time.getInstance().CurrentTime() >= _end;
	}

	public double getStart() {
		return this._start;
	}

	public double getEnd() {
		return this._end;
	}
}