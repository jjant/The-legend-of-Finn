package theLegendOfFinn.controller.communicators;

import java.util.List;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Ticker.Arena;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyCharacter;
import theLegendOfFinn.model.gameData.Map;;

/**
 * Provides an interface for the view to communicate changes to the controller.
 */
public class Delegate {
	private Manager manager;

	public Delegate(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Sets the stage for a given one
	 * @param stage stage to update to
	 */
	public void setStage(Stage stage) {
		manager.updateStage(stage);
	}

	/**
	 * Gets the current stage
	 * @return current stage
	 */
	public Stage getStage() {
		return manager.getStage();
	}

	/**
	 * Pass to the manager the key pressed
	 * @param key key pressed
	 */
	public void passKeyPressed(int key) {
		manager.keyChange(key);
	}

	/**
	 * Gets the current player 
	 * @return player
	 */
	public PlayerCharacter getPlayer() {
		return manager.getTicker().getPlayer();
	}
	
	/**
	 * Gets a list of the enemies
	 * @return the list of the enemies
	 */
	public List<EnemyCharacter> getEnemies() {
		return manager.getTicker().getEnemies();
	}

	/**
	 * Gets the current map
	 * @return current map
	 */
	public Map getMap() {
		return manager.getTicker().getMap();
	}
	
	/**
	 * Gets the arena
	 * @return current arena
	 */
	public Arena getArena(){
		return manager.getTicker().getArena();
	}
}
