package theLegendOfFinn.model;

import java.util.List;

import theLegendOfFinn.controller.RenderManager;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.PlayerCharacter;

public class Ticker {
	Map map;

	public Ticker() {
		map = new Map();
	}

	public Ticker(Map map) {
		this.map = map;
	}

	public void updateOnEvent(int event) {
		// do stuff
	}

	public void tick() {
		moveCharacter(map.getPlayer(), map.getEnemies());
	}
	
	public List<EnemyCharacter> getEnemies() {
		return map.getEnemies();
	}

	public PlayerCharacter getPlayer() {
		return map.getPlayer();
	}

	public Map getMap() {
		return map;
	}

	//Hace que todos se muevan
	private void moveCharacter(PlayerCharacter player, List<EnemyCharacter> enemies) {
		player.move();
		for (EnemyCharacter enemy: enemies) {
			if (RenderManager.secondPassed()) {
				enemy.chasePlayer(player.getX(), player.getY());
				enemy.move();
			}
		}
		/*
		 * for(EnemyCharacter enemy: enemies){ //enemy.move(); }
		 */
	}

}
