package theLegendOfFinn.model.gameData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import theLegendOfFinn.model.entity.character.enemy.EnemyCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyFactory;
import theLegendOfFinn.model.entity.character.enemy.EnemyFactory.EnemyType;
import theLegendOfFinn.model.utils.Position;


/**
 * Represents and stores game's rounds.
 */
public class Round implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int BOSS_ROUND_NUMBER = 9;
	private List<EnemyCharacter> enemies;
	
	// Different types of rounds, or game modes.
	public static enum RoundType {
		NORMAL, BOSS, SURVIVAL;
	}
	
	public Round(RoundType roundType) {
		this(roundType, 0);
	}
	
	public Round(RoundType roundType, int roundNumber) {
		this.enemies = new ArrayList<>();
		switch (roundType) {
		case NORMAL:
			normalRound(roundNumber);
			break;
		case BOSS:
			normalRound(BOSS_ROUND_NUMBER);
			break;
		case SURVIVAL:
			survivalRound(roundNumber);
			break;
		}
	}
	
	/**
	 * Sets the enemies of each round randomly in the corners of the map.
	 * 
	 * @param roundNumber determines the number of enemies to be added.
	 */
	private void survivalRound(int roundNumber) {
		int index = 0;
		EnemyFactory enemyFactory = new EnemyFactory();
		while (roundNumber-- >= 0) addEnemy(index++, enemyFactory.getRandomEnemy());
	}
	
	/**
	 * Adds an enemy in a border position given by the index.
	 * Positions are occupied first on every corner, then on the successive positions,
	 * to give a fairly distributed grid.
	 * 
	 * @param index index of the position to add.
	 * @param enemy Enemy to add
	 * @return next position index.
	 */
	private int addEnemy(int index, EnemyCharacter enemy) {
		Position pos;
		if (index % 4 == 0) pos = new Position((index - 3 * index / 4) * Map.CELL_SIZE, 0);
		else if (index % 4 == 1) pos = new Position((Map.WIDTH - 1) * Map.CELL_SIZE, (Map.HEIGHT - 1 - index / 4) * Map.CELL_SIZE);
		else if (index % 4 == 2) pos = new Position((index - 2 * index / 4) * Map.CELL_SIZE, (Map.HEIGHT - 1) * Map.CELL_SIZE);
		else pos = new Position(0, Map.CELL_SIZE + (index - 3 * index / 4) * Map.CELL_SIZE);
		enemy.setPosition(pos);
		this.enemies.add(enemy);
		return index + 1;
	}
	
	/**
	 * Adds n enemies in a given position.
	 * 
	 * @param index index of the position to add.
	 * @param enemyType type of the enemy to add.
	 * @param quantity quantity of the enemies to add.
	 * @return next position index.
	 */
	private int addEnemy(int index, EnemyType enemyType, int quantity) {
		EnemyFactory enemyFactory = new EnemyFactory();
		for (int i = index; i < index + quantity; i++ ) addEnemy(i, enemyFactory.getEnemy(enemyType));
		return index + quantity;
	}
	
	/**
	 * Adds n enemies from the first position.
	 * 
	 * @param enemyType type of the enemy to add.
	 * @param quantity quantity of the enemies to add.
	 * @return next position index.
	 */
	private int addEnemy(EnemyType enemyType, int quantity) {
		return addEnemy(0, enemyType, quantity);
	}
	
	/**
	 * Loads four enemies in each normal round increasing the difficulty
	 * until the boss round is reached.
	 * 
	 * @param roundNumber determines the difficulty of the round.
	 */
	private void normalRound(int roundNumber) {
		switch (roundNumber) {
		case 0:
			addEnemy(EnemyFactory.EnemyType.PENGUIN, 4);
			break;
		case 1:
			addEnemy(addEnemy(
					EnemyFactory.EnemyType.PENGUIN, 2),
					EnemyFactory.EnemyType.DOG, 2);
			break;
		case 2:
			addEnemy(EnemyFactory.EnemyType.DOG, 4);
			break;
		case 3:
			addEnemy(addEnemy(
					EnemyFactory.EnemyType.PENGUIN, 2),
					EnemyFactory.EnemyType.DONUT, 2);
			break;
		case 4:
			addEnemy(addEnemy(
					EnemyFactory.EnemyType.DOG, 2),
					EnemyFactory.EnemyType.DONUT, 2);
			break;
		case 5:
			addEnemy(EnemyFactory.EnemyType.DONUT, 4);
			break;
		case 6:
			addEnemy(addEnemy(
					EnemyFactory.EnemyType.KING, 2),
					EnemyFactory.EnemyType.DOG, 2);
			break;
		case 7:
			addEnemy(addEnemy(
					EnemyFactory.EnemyType.KING, 2),
					EnemyFactory.EnemyType.DONUT, 2);
			break;
		case 8:
			addEnemy(EnemyFactory.EnemyType.KING, 4);
			break;
		case 9:
			addEnemy(EnemyFactory.EnemyType.BOSS, 1);
		}
	}
	
	/**
	 * Returns true if there are enemies left in the round.
	 * 
	 * @return Boolean
	 */
	public boolean enemiesLeft() {
		return enemies.size() > 0;
	}
	
	/**
	 * Gets the list of enemies of the current Round.
	 * 
	 * @return list of enemies.
	 */
	public List<EnemyCharacter> getRoundEnemies(){
		return enemies;
	}
	
}
