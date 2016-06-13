package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.exceptions.TickerMissingException;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.Ticker.Arena;
import theLegendOfFinn.view.menu.MapSelectionRenderer;

public class StageMapSelectionManager extends StageManager{

	public StageMapSelectionManager(Manager manager) {
		super(manager);
	}

	public Stage handleStage(int key) {
		Stage stage = Stage.MAPSELECTION;
		MapSelectionRenderer menuMapSelection = masterRenderer.getMapSelectionRenderer();
		
		switch (key) {
		case KeyEvent.VK_UP:
			menuMapSelection.selectOption(-2);
			break;
		case KeyEvent.VK_RIGHT:
			menuMapSelection.nextOption();
			break;
		case KeyEvent.VK_DOWN:
			menuMapSelection.selectOption(2);
			break;
		case KeyEvent.VK_LEFT:
			menuMapSelection.previousOption();
			break;
		case KeyEvent.VK_ESCAPE:
			stage = Stage.MODE;
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			manager.loadTicker(new Ticker(manager.getNotifier()));
			Ticker ticker = manager.getTicker();
			if (menuMapSelection.getOption().equals(MapSelectionRenderer.GRASS))
				ticker.setArena(Arena.GRASS);
			else if(menuMapSelection.getOption().equals(MapSelectionRenderer.ICE))
				ticker.setArena(Arena.ICE);
			else if(menuMapSelection.getOption().equals(MapSelectionRenderer.MOUNTAIN))
				ticker.setArena(Arena.MOUNTAIN);
			else if(menuMapSelection.getOption().equals(MapSelectionRenderer.LAVA))
				ticker.setArena(Arena.LAVA);
			try {
				manager.initialize();
			} catch (TickerMissingException e) {
				e.printStackTrace();
			}
			stage = Stage.MAP;
			break;
		}
		return stage;
	}

}
