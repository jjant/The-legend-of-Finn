package theLegendOfFinn.controller;

public class TimeManager {

	private final double ns = 1000000000.0 / 60.0;
	private long lastTime;
	private long lastSecond;
	private double delta;

	public TimeManager() {
		lastTime = System.nanoTime();
		lastSecond = System.currentTimeMillis();
		delta = 0;
	}

	public boolean update() {
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		if (delta >= 1) {
			delta--;
			return true;
		}
		return false;
	}

	public double getDelta() {
		return delta;
	}

	public boolean secondPassed() {
		long now = System.currentTimeMillis();
		if ((now - lastSecond) >= 1000) {
			lastSecond = now;
			return true;
		}
		return false;
	}

}
