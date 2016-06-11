package theLegendOfFinn.controller;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.menu.GameOverRenderer;
import theLegendOfFinn.view.menu.MapSelectionRenderer;
import theLegendOfFinn.view.menu.MenuRenderer;
import theLegendOfFinn.view.menu.PauseRenderer;
import theLegendOfFinn.view.menu.StartingMenuRenderer;

public class EventManager {
	private Manager manager;

	private MasterRenderer masterRenderer;
	private Ticker ticker;

	public EventManager(Manager manager) {
		this.manager = manager;
		this.masterRenderer = manager.getMasterRenderer();
	}

	public Stage handleEvent(int key, Stage stage) {
		Stage newStage = null;
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
		case GAMEOVER:
			newStage = handleGameOver(key);
		case MAPSELECTION:
			newStage = handleMapSelection(key);
		}

		return newStage;
	}

	public Stage handleMenu(int key) {
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
				} catch (ClassNotFoundException e) {
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
				fileManager.saveGame(ticker);
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

	public Stage handleGameOver(int key) {
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

	// probando
	public Stage handlePlayerDeath() {
		return Stage.GAMEOVER;
	}
	
	public Stage handleMapSelection(int key) {
		Stage stage = Stage.MAPSELECTION;
		MapSelectionRenderer menuMapSelection = masterRenderer.getMapSelectionRenderer();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			menuMapSelection.nextOption();
			break;
		case KeyEvent.VK_LEFT:
			menuMapSelection.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menuMapSelection.getOption() == MapSelectionRenderer.GRASS)
				manager.loadTicker(new Ticker(manager.getNotifier()));
				manager.initialize();
				ticker = manager.getTicker();
				stage = Stage.MAP;
			break;
		}
		return stage;
	}
}