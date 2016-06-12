package theLegendOfFinn.model.entity;

import java.io.Serializable;

import theLegendOfFinn.model.Position;

public class Entity implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * Directions to which an entity can be facing.
	 */
	public static enum Direction {
		UP, RIGHT, DOWN, LEFT;
	}
	
	private Position position;
	protected Direction direction;
	
	public Entity(Position position, Direction direction){
		this.position = position;
		this.direction = direction;
	}
		
	/**
	 * Gets the current position for this entity.
	 * 
	 * @return current position.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Gets the current direction for this entity.
	 * 
	 * @return the current direction.
	 */
	public Direction getDirection() {
		return direction;
	}
}
