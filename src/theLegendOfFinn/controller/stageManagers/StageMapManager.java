package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.Character;

public class StageMapManager extends StageManager {

	public StageMapManager(Manager manager) {
		super(manager);
	}

	@Override
	public Stage handleStage(int key) {
		Stage stage = Stage.MAP;
		Ticker ticker = getTicker();
		Character player = ticker.getPlayer();

		switch (key) {
		case KeyEvent.VK_UP:
			player.tryToMove(Character.Direction.UP, ticker.getMap().getGrid());
			break;
		case KeyEvent.VK_LEFT:
			player.tryToMove(Character.Direction.LEFT, ticker.getMap().getGrid());
			break;
		case KeyEvent.VK_DOWN:
			player.tryToMove(Character.Direction.DOWN, ticker.getMap().getGrid());
			break;
		case KeyEvent.VK_RIGHT:
			player.tryToMove(Character.Direction.RIGHT, ticker.getMap().getGrid());
			break;
		case KeyEvent.VK_ESCAPE:
			stage = Stage.PAUSE;
			break;
		case KeyEvent.VK_A:
			Position posToAttack = player.getPosition().toGridIndexes(player.getDirection());
			System.out.println(posToAttack);
			System.out.println(player.getDirection());
			if (posToAttack != null) {
				int x = posToAttack.getX();
				int y = posToAttack.getY();
				player.attack(ticker.getMap().getGrid().getCharMatrix()[x][y]);
			}
			break;
		default:
			break;
		}
		return stage;
	}

}
