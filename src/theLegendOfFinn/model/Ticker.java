package theLegendOfFinn.model;

import java.util.List;

import theLegendOfFinn.controller.RenderManager;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.PlayerCharacter;

public class Ticker {
	Map map;
	Boolean canModify=false;
	
	/*
	public Ticker() {
		map = new Map();
	}
	*/
	
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

	public void changeModifier(Boolean b){	canModify=b;	}
	//Hace que todos se muevan
	
	private void moveCharacter(PlayerCharacter player, List<EnemyCharacter> enemies) {
		if(canModify) {
			player.move();
			if (RenderManager.secondPassed()) {
				for (EnemyCharacter enemy : enemies) {
					// This raises ConcurrentModificationException
					// Should be looped differently or done in another way.
					/*if (!enemy.isAlive()) {
						map.getGrid().freePosition(enemy.getPosition());
						enemies.remove(enemy);
					} else {
						enemy.chasePlayer(player.getPosition(), map.getGrid());
						//enemy.chasePlayer(player.getX(), player.getY());
						enemy.move();	
					}*/
					enemy.chasePlayer(player.getPosition(), map.getGrid());
					//enemy.chasePlayer(player.getX(), player.getY());
					enemy.move();	
				}
			}
		}
		/*
		 * for(EnemyCharacter enemy: enemies){ //enemy.move(); }
		 */
	}

}
