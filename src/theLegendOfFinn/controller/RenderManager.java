package theLegendOfFinn.controller;

import theLegendOfFinn.model.character.EnemyFactory;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.RenderFactory;

public class RenderManager implements Runnable {
	public enum Stage {
		MENU, MAP, PAUSE;
	}
	
	private Thread thread;
	
	private MasterRenderer masterRenderer;

	
	
	private Stage stage;
	
	private final double ns = 1000000000.0 / 600.0;
	private long lastTime;
	private long lastSecond;
	private double delta;
	

	public RenderManager(MasterRenderer masterRenderer) {
		this.masterRenderer = masterRenderer;
	}

	public void initialize() {

		lastTime = System.nanoTime();
		lastSecond = System.currentTimeMillis();
		delta = 0;
		
		stage = Stage.MENU;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				delta--;
				masterRenderer.render(stage);
			}			
		}
			
	}

	public boolean secondPassed() {
		long now = System.currentTimeMillis();
		if ((now - lastSecond) >= 1000) {
			lastSecond = now;
			return true;
		}
		return false;
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
}
