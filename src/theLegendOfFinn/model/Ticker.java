package theLegendOfFinn.model;

import java.util.Iterator;
import java.util.List;

import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.PlayerCharacter;

public class Ticker {
	Map map;
	Round round;
	Boolean canModify = false;

	/*
	 * public Ticker() { map = new Map(); }
	 */

	public Ticker(PlayerCharacter player) {
		round = Round.round1();
		this.map = new Map(player, round.getEnemies());
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

	// Maybe rename to: toggleMovement
	public void changeModifier(Boolean b) {
		canModify = b;
	}
	
	// Hace que todos se muevan
	private void moveCharacter(PlayerCharacter player, List<EnemyCharacter> enemies) {
		if (canModify) {
			player.move();
			Iterator<EnemyCharacter> enemyIter = enemies.iterator();
			// if (RenderManager.secondPassed()) {

			while (enemyIter.hasNext()) {
				EnemyCharacter enemy = enemyIter.next();
				if (enemy.isAlive()) {
					enemy.chasePlayer(player.getPosition(), map.getGrid());
					// enemy.chasePlayer(player.getX(), player.getY());
					enemy.move();
				} else {
					map.getGrid().freePosition(enemy.getPosition());
					enemyIter.remove();
				}
			}
			/*
			 * for (EnemyCharacter enemy : enemies) { // This raises
			 * ConcurrentModificationException // Should be looped differently
			 * or done in another way. /*if (!enemy.isAlive()) {
			 * map.getGrid().freePosition(enemy.getPosition());
			 * enemies.remove(enemy); } else {
			 * enemy.chasePlayer(player.getPosition(), map.getGrid());
			 * //enemy.chasePlayer(player.getX(), player.getY()); enemy.move();
			 * } enemy.chasePlayer(player.getPosition(), map.getGrid());
			 * //enemy.chasePlayer(player.getX(), player.getY()); enemy.move();
			 * }
			 */
			// }
		}
		/*
		 * for(EnemyCharacter enemy: enemies){ //enemy.move(); }
		 */
	}
	
	
	public boolean roundFinished(){
		return !round.enemiesLeft();
	}
	public void setRound(Round round){
		this.round = round;
	}
	public void nextRound(){
		round = Round.round2();
		updateMap();
	}
	public void updateMap(){
		map.setRound(round);
	}
	
	
}
