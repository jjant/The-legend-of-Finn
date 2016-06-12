package theLegendOfFinn.model.entity;

import theLegendOfFinn.model.Grid;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.Entity.Direction;
import theLegendOfFinn.model.entity.character.Character.State;

public class MovingEntity extends Entity {
	private static final long serialVersionUID = 1L;

	
	public enum State{
		IDLE, MOVING;
	}
	
	protected State state = State.IDLE;
	
	// Movement fields
	public final long MOVE_COOLDOWN = 15; // in ms
	protected long lastMoveTime;
	protected int moveRemaining;
	private int velocity;

	public MovingEntity(Position position, Direction direction, int velocity) {
		super(position, direction);
		this.velocity = velocity;
		lastMoveTime = 0;
	}
	
	
	public void tryToMove(Direction direction, Grid grid) {
		Position destination = null;

		if (state == State.MOVING || direction == null)
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
		if (destination.getX() < 0 || destination.getX() >= Map.WIDTH * Map.CELL_SIZE || destination.getY() < 0
				|| destination.getY() >= Map.HEIGHT * Map.CELL_SIZE || !grid.isFreePosition(destination)
				|| destination == null)
			return;

		state = State.MOVING;
		moveRemaining = Map.CELL_SIZE;
		lastMoveTime = System.currentTimeMillis();
		grid.occupyPosition(this, destination);
		grid.freePosition(this.getPosition());
	}

	/**
	 * Sets the character velocity.
	 * 
	 * @param velocity the velocity to be set.
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
	
	
}
