package theLegendOfFinn.controller.communicators;

import theLegendOfFinn.controller.Manager;

/**
 * Provides a way for the model to communicate changes to the controller.
 *
 */
public class Notifier {

	private Manager manager;

	public Notifier(Manager manager) {
		this.manager = manager;
	}

	public void NotifyDeath() {
		manager.gameOver();
	}

	public void notifyWin() {
		manager.win();
	}
}
