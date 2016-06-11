package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.FileManager;
import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.view.menu.PauseRenderer;

public class StagePauseManager extends StageManager {

	public StagePauseManager(Manager manager) {
		super(manager);
	}

	public Stage handleStage(int key) {
		Stage stage = Stage.PAUSE;
		PauseRenderer menuPause = masterRenderer.getPauseRenderer();
		FileManager fileManager = FileManager.getFileManager();

		switch (key) {
		case KeyEvent.VK_DOWN:
			menuPause.nextOption();
			break;
		case KeyEvent.VK_UP:
			menuPause.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menuPause.getOption().equals(PauseRenderer.RESUME))
				stage = Stage.MAP;
			else if (menuPause.getOption().equals(PauseRenderer.SAVE))
				fileManager.saveGame(getTicker());
			else if (menuPause.getOption().equals(PauseRenderer.EXIT))
				System.exit(0);
			break;
		case KeyEvent.VK_ESCAPE:
			stage = Stage.MAP;
			break;
		default:
			break;
		}
		return stage;
	}

}
