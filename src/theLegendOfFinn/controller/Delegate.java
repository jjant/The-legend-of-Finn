package theLegendOfFinn.controller;

import theLegendOfFinn.controller.RenderManager.Stage;;

public class Delegate {
	private Manager manager;

	public Delegate(Manager manager) {
		this.manager = manager;
	}

	public void setStage(Stage stage) {
		manager.setStage(stage);
	}

	public Stage getStage() {
		return manager.getStage();
	}

	public void passKeyPressed(int key) {
		manager.keyChange(key);
	}
}
