package theLegendOfFinn.controller;

import theLegendOfFinn.view.MasterRenderer;

/**
 * Manages the game's view renderization.
 */
public class RenderManager implements Runnable {
	// Possible game stages
	public enum Stage {
		MENU, MAP, PAUSE, GAMEOVER, MAPSELECTION, MODE;
	}
	
	private Thread thread;
	
	private MasterRenderer masterRenderer;
	
	private Stage stage;

	// Default values for renderization
	private final double fps = 60.0;
	private final double ns = 100000000.0 / fps;
	private long lastTime;
	private double delta;
	

	public RenderManager(Manager manager) {
		this.masterRenderer = manager.getMasterRenderer();
	}

	/**
	 * Initializes the rendering of the game.
	 */
	public void initialize() {
		lastTime = System.nanoTime();
		delta = 0;
		
		stage = Stage.MENU;
		thread = new Thread(this, "Render manager thread");
		thread.start();
	}
	
	/**
	 * Loop that renders the game.
	 */
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
	
	/** 
	 * Returns the current stage of the game.
	 * @return the current stage.
	 */
	public Stage getStage(){
		return stage;
	}
	
	/** 
	 * Sets the current stage of the game.
	 * @param stage the stage to be set.
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
}
