package theLegendOfFinn.model.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import theLegendOfFinn.model.Position;

public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Directions to which an entity can be facing.
	 */
	public enum Direction {
		UP, RIGHT, DOWN, LEFT;

		private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();

		/**
		 * Returns a random direction.
		 * @return A random direction.
		 */
		public static Direction randomDirection() {
			return VALUES.get(RANDOM.nextInt(SIZE));
		}
	}

	private Position position;
	protected Direction direction;

	public Entity(Position position, Direction direction) {
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
