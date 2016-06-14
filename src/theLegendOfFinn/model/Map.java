package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import theLegendOfFinn.model.entity.character.Boss;
import theLegendOfFinn.model.entity.character.EnemyCharacter;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;

/**
 * Contains the characters of the game and the grid of entities.
 */
public class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	// width and height of a single position of the grid.
	public static final int CELL_SIZE = 32;

	// width and height of the map measured in grid positions.
	public static final int WIDTH = 640 / CELL_SIZE;
	public static final int HEIGHT = ((WIDTH * 3) / 4);
	
	// useful positions in the map.
	public static final Position TOP_LEFT_CORNER = new Position(0, 0);
	public static final Position BOTTOM_LEFT_CORNER = new Position(0, (HEIGHT - 1) * CELL_SIZE);
	public static final Position TOP_RIGHT_CORNER = new Position((WIDTH - 1) * CELL_SIZE, 0);
	public static final Position BOTTOM_RIGHT_CORNER = new Position((WIDTH - 1) * CELL_SIZE, (HEIGHT - 1) * CELL_SIZE);

	private PlayerCharacter player;
	private List<EnemyCharacter> enemyList;
	
	private Grid grid;

	public Map(PlayerCharacter player) {
		this(player, new ArrayList<EnemyCharacter>());
	}

	public Map(PlayerCharacter player, List<EnemyCharacter> enemyList) {
		grid = new Grid();
		this.player = player;
		try {
			grid.add(player);
		} catch (PositionOccupiedException e) {
			e.printStackTrace();
		}
		this.enemyList = enemyList;
	}

	public PlayerCharacter getPlayer() {
		return player;
	}
	
	public List<EnemyCharacter> getEnemies() {
		return enemyList;
	}
	
	public Boss getBoss(){
		if(enemyList.get(0) instanceof Boss)
			return (Boss)enemyList.get(0);
		return null;
	}

	/**
	 * Gets the matrix of entities called grid.
	 * 
	 * @return the grid.
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Loads the enemies of a round to the enemyList of the map.
	 * 
	 * @param round
	 * 				a round with an array of enemies.
	 */
	public void setRound(Round round){
		enemyList = round.getRoundEnemies();
	}
	

	
}
