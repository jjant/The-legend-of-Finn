package theLegendOfFinn.controller.stageManagers;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.view.MasterRenderer;

public abstract class StageManager {
	Manager manager;
	MasterRenderer masterRenderer;
	
	public StageManager(Manager manager){
		this.manager = manager;
		this.masterRenderer = manager.getMasterRenderer();
	}
	
	public abstract Stage handleStage(int key);
	
	public Ticker getTicker(){
		return manager.getTicker();
	}
}
