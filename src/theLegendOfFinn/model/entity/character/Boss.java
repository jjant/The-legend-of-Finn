package theLegendOfFinn.model.entity.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import theLegendOfFinn.model.Grid;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.BossProjectile;

public class Boss extends EnemyCharacter {
	private static final long serialVersionUID = 1L;

	// Teleporting state.
	public static final int TELEPORTING = 3;

	private static final int PROJECTILES_SPAWNED = 4;
	// probando
	private static final int RESTING_TIME = 1000;
	// cambiar dsp
	private static final Position BOSS_POSITION = new Position(32 * 5, 32 * 5);
	private static final int BOSS_VELOCITY = 3;
	private static final int BOSS_MAX_HP = 10;
	private static final int BOSS_ATTACK = 5;
	private static final int BOSS_HP_BOUNTY = 0;
	private static final long TELEPORT_DELAY = 500;

	private List<BossProjectile> projectiles;
	private int lastAction = Boss.MOVING;

	private Position nextPosition;

	public Boss() {
		super(BOSS_POSITION, BOSS_VELOCITY, BOSS_MAX_HP, BOSS_ATTACK, BOSS_HP_BOUNTY);
		projectiles = new ArrayList<>();
	}

	// probando

	public void act(Grid grid) {
		long now = System.currentTimeMillis();
		if (getTimer().attackTimePassed(now)) {
			getTimer().updateLastAttackTime(now);
			if (lastAction == Boss.MOVING) {
				tryToAttack();
				lastAction = Boss.ATTACKING;
			} else if (lastAction == Boss.BOSS_ATTACK) {
				tryToIdle();
				lastAction = Boss.IDLE;
			} else {
				tryToMove(Direction.randomDirection(), grid);
				lastAction = Boss.MOVING;
			}
		}
	}

	/**
	 * If it's possible, it attacks.
	 */
	public void tryToAttack() {
		if (state == IDLE) {
			attack();
		}
	}

	// do nothing..?
	public void tryToIdle() {

	}

	// cambiar dsp
	public void attack() {
		for (Direction direction : Direction.values()) {
			Position projPosition = getProjectilePosition(direction);
			if (projPosition.withinBoundaries())
				projectiles.add(new BossProjectile(projPosition, direction));
		}
	}

	public void tryToMove(Direction direction, Grid grid) {
		if (state == IDLE) {
			state = MOVING;

			Random r = new Random();

			do {
				int x = r.nextInt(grid.getWidth()) * Map.CELL_SIZE;
				int y = r.nextInt(grid.getHeight()) * Map.CELL_SIZE;
				nextPosition = new Position(x, y);
			} while (!grid.isFreePosition(nextPosition));

			Timer teleportTimer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					move(nextPosition);
					state = IDLE;
				}
			};
			teleportTimer.schedule(task, TELEPORT_DELAY);
		}
	}

	private void move(Position newPosition) {
		getPosition().setX(newPosition.getX());
		getPosition().setY(newPosition.getY());
	}

	private Position getProjectilePosition(Direction direction) {
		int bossX = getPosition().getX();
		int bossY = getPosition().getY();
		int x = 0, y = 0;

		switch (direction) {
		case UP:
			x = bossX;
			y = bossY + 1;
			break;
		case RIGHT:
			x = bossX + 1;
			y = bossY;
			break;
		case DOWN:
			x = bossX;
			y = bossY - 1;
			break;
		case LEFT:
			x = bossX - 1;
			y = bossY;
			break;
		}

		return new Position(x, y);
	}

	public List<BossProjectile> getProjectiles() {
		return projectiles;
	}
}
