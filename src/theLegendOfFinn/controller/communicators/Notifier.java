package theLegendOfFinn.controller.communicators;

import theLegendOfFinn.controller.Manager;

/**
 * Provides a way for the model to communicate changes to the controller.
 */
public class Notifier {

	private Manager manager;

	public Notifier(Manager manager) {
		this.manager = manager;
	}
	
	/**
	 * Notifies death in the game
	 */
	public void NotifyDeath(){
		manager.gameOver();
	}

	/**
	 * Notifies win in the game
	 */
	public void notifyWin() {
		manager.win();
	}
}
