package theLegendOfFinn.controller.communicators;

import java.util.List;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Ticker.Arena;
import theLegendOfFinn.model.entity.character.EnemyCharacter;
import theLegendOfFinn.model.entity.character.PlayerCharacter;;

/** Provides an interface for the view to communicate changes to the controller.
 * 
 */
public class Delegate {
	private Manager manager;

	public Delegate(Manager manager) {
		this.manager = manager;
	}

	public void setStage(Stage stage) {
		manager.updateStage(stage);
	}

	public Stage getStage() {
		return manager.getStage();
	}

	public void passKeyPressed(int key) {
		manager.keyChange(key);
	}

	public PlayerCharacter getPlayer() {
		return manager.getTicker().getPlayer();
	}

	public List<EnemyCharacter> getEnemies() {
		return manager.getTicker().getEnemies();
	}

	public Map getMap() {
		return manager.getTicker().getMap();
	}
	
	public Arena getArena(){
		return manager.getTicker().getArena();
	}
}
