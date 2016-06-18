package theLegendOfFinn.controller.stageManagers;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.view.MasterRenderer;

/**
 * Parent of every stage manager.
 */
public abstract class StageManager {
	Manager manager;
	MasterRenderer masterRenderer;
	
	public StageManager(Manager manager){
		this.manager = manager;
		this.masterRenderer = manager.getMasterRenderer();
	}
	
	/**
	 * Handles a given key for the current stage
	 * @param key key to manage
	 * @return the stage to change
	 */
	public abstract Stage handleStage(int key);
	
	/**
	 * Gets the ticker
	 * @return ticker
	 */
	public Ticker getTicker(){
		return manager.getTicker();
	}
}
