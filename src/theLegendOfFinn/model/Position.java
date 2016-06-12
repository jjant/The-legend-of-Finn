package theLegendOfFinn.model;

import java.io.Serializable;

import theLegendOfFinn.model.entity.Entity.Direction;

public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position other) {
		this.x = other.getX();
		this.y = other.getY();
	}

	public Position toGridIndexes(Direction direction) {
		Position pos = null;
		if (direction == null)
			return null;

		switch (direction) {
		case LEFT:
			pos = new Position(this.getX() / Map.CELL_SIZE - 1, this.getY() / Map.CELL_SIZE);
			break;
		case UP:
			pos = new Position(this.getX() / Map.CELL_SIZE, this.getY() / Map.CELL_SIZE - 1);
			break;
		case RIGHT:
			pos = new Position(this.getX() / Map.CELL_SIZE + 1, this.getY() / Map.CELL_SIZE);
			break;
		case DOWN:
			pos = new Position(this.getX() / Map.CELL_SIZE, this.getY() / Map.CELL_SIZE + 1);
			break;
		default:
			break;
		}

		if (pos.getX() < 0 || pos.getX() >= Map.WIDTH || pos.getY() < 0 || pos.getY() >= Map.HEIGHT) {
			return null;
		}

		return pos;
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

	public void incX(int increment) {
		x += increment;
	}

	public void incY(int increment) {
		y += increment;
	}

	public void incPos(int incrementX, int incrementY) {
		x += incrementX;
		y += incrementY;
	}

	public int distanceX(Position other) {
		int distanceX = (other.getX() - this.getX());
		if (distanceX < 0)
			distanceX *= -1;
		return distanceX;
	}

	public int distanceY(Position other) {
		int distanceY = (other.getY() - this.getY());
		if (distanceY < 0)
			distanceY *= -1;
		return distanceY;
	}

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
