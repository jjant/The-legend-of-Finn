package theLegendOfFinn.model.gameData;

import java.io.Serializable;

import theLegendOfFinn.model.entity.Entity;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;
import theLegendOfFinn.model.utils.Position;

/**
 * Provides a matrix of Entities to take record of the position
 * that every one of them occupies in the map.
 */
public class Grid implements Serializable {
	private static final long serialVersionUID = 1L;

	private Entity[][] matrix;
	private int enemiesAlive;

	public Grid() {
		matrix = new Entity[Map.WIDTH][Map.HEIGHT];
		enemiesAlive = 0;
	}

	/**
	 * Adds a new Entity to the grid
	 * 
	 * @param entity
	 * @throws PositionOccupiedException if the intended position is occupied
	 */
	public void add(Entity entity) throws PositionOccupiedException {
		if (matrix[entity.getPosition().getX() / Map.CELL_SIZE][entity.getPosition().getY() / Map.CELL_SIZE] != null) {
			throw new PositionOccupiedException("La posicion [" + entity.getPosition().getX() + "]" + "["
					+ entity.getPosition().getY() + "]" + " esta ocupada, imposible a�adir " + entity + ".");
		}
		matrix[entity.getPosition().getX() / Map.CELL_SIZE][entity.getPosition().getY() / Map.CELL_SIZE] = entity;
		enemiesAlive += 1;
	}

	/**
	 * Checks if a particular position is free in the grid.
	 * 
	 * @param position the position intended to be checked.
	 * @return true if the position is free, false otherwise.
	 */
	public boolean isFree(Position position) {
		return matrix[position.getX() / Map.CELL_SIZE][position.getY() / Map.CELL_SIZE] == null;
	}

	/**
	 * Liberates a position in the grid setting it to null.
	 * 
	 * @param position the position intended to free.
	 */
	public void freePosition(Position position) {
		matrix[position.getX() / Map.CELL_SIZE][position.getY() / Map.CELL_SIZE] = null;
	}

	/**
	 * Occupies a position of the grid with an Entity.
	 * 
	 * @param entity the Entity that will occupy the position.
	 * @param pos the position to be occupied.
	 */
	public void occupyPosition(Entity entity, Position position) {
		matrix[position.getX() / Map.CELL_SIZE][position.getY() / Map.CELL_SIZE] = entity;
	}

	/**
	 * Gets the enemies alive.
	 * 
	 * @return the number of enemies alive in the grid.
	 */
	public int getEnemiesAlive() {
		return enemiesAlive;
	}

	/**
	 * Gets the matrix of entities.
	 * 
	 * @return the matrix.
	 */
	public Entity[][] getCharMatrix() {
		return matrix;
	}

	/**
	 * Checks if there are enemies left in the grid.
	 * 
	 * @return true if there are, false otherwise.
	 */
	public boolean areEnemiesLeft() {
		return enemiesAlive > 0;
	}

	public int getHeight() {
		return Map.HEIGHT;
	}

	public int getWidth() {
		return Map.WIDTH;
	}
	
	/**
	 * Gets an Entity in a particular position of the grid.
	 * 
	 * @param position
	 * @return the Entity in the position.
	 */
	public Entity getEntity(Position position){
		int x = position.getX()/Map.CELL_SIZE;
		int y = position.getY()/Map.CELL_SIZE;
			return matrix[x][y];	
	}
}
