package theLegendOfFinn.controller;

import java.util.Timer;
import java.util.TimerTask;

import theLegendOfFinn.model.Ticker;

/**
 * Manages the model's updates.
 * @author LCDPCJL
 *
 */
public class ModelManager implements Runnable {
	private Ticker ticker;
	private Thread thread;

	public ModelManager(Manager manager) {
		ticker = manager.getTicker();
	}

	public void initialize(){
		thread = new Thread(this, "Model manager thread");
		thread.start();
	}
	/**
	 * Game loop that updates the model.
	 * Runs once every 5ms.
	 */
	public void run() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				ticker.tick();
				if (ticker.roundFinished()) 
					ticker.nextRound();
			}
		};
		timer.schedule(task, 0, 5);
	}

}
