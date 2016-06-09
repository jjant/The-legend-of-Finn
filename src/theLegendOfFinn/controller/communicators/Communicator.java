package theLegendOfFinn.controller.communicators;

import theLegendOfFinn.controller.Manager;

/**Provides a way for the controller classes 
 * to send information to the manager.
 * 
 */
public class Communicator {
	private Manager manager;

	public Communicator(Manager manager) {
		this.manager = manager;
	}

	public void firstGameRequest() {
		manager.initialize(false);
	}
	public void newGameRequest() {
		manager.initialize(true);
	}
}
