package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import theLegendOfFinn.controller.Notifier;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.PlayerCharacter;

public class Ticker implements Serializable {
	private int roundDifficulty;
	private Map map;
	private Round round;
	private Boolean canModify = false;
	private transient Notifier notifier;

	public Ticker(PlayerCharacter player, Notifier notifier) {
		roundDifficulty = 0;
		round = new Round(roundDifficulty);
		//round = Round.round1();
		this.notifier = notifier;
		this.map = new Map(player, round.getEnemies());
	}

	public void tick() {
		if (canModify) {
			PlayerCharacter player = map.getPlayer();
			player.move();
			player.updateStatus();
			behaviourEnemies(map.getEnemies());
			if(!player.isAlive())
				notifier.NotifyDeath();
		}
	}

	public void loadTicker(Ticker ticker) {
		map = ticker.map;
		round = ticker.round;
	}
	
	public List<EnemyCharacter> getEnemies() {
		return map.getEnemies();
	}

	public PlayerCharacter getPlayer() {
		return map.getPlayer();
	}
	
	public void loadMap(Map map) {
		this.map = map;
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
					enemy.updateStatus();
					enemy.attackNearbyPlayer(map.getPlayer());
					enemy.chasePlayer(map.getPlayer().getPosition(), map.getGrid());
					enemy.move();
				} else {
					if (enemy.getPosition().getY() % Map.CELL_SIZE != 0 && enemy.getDirection() == Character.Direction.DOWN)
						enemy.getPosition().incY(Map.CELL_SIZE);
					else if (enemy.getPosition().getX() % Map.CELL_SIZE != 0 && enemy.getDirection() == Character.Direction.RIGHT)
						enemy.getPosition().incX(Map.CELL_SIZE);
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
		if (roundDifficulty == 4) {
			round = new Round(roundDifficulty);				//should begin the boss round
			return;
		}
		roundDifficulty ++;
		round = new Round(roundDifficulty);
		//round = Round.round2();
		updateMap();
	}

	public void updateMap() {
		map.setRound(round);
	}
}
