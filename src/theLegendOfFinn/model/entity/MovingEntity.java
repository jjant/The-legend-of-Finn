package theLegendOfFinn.model.entity;

import theLegendOfFinn.model.Grid;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.Timer;

public class MovingEntity extends Entity {
	private static final long serialVersionUID = 1L;

	public static final int IDLE = 0;
	public static final int MOVING = 1;

	protected int state = IDLE;

	// Movement fields
	/*
	 * public final long MOVE_COOLDOWN = 15; // in ms protected long
	 * lastMoveTime;
	 */

	private Timer timer;
	protected int moveRemaining;
	private int velocity;

	public MovingEntity(Position position, Direction direction, int velocity) {
		super(position, direction);
		this.velocity = velocity;
		// lastMoveTime = 0;
		this.timer = new Timer(velocity);
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

}
