package theLegendOfFinn.model.entity.character;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.Projectile;

public class Boss extends EnemyCharacter {
	private static final long serialVersionUID = 1L;
	
	// Teleporting state.
	public static final int TELEPORTING = 3;

	private static final int PROJECTILES_SPAWNED = 4;
	// probando
	private static final int RESTING_TIME = 1000;
	// cambiar dsp
	private static final Position BOSS_POSITION = new Position(0, 120);
	private static final int BOSS_VELOCITY = 3;
	private static final int BOSS_MAX_HP = 10;
	private static final int BOSS_ATTACK = 5;
	private static final int BOSS_HP_BOUNTY = 0;
	private static final long TELEPORT_DELAY = 500;

	public Boss() {
		super(BOSS_POSITION, BOSS_VELOCITY, BOSS_MAX_HP, BOSS_ATTACK, BOSS_HP_BOUNTY);

	}

	// probando

	public void act() {

	}

	public void tryToAttack() {
		if (state == IDLE) {
			attack();
		}
	}

	//cambiar dsp
	public void attack() {
		Map<Direction, Projectile> projectiles = new HashMap<>();
		for (Direction direction : Direction.values()){
			projectiles.put(direction, new Projectile(getProjectilePosition(direction), direction));
			projectiles.get(direction).move();
			}
	}


	public void tryToTeleport(Position newPosition) {
		if (state == IDLE) {
			state = TELEPORTING;
			Timer teleportTimer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					teleport(newPosition);
					state = IDLE;
				}
			};
			teleportTimer.schedule(task, TELEPORT_DELAY);
		}
	}

	private void teleport(Position newPosition) {
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

}
