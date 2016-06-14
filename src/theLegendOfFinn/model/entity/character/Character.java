package theLegendOfFinn.model.entity.character;

import java.io.Serializable;

import theLegendOfFinn.model.entity.ActingEntity;
import theLegendOfFinn.model.utils.Position;

/**
 * Parent class for every character in the game.
 * Characters can receive damage from entities, for this they have HP.
 *
 */
public abstract class Character extends ActingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// Health fields
	private int maxHP;
	private int currentHP;


	public Character(Position position, Direction direction, int maxHP, int attack, int velocity) {
		super(position, direction, velocity, attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
	}

	/**
	 * Checks if the character is alive or not.
	 * 
	 * @return true if it's alive, false otherwise.
	 */
	public boolean isAlive() {
		return currentHP > 0;
	}

	/**
	 * Sets the maximum health points of this character.
	 * 
	 * @param maxHP
	 *            the new amount of maximum health points.
	 */
	protected void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	/**
	 * Gets the maximum health points the character can have.
	 * 
	 * @return the maximum health points for the character.
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * Sets the maximum health points of this character. If a value greater than
	 * this character's maxHP is passed, the current HP is set to maxHP.
	 * 
	 * @param maxHP
	 *            the new amount of maximum health points.
	 */
	protected void setCurrent(int currentHP) {
		if (currentHP > maxHP)
			this.currentHP = maxHP;
		else
			this.currentHP = currentHP;
	}

	/**
	 * Gets character current health points (HP)
	 * 
	 * @return current health points.
	 */
	public int getCurrentHP() {
		return currentHP;
	}

	/**
	 * Set health points (HP) to a new value
	 * 
	 * @param newHP
	 *            new value to set the health points.
	 */
	protected void setCurrentHP(int newHP) {
		this.currentHP = newHP;
	}

	/**
	 * Takes an amount of damage from a given character
	 * 
	 * @param actingEntity
	 *            Character who attacks.
	 */
	public void receiveAttack(ActingEntity actingEntity) {
		setCurrentHP(getCurrentHP() - actingEntity.getAttack());
	}

	/**
	 * Updates status to corresponding one.
	 */
	public void updateStatus() {
		// long now = System.currentTimeMillis();
		long nowTime = System.currentTimeMillis();

		if (state == ATTACKING && this.getTimer().attackTimePassed(nowTime))
			state = IDLE;
		/*
		 * if (state == ATTACKING && now - lastAttackTime >= ATTACK_COOLDOWN)
		 * state = IDLE;
		 */
		else if ((state == MOVING) && moveRemaining <= 0)
			state = IDLE;
	}

}
