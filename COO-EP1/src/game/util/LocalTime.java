package game.util;

public class LocalTime {

	protected double start;
	protected double end;
	protected double current;

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

	public double getEnd() {
		return this.end;
	}

	public double getStart() {
		return this.start;
	}

	public boolean hasEnded() {
		return Time.getInstance().currentTime() >= end;
	}

	public void start(double durationFromNow) {
		start = Time.getInstance().currentTime();
		end = start
				+ (durationFromNow * (1.0 / Time.getInstance().getTimeScale()));
	}
}
