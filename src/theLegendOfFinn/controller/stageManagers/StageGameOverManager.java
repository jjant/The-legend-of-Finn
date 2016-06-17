package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.view.menu.GameOverRenderer;

/**
 * Manages the game over view
 */
public class StageGameOverManager extends StageManager{

	public StageGameOverManager(Manager manager) {
		super(manager);
	}

	/**
	 * Handles a given key for the current stage
	 */
	public Stage handleStage(int key) {
		Stage stage = Stage.GAMEOVER;
		GameOverRenderer menuGameOver = masterRenderer.getGameOverRenderer();
		
		switch (key) {
		case KeyEvent.VK_DOWN:
			menuGameOver.nextOption();
			break;
		case KeyEvent.VK_UP:
			menuGameOver.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menuGameOver.getOption() == GameOverRenderer.MAIN_MENU)
				stage = Stage.MENU;
			else if (menuGameOver.getOption() == GameOverRenderer.EXIT) // revisar
																		// luego
				System.exit(0);
			break;
		}
		return stage;
	}

}
