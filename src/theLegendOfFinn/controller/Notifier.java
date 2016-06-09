package theLegendOfFinn.controller;

import java.io.Serializable;

//probando...
public class Notifier implements Serializable {
	
	private Manager manager;
	
	public Notifier(Manager manager){
		this.manager = manager;
	}
	public void NotifyDeath(){
		manager.gameOver();
	}
}
