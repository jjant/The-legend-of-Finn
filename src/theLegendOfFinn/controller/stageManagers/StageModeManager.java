package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Round;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.view.menu.ModeRenderer;

public class StageModeManager extends StageManager{

	public StageModeManager(Manager manager) {
		super(manager);
	}
	
	public Stage handleStage(int key) {
		Stage stage = Stage.MODE;
		ModeRenderer menuMode = masterRenderer.getModeRenderer();
		
		switch (key) {
		case KeyEvent.VK_LEFT:
			menuMode.nextOption();
			break;
		case KeyEvent.VK_RIGHT:
			menuMode.previousOption();
			break;
		case KeyEvent.VK_ESCAPE:
			stage = Stage.MENU;
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menuMode.getOption().equals(ModeRenderer.CAMPAIGN))
				manager.loadTicker(new Ticker(manager.getNotifier(), Round.RoundType.NORMAL));
			else if (menuMode.getOption().equals(ModeRenderer.SURVIVAL))
				manager.loadTicker(new Ticker(manager.getNotifier(), Round.RoundType.SURVIVAL));
			stage = Stage.MAPSELECTION;
			break;
		default:
			break;
		}
		return stage;
	}
}
