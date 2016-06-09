package theLegendOfFinn.controller;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.view.GameOverRenderer;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.MenuRenderer;
import theLegendOfFinn.view.PauseRenderer;
import theLegendOfFinn.view.StartingMenuRenderer;

public class EventManager {

	MasterRenderer masterRenderer;
	Ticker ticker;

	public EventManager(MasterRenderer masterRenderer, Ticker ticker) {
		this.masterRenderer = masterRenderer;
		this.ticker = ticker;
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
		}

		return newStage;
	}

	public Stage handleMenu(int key) {
		Stage stage = Stage.MENU;
		MenuRenderer menu = masterRenderer.getMenuRenderer();
		switch (key) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			menu.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menu.getOption().equals(StartingMenuRenderer.NEW))
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
			menuPause.nextOption();
		case KeyEvent.VK_UP:
			menuPause.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menuPause.getOption().equals(PauseRenderer.RESUME))
				stage = Stage.MAP;
			break;
		case KeyEvent.VK_ESCAPE:
			stage = Stage.MAP;
			break;
		default:
			break;
		}
		return stage;
	}
	
	public Stage handleGameOver(int key){
		Stage stage = Stage.GAMEOVER;
		GameOverRenderer menuGameOver = masterRenderer.getGameOverRenderer();
		switch(key){
		case KeyEvent.VK_DOWN:
			menuGameOver.nextOption();
			break;
		case KeyEvent.VK_UP:
			menuGameOver.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if(menuGameOver.getOption() == GameOverRenderer.MAIN_MENU)
				stage = Stage.MENU;
			else if(menuGameOver.getOption() == GameOverRenderer.EXIT) 	//revisar luego
				System.exit(0);
			break;
		}
		return stage;
	}
	//probando
	public Stage handlePlayerDeath(){
		return Stage.GAMEOVER;
	}
}