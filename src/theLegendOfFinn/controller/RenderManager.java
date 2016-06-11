package theLegendOfFinn.controller;

import theLegendOfFinn.view.MasterRenderer;

public class RenderManager implements Runnable {
	public enum Stage {
		MENU, MAP, PAUSE, GAMEOVER, MAPSELECTION;
	}
	
	private Thread thread;
	
	private MasterRenderer masterRenderer;
	
	private Stage stage;

	private final double fps = 60.0;
	private final double ns = 100000000.0 / fps;
	private long lastTime;
	private static long lastSecond;
	private double delta;
	

	public RenderManager(Manager manager) {
		this.masterRenderer = manager.getMasterRenderer();
	}

	/**Initializes the rendering of the game.
	 * 
	 */
	public void initialize() {
		lastTime = System.nanoTime();
		lastSecond = System.currentTimeMillis();
		delta = 0;
		
		stage = Stage.MENU;
		thread = new Thread(this, "Render manager thread");
		thread.start();
	}
	
	/**Loop that renders the game.
	 * 
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

	// WARNING!
	// SECOND PASSED CHANGED TO STATIC
	// LASTSECOND CHANGED TO STATIC
	// 1000ms CHANGED to 15ms
	// ONLY FOR DEBUGGING GOALS
	// SOMETHING LIKE THIS SHOULD BE IMPLEMENTED
	// TAKING INTO ACCOUNT CHARACTER'S VELOCITY
	// porq tan emocionado el comment
	public static boolean secondPassed() {
		long now = System.currentTimeMillis();
		if ((now - lastSecond) >= 15) {
			lastSecond = now;
			return true;
		}
		return false;
	}
	
	/** Returns the current stage of the game.
	 * 
	 * @return the current stage.
	 */
	public Stage getStage(){
		return stage;
	}
	
	/** Sets the current stage of the game.
	 * 
	 * @param stage the stage to be set.
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
}
