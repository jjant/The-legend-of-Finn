package theLegendOfFinn.controller;

public class Delegate {
	private Manager manager;

	public Delegate(Manager manager) {
		this.manager = manager;
	}

	public void setStage(Manager.Stage stage) {
		manager.setStage(stage);
	}

	public Manager.Stage getStage() {
		return manager.getStage();
	}

	public void passKeyPressed(int key) {
		manager.keyChange(key);
	}
}
