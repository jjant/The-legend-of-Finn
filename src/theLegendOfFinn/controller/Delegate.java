package theLegendOfFinn.controller;

import theLegendOfFinn.controller.RenderManager;

public class Delegate {
	private Manager manager;

	public Delegate(Manager manager) {
		this.manager = manager;
	}

	public void setStage(RenderManager.Stage stage) {
		manager.setStage(stage);
	}

	public RenderManager.Stage getStage() {
		return manager.getStage();
	}

	public void passKeyPressed(int key) {
		manager.keyChange(key);
	}
}
