package theLegendOfFinn.model;

import java.io.Serializable;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;

public class CharacterGrid implements Serializable {

	private Character[][] matrix;
	private int enemiesAlive;
	
	public CharacterGrid() {
		matrix = new Character[Map.WIDTH][Map.HEIGHT];
		enemiesAlive = 0;
	}
	
	public void add(Character character) throws PositionOccupiedException {
		if (matrix[character.getPosition().getX()/Map.CELL_SIZE][character.getPosition().getY()/Map.CELL_SIZE] != null) {
			throw new PositionOccupiedException(
					"La posicion [" + character.getPosition().getX() + "]" + "[" + character.getPosition().getY() + "]" + " esta ocupada, imposible a�adir " + character + ".");
		}
		matrix[character.getPosition().getX()/Map.CELL_SIZE][character.getPosition().getY()/Map.CELL_SIZE] = character;
		enemiesAlive += 1;
	}
	
	public boolean isFreePosition(Position pos) {
		if (matrix[pos.getX()/Map.CELL_SIZE][pos.getY()/Map.CELL_SIZE] != null)
			return false;
		return true;
	}
	
	public void freePosition(Position pos) {
		matrix[pos.getX()/Map.CELL_SIZE][pos.getY()/Map.CELL_SIZE] = null;
	}
	
	public void occupyPosition(Character character, Position pos) {
		matrix[pos.getX()/Map.CELL_SIZE][pos.getY()/Map.CELL_SIZE] = character;
	}
	
	public int getEnemiesAlive() {
		return enemiesAlive;
	}
	
	public Character[][] getCharMatrix() {
		return matrix;
	}
	
	public boolean areEnemiesLeft() {
		if (enemiesAlive > 0)
			return true;
		return false;
	}
	
}
