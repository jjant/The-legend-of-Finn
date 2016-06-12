package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import theLegendOfFinn.controller.communicators.Notifier;
import theLegendOfFinn.model.Round;
import theLegendOfFinn.model.entity.character.EnemyCharacter;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.Entity;

public class Ticker implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Arena{
		GRASS, ICE, LAVA, MOUNTAIN;
	}
	
	private int roundNumber;
	private Round.RoundTypes roundType;
	private Map map;
	private Round round;
	private Boolean canModify = false;
	private transient Notifier notifier;
	//probando
	private Arena arena;

	public Ticker(Notifier notifier) {
		this.notifier = notifier;
		this.renew();
	}

	public void renew() {
		roundType = Round.RoundTypes.NORMAL;
		roundNumber = 0;
		round = new Round(roundType, roundNumber);
		this.map = new Map(new PlayerCharacter(0), round.getEnemies());
	}

	public void tick() {
		if (canModify) {
			PlayerCharacter player = map.getPlayer();
			player.move();
			player.updateStatus();
			behaviourEnemies(map.getEnemies());
			if (!player.isAlive())
				notifier.NotifyDeath();
		}
	}

	public static Ticker loadTicker(Ticker ticker) {
		return ticker;
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

	/**
	 * Sets the enemies' behavior: They'll seek to chase and attack the player.
	 * 
	 * @param enemies
	 *            List of enemies who's behavior is to be set.
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
					if (enemy.getPosition().getY() % Map.CELL_SIZE != 0
							&& enemy.getDirection() == Entity.Direction.DOWN)
						enemy.getPosition().incY(Map.CELL_SIZE);
					else if (enemy.getPosition().getX() % Map.CELL_SIZE != 0
							&& enemy.getDirection() == Entity.Direction.RIGHT)
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
		/*
		if (roundDifficulty == 4) {
			round = new Round(roundDifficulty); // should begin the boss round
			return;
		}
		*/
		roundNumber ++;
		//probando
		if(roundNumber == 2 && roundType == Round.RoundTypes.NORMAL)
			getPlayer().levelUp();
		//
		if (roundType == Round.RoundTypes.NORMAL) {
			if (roundNumber == 4)
				roundType = Round.RoundTypes.BOSS;
		}
		else if (roundType == Round.RoundTypes.BOSS) {
			// should display YOU WIN or something like that and return to main menu.
		}
		round = new Round(roundType, roundNumber);
		// round = Round.round2();
		updateMap();
	}

	public void updateMap() {
		map.setRound(round);
	}

	// shoud only be called when game is looadded, change this mehtod's aname
	public void setNotifier(Notifier notifier){
		this.notifier = notifier;
	}
	
	public Arena getArena(){
		return arena;
	}
	
	public void setArena(Arena arena){
		this.arena = arena;
	}
}
