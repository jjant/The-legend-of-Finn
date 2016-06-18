package theLegendOfFinn.model.utils;

import java.io.Serializable;

public class Timer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Cooldown times for characters
	private static final long ATTACK_COOLDOWN = 500; // in ms
	private static final long MOVE_COOLDOWN_BASE = 15; // in ms
	
	// Time attributes for characters
	private long lastAttackTime;
	private long lastMoveTime;
	private long moveCooldown;

	public Timer(int velocity) {
		lastAttackTime = 0;
		lastMoveTime = 0;
		moveCooldown = MOVE_COOLDOWN_BASE / velocity;
	}
	
	/**
	 * Gets the time for the last attack
	 * @return the time for the last attack
	 */
	public long getLastAttackTime() {
		return lastAttackTime;
	}
	
	/**
	 * Gets the time for the last movement
	 * @return the time for the last movement
	 */
	public long getLastMoveTime() {
		return lastMoveTime;
	}
	
	/**
	 * Gets the time necessary to wait after a movement
	 * @return the move cooldown time
	 */
	public long getMoveCooldown() {
		return moveCooldown;
	}

	/**
	 * Sets the time for last attack
	 * @param nowTime time to update the last attack's time
	 */
	public void updateLastAttackTime(long nowTime) {
		this.lastAttackTime = nowTime;
	}
	
	/**
	 * Sets the time for last movement
	 * @param nowTime time to update the last movemnt's time
	 */
	public void updateLastMoveTime(long nowTime) {
		this.lastMoveTime = nowTime;
	}
	
	/**
	 * Compares attack time elapsed with the default cooldown
	 * @param nowTime time to compare
	 * @return true if elapsed time overpass the cooldown. false otherwise.
	 */
	public boolean attackTimePassed(long nowTime) {
		return (nowTime - this.getLastAttackTime() >= ATTACK_COOLDOWN);
	}
	
	/**
	 * Compares movement time elapsed with the default cooldown
	 * @param nowTime time to compare
	 * @return true if elapsed time overpass the cooldown. false otherwise.
	 */
	public boolean moveTimePassed(long nowTime) {
		return (nowTime - this.getLastMoveTime() >= moveCooldown);
	}
}