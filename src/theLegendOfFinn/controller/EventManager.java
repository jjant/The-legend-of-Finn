package theLegendOfFinn.controller;

import java.util.HashMap;
import java.util.Map;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.stageManagers.StageManager;
import theLegendOfFinn.controller.stageManagers.StageGameOverManager;
import theLegendOfFinn.controller.stageManagers.StageMapManager;
import theLegendOfFinn.controller.stageManagers.StageMenuManager;
import theLegendOfFinn.controller.stageManagers.StageModeManager;
import theLegendOfFinn.controller.stageManagers.StageMapSelectionManager;
import theLegendOfFinn.controller.stageManagers.StagePauseManager;

/**
 * Manages every event occurred during the game.
 *
 */
public class EventManager {
	private Map<Stage, StageManager> stageManagerSelector;

	// Stage managers
	private StageManager menuManager;
	private StageManager gameOverManager;
	private StageManager mapManager;
	private StageManager pauseManager;
	private StageManager mapSelectionManager;
	private StageManager modeManager;

	public EventManager(Manager manager) {
		menuManager = new StageMenuManager(manager);
		gameOverManager = new StageGameOverManager(manager);
		mapManager = new StageMapManager(manager);
		pauseManager = new StagePauseManager(manager);
		mapSelectionManager = new StageMapSelectionManager(manager);
		modeManager = new StageModeManager(manager);

		stageManagerSelector = new HashMap<>();
		stageManagerSelector.put(Stage.MENU, menuManager);
		stageManagerSelector.put(Stage.MAP, mapManager);
		stageManagerSelector.put(Stage.PAUSE, pauseManager);
		stageManagerSelector.put(Stage.GAMEOVER, gameOverManager);
		stageManagerSelector.put(Stage.MAPSELECTION, mapSelectionManager);
		stageManagerSelector.put(Stage.MODE, modeManager);
	}

	/**
	 * Handles an event
	 * @param key key to handle
	 * @param stage current stage
	 * @return new stage
	 */
	public Stage handleEvent(int key, Stage stage) {
		Stage newStage = stageManagerSelector.get(stage).handleStage(key);
		return newStage;
	}

	/**
	 * Gets game over stage
	 * @return game over stage
	 */
	public Stage handlePlayerDeath() {
		return Stage.GAMEOVER;
	}

}