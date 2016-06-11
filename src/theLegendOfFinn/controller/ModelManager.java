package theLegendOfFinn.controller;

import theLegendOfFinn.model.Ticker;

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
	// Buscar timer clase (para sacar el sleep)
	public void run() {
		while (true) {
			ticker.tick();
			if (ticker.roundFinished()) {
				ticker.nextRound();
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
	}

}
