package theLegendOfFinn.model;

import java.io.Serializable;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.Entity;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;

public class Grid implements Serializable {
	private static final long serialVersionUID = 1L;

	private Entity[][] matrix;
	private int enemiesAlive;

	public Grid() {
		matrix = new Entity[Map.WIDTH][Map.HEIGHT];
		enemiesAlive = 0;
	}

	public void add(Entity entity) throws PositionOccupiedException {
		if (matrix[entity.getPosition().getX() / Map.CELL_SIZE][entity.getPosition().getY() / Map.CELL_SIZE] != null) {
			throw new PositionOccupiedException("La posicion [" + entity.getPosition().getX() + "]" + "["
					+ entity.getPosition().getY() + "]" + " esta ocupada, imposible a�adir " + entity + ".");
		}
		matrix[entity.getPosition().getX() / Map.CELL_SIZE][entity.getPosition().getY() / Map.CELL_SIZE] = entity;
		enemiesAlive += 1;
	}

	public boolean isFreePosition(Position pos) {
		if (matrix[pos.getX() / Map.CELL_SIZE][pos.getY() / Map.CELL_SIZE] != null)
			return false;
		return true;
	}

	public void freePosition(Position pos) {
		matrix[pos.getX() / Map.CELL_SIZE][pos.getY() / Map.CELL_SIZE] = null;
	}

	public void occupyPosition(Entity entity, Position pos) {
		matrix[pos.getX() / Map.CELL_SIZE][pos.getY() / Map.CELL_SIZE] = entity;
	}

	public int getEnemiesAlive() {
		return enemiesAlive;
	}

	public Entity[][] getCharMatrix() {
		return matrix;
	}

	public boolean areEnemiesLeft() {
		if (enemiesAlive > 0)
			return true;
		return false;
	}

	public int getHeight() {
		return Map.HEIGHT;
	}

	public int getWidth() {
		return Map.WIDTH;
	}
	
	public Entity getEntity(Position position){
		int x = position.getX()/Map.CELL_SIZE;
		int y = position.getY()/Map.CELL_SIZE;
		return matrix[x][y];
	}

}
