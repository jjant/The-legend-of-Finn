package theLegendOfFinn.controller;


import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.MenuRenderer;
import theLegendOfFinn.view.PauseRenderer;

public class EventManager {

	MasterRenderer masterRenderer;
	Ticker ticker;

	public EventManager(MasterRenderer masterRenderer, Ticker ticker) {
		this.masterRenderer = masterRenderer;
		this.ticker = ticker;
	}

	public Stage handleEvent(int key, Stage stage) {
		Stage newStage;
		switch (stage) {
		case MENU:
			newStage = handleMenu(key);
			break;
		case MAP:
			newStage = handleMap(key);
			break;
		case PAUSE:
			newStage = handlePause(key);
			break;
		default:
			throw new RuntimeException("Illegal value for stage.");
		}

		return newStage;
	}

	public Stage handleMenu(int key) {
		Stage stage = Stage.MENU;
		MenuRenderer menu = masterRenderer.getMenuRenderer();
		switch (key) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			menu.changeOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menu.getOption().equals(MenuRenderer.Option.NUEVO))
				stage = Stage.MAP;
			break;
		default:
			break;
		}
		return stage;
	}

	public Stage handleMap(int key) {
		Stage stage = Stage.MAP;
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

	public Stage handlePause(int key) {
		Stage stage = Stage.PAUSE;
		PauseRenderer menuPause = masterRenderer.getPauseRenderer();
		switch (key) {
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_UP:
			menuPause.changeOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menuPause.getOption().equals(PauseRenderer.Option.RESUME))
				stage = Stage.MAP;
			break;
		}
		return stage;
	}
}