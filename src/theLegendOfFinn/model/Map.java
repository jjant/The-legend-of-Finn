package theLegendOfFinn.model;

import java.util.ArrayList;
import java.util.List;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;

public class Map {
	/*
	 * Decidir si estas constantes van aca, en la clase aue hace de
	 * intermediario o en alguna del view. CELL_SIZE repetida :/
	 */

	public static final int CELL_SIZE = 32;

	public static final int WIDTH = 640 / CELL_SIZE;
	public static final int HEIGHT = ((WIDTH * 3) / 4);

	// No se que podria ir aca, tenia pensado una lista de terrenos, pero no se.

	// Ver si quedarnos con player aca.
	private PlayerCharacter player;
	private List<EnemyCharacter> enemyList;
	private Character[][] grid = new Character[WIDTH][HEIGHT];

	public Map(){
		player = new PlayerCharacter(1);
		try {
			add(player);
		} catch (PositionOccupiedException e) {
			e.printStackTrace();
		}
	}
	public Map(PlayerCharacter player) {
		this(player, new ArrayList<EnemyCharacter>());
	}

	public Map(PlayerCharacter player, List<EnemyCharacter> enemyList) {
		this.player = player;
		this.enemyList = enemyList;
	}
	
	// Lo hice villerisimo...
	public void add(Character character) throws PositionOccupiedException {
		if (grid[character.getX()/CELL_SIZE][character.getY()/CELL_SIZE] != null) {
			throw new PositionOccupiedException(
					"La posicion [" + character.getX() + "]" + "[" + character.getY() + "]" + " esta ocupada, imposible a�adir " + character + ".");
		}
		grid[character.getX()/CELL_SIZE][character.getY()/CELL_SIZE] = character;
	}
	
	// cambiar luego;
	public void remove(int x, int y) {
		grid[x][y] = null;
	}

	public PlayerCharacter getPlayer(){
		return player;
	}
	
	public List<EnemyCharacter> getEnemies(){
		return enemyList;
	}
	/*
	 * Como implementar esto? Que devuelve? Es necesaria? public void
	 *
	 * public POSICION getLocation(Character character) { }
	 */
}
