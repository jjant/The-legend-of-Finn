package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.FileManager;
import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.exceptions.TickerMissingException;
import theLegendOfFinn.view.menu.MenuRenderer;
import theLegendOfFinn.view.menu.StartingMenuRenderer;

public class StageMenuManager extends StageManager {

	public StageMenuManager(Manager manager) {
		super(manager);
	}

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
				} catch (ClassNotFoundException | TickerMissingException e) {
					// Tirar algo porq no encontro el archivo.
				}
			else if (menu.getOption().equals(StartingMenuRenderer.NEW))
				stage = Stage.MAPSELECTION;
			break;
		default:
			break;
		}
		return stage;
	}

}
