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

	public enum Arena {
		GRASS, ICE, LAVA, MOUNTAIN;
	}

	private int roundNumber;
	private Round.RoundType roundType;
	private Map map;
	private Round round;
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
	 * @param gameMode
	 *            Either normal or survival.
	 */
	public void renew(Round.RoundType gameMode) {
		this.roundType = gameMode;
		roundNumber = 8;
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
			if (roundType.equals(Round.RoundType.BOSS)) {
				tickBoss();
			} else {
				behaviourEnemies(map.getEnemies());
			}
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

	public static Ticker loadTicker(Ticker ticker) {
		return ticker;
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

	public boolean roundFinished() {
		return !round.enemiesLeft();
	}
	//

	public void setRound(Round round) {
		this.round = round;
	}

	public void nextRound() {
		roundNumber++;
		if (roundNumber == 2 && roundType == Round.RoundType.NORMAL
				|| roundNumber == 8 && roundType == Round.RoundType.SURVIVAL)
			getPlayer().levelUp();

		if (roundType == Round.RoundType.NORMAL) {
			if (roundNumber == 9) {
				roundType = Round.RoundType.BOSS;

			}
		} else if (roundType == Round.RoundType.BOSS) {
			// should display YOU WIN or something like that and return to main
			// menu.
		}
		round = new Round(roundType, roundNumber);
		// round = Round.round2();
		updateMap();
	}

	public void updateMap() {
		map.setRound(round);
	}

	// shoud only be called when game is looadded, change this mehtod's aname
	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	private Boss getBoss() {
		if (!roundType.equals(RoundType.BOSS))
			return null;
		return map.getBoss();
	}
}
