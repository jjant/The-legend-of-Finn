package theLegendOfFinn.controller.communicators;

import java.io.Serializable;

import theLegendOfFinn.controller.Manager;

/** Provides a way for the model to communicate changes to the controller.
 * @author LCDPCJL
 *
 */
public class Notifier implements Serializable {
	
	private Manager manager;
	
	public Notifier(Manager manager){
		this.manager = manager;
	}
	public void NotifyDeath(){
		manager.gameOver();
	}
}
