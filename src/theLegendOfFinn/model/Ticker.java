package theLegendOfFinn.model;

import java.util.Iterator;
import java.util.List;

import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.PlayerCharacter;

public class Ticker {
	Map map;
	Round round;
	Boolean canModify = false;


	public Ticker(PlayerCharacter player) {
		round = Round.round1();
		this.map = new Map(player, round.getEnemies());
	}

	public void tick() {
		if (canModify) {
			PlayerCharacter player = map.getPlayer();
			player.move();
			behaviourEnemies(map.getEnemies());
		}
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

	/** Sets the enemies' behavior: They'll seek to chase and attack the player.
	 * 
	 * @param enemies List of enemies who's behavior is to be set. 
	 */
	private void behaviourEnemies(List<EnemyCharacter> enemies) {
		if (canModify) {
			Iterator<EnemyCharacter> enemyIter = enemies.iterator();
			while (enemyIter.hasNext()) {
				EnemyCharacter enemy = enemyIter.next();
				if (enemy.isAlive()) {
					enemy.attackNearbyPlayer(map.getPlayer());
					enemy.chasePlayer(map.getPlayer().getPosition(), map.getGrid());
					enemy.move();
				} else {
					map.getGrid().freePosition(enemy.getPosition());
					enemyIter.remove();
				}
			}
		}
	}

	public boolean roundFinished() {
		return !round.enemiesLeft();
	}
	//

	public void setRound(Round round) {
		this.round = round;
	}
	public void nextRound() {
		round = Round.round2();
		updateMap();
	}

	public void updateMap() {
		map.setRound(round);
	}

}
