package theLegendOfFinn.model.entity;

import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.gameData.Grid;
import theLegendOfFinn.model.gameData.Map;
import theLegendOfFinn.model.utils.Position;
import theLegendOfFinn.model.utils.Timer;

public class ActingEntity extends Entity {
	private static final long serialVersionUID = 1L;

	public static final int MOVING = 1;

	// Attacking state.
	public static final int ATTACKING = 2;

	// Attack fields
	private final int RANGE = 1;
	private int attack;

	protected int state = IDLE;

	// Movement fields
	/*
	 * public final long MOVE_COOLDOWN = 15; // in ms protected long
	 * lastMoveTime;
	 */

	private Timer timer;
	protected int moveRemaining;
	private int velocity;

	public ActingEntity(Position position, Direction direction, int velocity, int attack) {
		super(position, direction);
		this.velocity = velocity;
		// lastMoveTime = 0;
		this.timer = new Timer(velocity);
		this.attack = attack;
	}

	/**
	 * Tries to move the character to the specified direction. If movement is
	 * impossible, it does nothing.
	 * 
	 * @param direction
	 *            the direction towards the movement is desired.
	 * @param grid
	 *            the character grid.
	 */
	public void tryToMove(Direction direction, Grid grid) {
		Position destination = null;

		if (state == MOVING || direction == null)
			return;

		this.direction = direction;
		switch (direction) {
		case LEFT:
			destination = new Position(getPosition().getX() - Map.CELL_SIZE, getPosition().getY());
			break;
		case RIGHT:
			destination = new Position(getPosition().getX() + Map.CELL_SIZE, getPosition().getY());
			break;
		case UP:
			destination = new Position(getPosition().getX(), getPosition().getY() - Map.CELL_SIZE);
			break;
		case DOWN:
			destination = new Position(getPosition().getX(), getPosition().getY() + Map.CELL_SIZE);
			break;
		}

		// Check destination is within the borders of the map, and its a valid
		// destination.
		if (destination == null || !destination.withinBoundaries() || !grid.isFreePosition(destination))
			return;
		state = MOVING;
		moveRemaining = Map.CELL_SIZE;
		timer.updateLastMoveTime(System.currentTimeMillis());
		// lastMoveTime = System.currentTimeMillis();
		grid.occupyPosition(this, destination);
		grid.freePosition(this.getPosition());
	}

	
	//deberia ser privado y no relizar validaciones...
	/**
	 * Moves the character step by step.
	 */
	public void move() {
		int yIncrement = 0, xIncrement = 0;
		if (moveRemaining == 0) {
			updateStatus();
			return;
		}

		long nowTime = System.currentTimeMillis();
		if (getTimer().moveTimePassed(nowTime)) {
			getTimer().updateLastMoveTime(nowTime);

			/*
			 * long nowMoveTime = System.currentTimeMillis(); if (nowMoveTime -
			 * lastMoveTime >= MOVE_COOLDOWN / getVelocity()) { lastMoveTime =
			 * nowMoveTime;
			 */

			moveRemaining--;

			switch (direction) {
			case UP:
				yIncrement = -1;
				xIncrement = 0;
				break;
			case LEFT:
				yIncrement = 0;
				xIncrement = -1;
				break;
			case DOWN:
				yIncrement = 1;
				xIncrement = 0;
				break;
			case RIGHT:
				yIncrement = 0;
				xIncrement = 1;
				break;
			default:
				break;
			}
			getPosition().incPos(xIncrement, yIncrement);
		}
	}

	/**
	 * Updates status to corresponding one.
	 */
	public void updateStatus() {
		if (state == MOVING && moveRemaining <= 0)
			state = IDLE;
	}

	/**
	 * Sets the character velocity.
	 * 
	 * @param velocity
	 *            the velocity to be set.
	 */
	protected void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	/**
	 * Gets the character velocity.
	 * 
	 * @return character velocity.
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Gets character current state
	 * 
	 * @return character state
	 */
	public int getState() {
		return state;
	}

	/**
	 * Gets Entity timer
	 * 
	 * @return timer
	 */
	public Timer getTimer() {
		return timer;
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
	 * Attacks a character. If there's no character to be attacked,
	 * <code>null</code> is received.
	 * 
	 * @param character
	 *            Character to be attacked (or null).
	 * @return true if could attack it, false otherwise
	 */
	public boolean attack(Entity entity) {
		// long now = System.currentTimeMillis();
		long nowTime = System.currentTimeMillis();
		if (this.getTimer().attackTimePassed(nowTime) && state != IDLE)
			return false;

		/*
		 * if (now - lastAttackTime <= ATTACK_COOLDOWN && state != IDLE) return
		 * false;
		 */

		state = ATTACKING;

		this.getTimer().updateLastAttackTime(nowTime);
		// lastAttackTime = System.currentTimeMillis();

		// If what's in the position to be attacked is not a character,
		// do nothing.
		if (!(entity instanceof Character))
			return false;

		Character character = (Character) entity;

		// We check this after setting the state, to allow the character to
		// "attack" empty spaces;
		if (character == null || character == this || !closeEnough(character, RANGE))
			return false;
		character.receiveAttack(this);
		return true;
	}

}
