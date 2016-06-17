package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;
import java.util.logging.Level;

import theLegendOfFinn.controller.FileManager;
import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.exceptions.TickerMissingException;
import theLegendOfFinn.view.menu.MenuRenderer;
import theLegendOfFinn.view.menu.StartingMenuRenderer;

/**
 * Manages the stage menu selector view
 */
public class StageMenuManager extends StageManager {

	public StageMenuManager(Manager manager) {
		super(manager);
	}

	/**
	 * Handles a given key during the current stage
	 */
	@Override
	public Stage handleStage(int key) {
		FileManager fileManager = FileManager.getFileManager();

		Stage stage = Stage.MENU;
		MenuRenderer menu = masterRenderer.getMenuRenderer();

		switch (key) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			menu.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menu.getOption().equals(StartingMenuRenderer.LOAD))
				try {
					manager.loadTicker(fileManager.loadGame());
					manager.initialize();
					stage = Stage.MAP;
				} catch (ClassNotFoundException e) {
					//Will never happen
					Manager.LOGGER.log(Level.FINE, "Ticker class file missing", e);
				} catch (TickerMissingException e) {
					Manager.LOGGER.log(Level.WARNING, "Ticker object missing", e);
				}
			else if (menu.getOption().equals(StartingMenuRenderer.NEW))
				stage = Stage.MODE;
			break;
		default:
			break;
		}
		return stage;
	}

}
