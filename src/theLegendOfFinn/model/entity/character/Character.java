package theLegendOfFinn.model.entity.character;

import java.io.Serializable;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.Entity;
import theLegendOfFinn.model.entity.MovingEntity;

/**
 * Parent class for every character in the game.
 *
 */
public abstract class Character extends MovingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attacking state.
	public static final int ATTACKING = 2;

	// Health fields
	private int maxHP;
	private int currentHP;

	// Attack fields
	private int attack;
	
	/*
	private long lastAttackTime;
	private final long ATTACK_COOLDOWN = 500; // in ms
	*/

	public Character(Position position, Direction direction, int maxHP, int attack, int velocity) {
		super(position, direction, velocity);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.attack = attack;
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
	 * Sets the character's attack.
	 * 
	 * @param attack
	 *            the new attack value to be set.
	 */
	protected void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Gets character attack points (How much damage it does).
	 * 
	 * @return attack points
	 */
	public int getAttack() {
		return attack;
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
	 * Attacks a character. If there's no character to be attacked,
	 * <code>null</code> is received.
	 * 
	 * @param character
	 *            Character to be attacked (or null).
	 * @return true if could attack it, false otherwise
	 */
	public boolean attack(Entity entity) {
		//long now = System.currentTimeMillis();
		long nowTime = System.currentTimeMillis();
		if (this.getTimer().attackTimePassed(nowTime) && state != IDLE)
			return false;
		
		/*
		if (now - lastAttackTime <= ATTACK_COOLDOWN && state != IDLE)
			return false;
		*/

		state = ATTACKING;
		
		this.getTimer().updateLastAttackTime(nowTime);
		//lastAttackTime = System.currentTimeMillis();

		// If what's in the position to be attacked is not a character,
		// do nothing.
		if (!(entity instanceof Character))
			return false;

		Character character = (Character) entity;

		// We check this after setting the state, to allow the character to
		// "attack" empty spaces;
		if (character == null || character == this || !closeEnough(character))
			return false;
		character.receiveAttack(this);
		return true;
	}

	/**
	 * Takes an amount of damage from a given character
	 * 
	 * @param character
	 *            Character who attacks.
	 */
	private void receiveAttack(Character character) {
		setCurrentHP(getCurrentHP() - character.getAttack());
	}

	/**
	 * Updates status to corresponding one.
	 */
	public void updateStatus() {
		//long now = System.currentTimeMillis();
		long nowTime = System.currentTimeMillis();
		
		if (state == ATTACKING && this.getTimer().attackTimePassed(nowTime))
			state = IDLE;
		/*
		if (state == ATTACKING && now - lastAttackTime >= ATTACK_COOLDOWN)
			state = IDLE;
		*/
		else if ((state == MOVING) && moveRemaining <= 0) 
			state = IDLE;
	}

	/**
	 * Checks if calling character is close enough to another character given a
	 * DELTA (function of CELL_SIZE).
	 * 
	 * @param character
	 *            Character
	 * @return true if is close enough, false otherwise.
	 */
	public boolean closeEnough(Character character) {
		// WE SHOULD CHANGE IT TO MAKE THE REVERSE VALIDATIONS.
		// WE SHOULD SAY WHEN ITS TRUE, NOT WHEN IS FALSE.
		int distanceX = getPosition().distanceX(character.getPosition());
		int distanceY = getPosition().distanceY(character.getPosition());

		if (distanceX == 0 && distanceY >= Map.CELL_SIZE * 3 / 2
				|| distanceX == Map.CELL_SIZE && distanceY >= Map.CELL_SIZE / 2
				|| distanceX >= Map.CELL_SIZE * 3 / 2 && distanceY == 0
				|| distanceX >= Map.CELL_SIZE / 2 && distanceY == Map.CELL_SIZE
				|| distanceX >= Map.CELL_SIZE && distanceY >= Map.CELL_SIZE)
			return false;

		return true;
	}
}
