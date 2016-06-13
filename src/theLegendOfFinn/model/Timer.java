package theLegendOfFinn.model;

import java.io.Serializable;

public class Timer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final long ATTACK_COOLDOWN = 500;			//in ms 
	public static final long MOVE_COOLDOWN_BASE = 15; 		//in ms
	
	private long lastAttackTime;
	private long lastMoveTime;
	private long moveCooldown;
	
	public Timer(int velocity) {
		lastAttackTime = 0;
		lastMoveTime = 0;
		moveCooldown = MOVE_COOLDOWN_BASE / velocity;
	}
	
	public long getLastAttackTime() {
		return lastAttackTime;
	}
	
	public long getLastMoveTime() {
		return lastMoveTime;
	}
	
	public long getMoveCooldown() {
		return moveCooldown;
	}
	
	public void updateLastAttackTime(long nowTime) {
		this.lastAttackTime = nowTime;
	}
	
	public void updateLastMoveTime(long nowTime) {
		this.lastMoveTime = nowTime;
	}
	
	public boolean attackTimePassed(long nowTime) {
		return (nowTime - this.getLastAttackTime() >= ATTACK_COOLDOWN);
	}
	
	public boolean moveTimePassed(long nowTime) {
		return (nowTime - this.getLastMoveTime() >= moveCooldown);
	}
}