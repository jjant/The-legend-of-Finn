package theLegendOfFinn.controller;
//probando...
public class Notifier {
	
	private Manager manager;
	
	public Notifier(Manager manager){
		this.manager = manager;
	}
	public void NotifyDeath(){
		manager.gameOver();
	}
}
