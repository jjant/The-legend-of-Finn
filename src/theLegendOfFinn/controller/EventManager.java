package theLegendOfFinn.controller;

import java.util.HashMap;
import java.util.Map;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.stageManagers.StageManager;
import theLegendOfFinn.controller.stageManagers.StageGameOverManager;
import theLegendOfFinn.controller.stageManagers.StageMapManager;
import theLegendOfFinn.controller.stageManagers.StageMenuManager;
import theLegendOfFinn.controller.stageManagers.StageMapSelectionManager;
import theLegendOfFinn.controller.stageManagers.StagePauseManager;

public class EventManager {
	private Map<Stage, StageManager> stageManagerSelector;

	private StageManager menuManager;
	private StageManager gameOverManager;
	private StageManager mapManager;
	private StageManager pauseManager;
	private StageManager mapSelectionManager;

	public EventManager(Manager manager) {
		menuManager = new StageMenuManager(manager);
		gameOverManager = new StageGameOverManager(manager);
		mapManager = new StageMapManager(manager);
		pauseManager = new StagePauseManager(manager);
		mapSelectionManager = new StageMapSelectionManager(manager);

		stageManagerSelector = new HashMap<>();
		stageManagerSelector.put(Stage.MENU, menuManager);
		stageManagerSelector.put(Stage.MAP, mapManager);
		stageManagerSelector.put(Stage.PAUSE, pauseManager);
		stageManagerSelector.put(Stage.GAMEOVER, gameOverManager);
		stageManagerSelector.put(Stage.MAPSELECTION, mapSelectionManager);
	}

	public Stage handleEvent(int key, Stage stage) {
		Stage newStage = stageManagerSelector.get(stage).handleStage(key);
		return newStage;
	}

	public Stage handlePlayerDeath() {
		return Stage.GAMEOVER;
	}

}