package theLegendOfFinn.model.entity.character;

import java.util.Timer;
import java.util.TimerTask;

import theLegendOfFinn.model.Position;

public class Boss extends EnemyCharacter {
	private static final long serialVersionUID = 1L;

	// re villero, pero dsp lo miran.
	public enum State{
		IDLE, MOVING, ATTACKING, TELEPORTING;
	};

	// probando
	private static final int RESTING_TIME = 1000;
	// cambiar dsp
	private static final Position BOSS_POSITION = new Position(0, 120);
	private static final int BOSS_VELOCITY = 3;
	private static final int BOSS_MAX_HP = 10;
	private static final int BOSS_ATTACK = 5;
	private static final int BOSS_HP_BOUNTY = 0;
	private static final long TELEPORT_DELAY = 500;

	private State state = State.IDLE;

	public Boss() {
		super(BOSS_POSITION, BOSS_VELOCITY, BOSS_MAX_HP, BOSS_ATTACK, BOSS_HP_BOUNTY);
	}

	
	
	// probando
	
	public void act() {

	}

	public void tryToAttack(){
		if(state == State.IDLE){
			attack();
		}
	}
	
	public void attack() {
		//throw projectiles
	}

	public void tryToTeleport(Position newPosition) {
		if (state == State.IDLE) {
			state = State.TELEPORTING;
			Timer teleportTimer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					teleport(newPosition);
					state = State.IDLE;
				}
			};
			teleportTimer.schedule(task, TELEPORT_DELAY);
		}
	}

	private void teleport(Position newPosition) {
		getPosition().setX(newPosition.getX());
		getPosition().setY(newPosition.getY());
	}

}
