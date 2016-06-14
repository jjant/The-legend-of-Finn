package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import theLegendOfFinn.controller.communicators.Notifier;
import theLegendOfFinn.model.Round;
import theLegendOfFinn.model.Round.RoundType;
import theLegendOfFinn.model.entity.character.Boss;
import theLegendOfFinn.model.entity.character.EnemyCharacter;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.BossProjectile;
import theLegendOfFinn.model.entity.Entity;

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
	// probando
	private Arena arena;

	public Ticker(Notifier notifier, Round.RoundType gameMode) {
		this.notifier = notifier;
		this.renew(gameMode);
	}

	public void renew(Round.RoundType gameMode) {
		this.roundType = gameMode;
		roundNumber = 0;
		round = new Round(gameMode, roundNumber);
		this.map = new Map(new PlayerCharacter(0), round.getEnemies());
	}

	public void tick() {
		if (canModify) {
			PlayerCharacter player = map.getPlayer();
			player.move();
			player.updateStatus();
			if (roundType.equals(Round.RoundType.BOSS)) {
				tickBoss();
			}
			else
				behaviourEnemies(map.getEnemies());
			if (!player.isAlive())
				notifier.NotifyDeath();
		}
	}

	public void tickBoss() {
		Boss boss = getBoss();
		boss.act(map.getGrid());
		List<BossProjectile> projectiles = boss.getProjectiles();
		Iterator<BossProjectile> iter = projectiles.iterator();
		while(iter.hasNext()){
			BossProjectile projectile = iter.next();
			projectile.tryToMove(projectile.getDirection(), map.getGrid());
			projectile.move();
			if(!projectile.getPosition().withinBoundaries()){
				iter.remove();
			}
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
