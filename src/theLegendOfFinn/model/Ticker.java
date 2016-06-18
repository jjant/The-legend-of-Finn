package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import theLegendOfFinn.controller.communicators.Notifier;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyCharacter;
import theLegendOfFinn.model.entity.character.enemy.boss.Boss;
import theLegendOfFinn.model.entity.character.enemy.boss.BossProjectile;
import theLegendOfFinn.model.gameData.Map;
import theLegendOfFinn.model.gameData.Round;
import theLegendOfFinn.model.gameData.Round.RoundType;
import theLegendOfFinn.model.entity.Entity;

/**
 * Class responsible for updating the model.
 */
public class Ticker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Different arenas to play on.
	public enum Arena {
		GRASS, ICE, LAVA, MOUNTAIN;
	}

	// Round fields. Represent the enemies on the map on a given point.
	private int roundNumber;
	private Round.RoundType roundType;
	private Round round;
	
	private Map map;
	private Boolean canModify = false;
	private transient Notifier notifier;
	private Arena arena;

	public Ticker(Notifier notifier, Round.RoundType gameMode) {
		this.notifier = notifier;
		this.renew(gameMode);
	}

	/**
	 * Renews the ticker, setting it up for a new game.
	 * 
	 * @param gameMode Either normal or survival.
	 *  
	 */
	public void renew(Round.RoundType gameMode) {
		this.roundType = gameMode;
		roundNumber = 0;
		round = new Round(gameMode, roundNumber);
		this.map = new Map(new PlayerCharacter(), round.getRoundEnemies());
	}

	/**
	 * Updates the game model.
	 */
	public void tick() {
		if (canModify) {
			PlayerCharacter player = map.getPlayer();
			player.move();
			player.updateStatus();
			if (roundType.equals(Round.RoundType.BOSS))
				tickBoss();
			else
				behaviourEnemies(map.getEnemies());
			if (!player.isAlive())
				notifier.NotifyDeath();
		}
	}

	/**
	 * Updates the boss stage.
	 */
	public void tickBoss() {
		Boss boss = getBoss();
		if (boss.isAlive()) {
			boss.act(map.getGrid());
			List<BossProjectile> projectiles = boss.getProjectiles();
			Iterator<BossProjectile> iter = projectiles.iterator();
			while (iter.hasNext()) {
				BossProjectile projectile = iter.next();
				projectile.move();
				if (projectile.attack(getPlayer()))
					iter.remove();

				if (!projectile.getPosition().withinBoundaries()) {
					iter.remove();
				}
			}
		}
		else
			notifier.notifyWin();
		
	}
	
	/**
	 * Returns the list of enemies from the current map.
	 * 
	 * @return list of enemies.
	 */
	public List<EnemyCharacter> getEnemies() {
		return map.getEnemies();
	}

	/**
	 * Gets the player character.
	 * 
	 * @return the player character.
	 */
	public PlayerCharacter getPlayer() {
		return map.getPlayer();
	}
	
	/**
	 * Loads a new map into the game
	 * @param map map to load.
	 */
	public void loadMap(Map map) {
		this.map = map;
	}

	/**
	 * Gets the current game's map.
	 * @return the map.
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Sets the game's stop condition
	 * @param b the truth value
	 */
	public void changeModifier(Boolean b) {
		canModify = b;
	}

	/**
	 * Sets the enemies' behavior: They'll seek to chase and attack the player.
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
					enemy.chasePlayer(map.getPlayer().getPosition(), map.getGrid());
					enemy.attack(map.getPlayer());
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
	
	/**
	 * Says when a round is finished (Every enemy is dead).
	 * @return true if there are not enemies left. false otherwise.
	 */
	public boolean roundFinished() {
		return !round.enemiesLeft();
	}

	/**
	 * Sets a round
	 * @param round round to set.
	 */
	public void setRound(Round round) {
		this.round = round;
	}

	/**
	 * Manages rounds by brining the next one taking into account
	 * the enemies, rounds, and game modes.
	 */
	public void nextRound() {
		roundNumber++;
		if (roundNumber == 2 && roundType == Round.RoundType.NORMAL
			|| roundNumber == 8 && roundType == Round.RoundType.SURVIVAL)
			getPlayer().levelUp();

		if (roundType == Round.RoundType.NORMAL) {
			if (roundNumber == 9)
				roundType = Round.RoundType.BOSS;
		} else if (roundType == Round.RoundType.BOSS) {
			// should display YOU WIN or something like that and return to main
			// menu.
		}
		round = new Round(roundType, roundNumber);
		updateMap();
	}

	/**
	 * Updates map's round
	 */
	public void updateMap() {
		map.setRound(round);
	}

	/**
	 * Changes the game notifier
	 * @param notifier notifier to update.
	 */
	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}

	/**
	 * Gets the current arena
	 * @return arena
	 */
	public Arena getArena() {
		return arena;
	}

	/**
	 * Sets arena to a given one.
	 * @param arena to load.
	 */
	public void setArena(Arena arena) {
		this.arena = arena;
	}

	/**
	 * Gets the boss if there is one in the map. Null otherwise.
	 * @return the boss.
	 */
	private Boss getBoss() {
		if (!roundType.equals(RoundType.BOSS))
			return null;
		return map.getBoss();
	}
}
