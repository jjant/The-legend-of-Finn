package theLegendOfFinn.model.entity;

import theLegendOfFinn.model.Grid;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.Entity.Direction;

public class Projectile extends MovingEntity {
	private static final long serialVersionUID = 1L;

	private static final int PROJECTILE_VELOCITY = 1;
	public Projectile(Position position, Direction direction) {
		super(position, direction, PROJECTILE_VELOCITY);

	}

	
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
		if (destination.getX() < 0 || destination.getX() >= Map.WIDTH * Map.CELL_SIZE || destination.getY() < 0
				|| destination.getY() >= Map.HEIGHT * Map.CELL_SIZE || !grid.isFreePosition(destination)
				|| destination == null)
			return;

		state = MOVING;
		moveRemaining = Map.CELL_SIZE;
		this.getTimer().updateLastMoveTime(System.currentTimeMillis());
		//lastMoveTime = System.currentTimeMillis();
		grid.occupyPosition(this, destination);
		grid.freePosition(this.getPosition());
	}
	

	public void move() {

	}
}
