package theLegendOfFinn.model.utils;

import java.io.Serializable;

import theLegendOfFinn.model.entity.Entity.Direction;
import theLegendOfFinn.model.gameData.Map;

/**
 * Stores the coordinates of a point of the map.
 */
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	// Coordinates with the (0,0) at the top left corner.
	// Positives in down/right directions.
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position otherPosition) {
		this.x = otherPosition.getX();
		this.y = otherPosition.getY();
	}
	
	/**
	 * Sets a position compatible with the grid using a direction as guide. 
	 * 
	 * @param direction
	 * 				used to set the new coordinates.
	 * @return the changed position if it's a valid position, null otherwise.
	 */
	public Position toGridIndexes(Direction direction) {
		Position newPos = null;
		if (direction == null)
			return null;

		switch (direction) {
		case LEFT:
			newPos = new Position(this.getX() / Map.CELL_SIZE - 1, this.getY() / Map.CELL_SIZE);
			break;
		case UP:
			newPos = new Position(this.getX() / Map.CELL_SIZE, this.getY() / Map.CELL_SIZE - 1);
			break;
		case RIGHT:
			newPos = new Position(this.getX() / Map.CELL_SIZE + 1, this.getY() / Map.CELL_SIZE);
			break;
		case DOWN:
			newPos = new Position(this.getX() / Map.CELL_SIZE, this.getY() / Map.CELL_SIZE + 1);
			break;
		default:
			break;
		}

		if (newPos.getX() < 0 || newPos.getX() >= Map.WIDTH || newPos.getY() < 0 || newPos.getY() >= Map.HEIGHT) {
			return null;
		}

		return newPos;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Increments the x coordinate by a number.
	 * 
	 * @param increment
	 */
	public void incX(int increment) {
		x += increment;
	}

	/**
	 * Increments the y coordinate by a number.
	 * @param increment
	 */
	public void incY(int increment) {
		y += increment;
	}

	/**
	 * Increments both coordinates at the same time, not necessarily by the same number.
	 * 
	 * @param incrementX
	 * @param incrementY
	 */
	public void incPos(int incrementX, int incrementY) {
		x += incrementX;
		y += incrementY;
	}

	/**
	 * Gets the distance between the current's and an other's 'x' coordinate.
	 * 
	 * @param other
	 * 			the position to compare.
	 * @return The distance between this position's 'x' and other's.
	 */
	public int distanceX(Position other) {
		return Math.abs(this.getX() - other.getX());
	}

	/**
	 * Gets the distance between the current's and another's 'y' coordinate.
	 * 
	 * @param other
	 * 			the position to compare.
	 * @return The distance between this position's 'y' and other's.
	 */
	public int distanceY(Position other) {
		return Math.abs(this.getY() - other.getY());
	}
	
	

	/**
	 * Checks if another position is one grid's position far from the current.
	 * 
	 * @param position
	 * 				the position to be checked.
	 * @return true if the condition is true, false otherwise.
	 */
	public boolean isNearby(Position position) {
		if (Math.abs(getX() - position.getX()) + Math.abs(getY() - position.getY()) <= Map.CELL_SIZE)
			return true;
		return false;
	}
	
	/**
	 * Returns whether or not this position is within the map boundaries.
	 * @return true if this position is within the map boundaries.
	 */
	public boolean withinBoundaries() {
		return !(getX() < 0 || getX() >= Map.WIDTH * Map.CELL_SIZE || getY() < 0 || getY() >= Map.HEIGHT * Map.CELL_SIZE);
	}

	public boolean equals(Position position) {
		if (position == this)
			return true;
		if (position == null || !position.getClass().equals(this.getClass()))
			return false;
		return x == position.getX() && y == position.getY();
	}

	public String toString() {
		return "(" + this.getX() + ", " + this.getY() + ")";
	}
}
