package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import theLegendOfFinn.model.entity.character.Boss;
import theLegendOfFinn.model.entity.character.EnemyCharacter;
import theLegendOfFinn.model.entity.character.EnemyPenguin;
import theLegendOfFinn.model.entity.character.EnemyDog;
import theLegendOfFinn.model.entity.character.EnemyDonut;
import theLegendOfFinn.model.entity.character.EnemyKing;


/**
 * Represents and stores the round in the game.
 *
 * @author rama
 *
 */
public class Round implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int BOSS_ROUND_NUMBER = 9;
	
	// Different types of rounds, or game modes.
	public static enum RoundType {
		NORMAL, BOSS, SURVIVAL;
	}
	
	private List<EnemyCharacter> enemies;
	// Array with the four corners of the map.
	private final Position[] positions = {Map.TOP_LEFT_CORNER, Map.TOP_RIGHT_CORNER, Map.BOTTOM_LEFT_CORNER, Map.BOTTOM_RIGHT_CORNER};
	
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
	 * @param roundNumber
	 * 					determines the number of enemies to be added.
	 */
	private void survivalRound(int roundNumber) {
		int index = 0;
		while (roundNumber >= 0) {
			int flipEnemy = ThreadLocalRandom.current().nextInt(0, 4);
			switch (flipEnemy) {
			case 0:
				this.enemies.add(new EnemyPenguin(new Position(positions[index % 4])));
				break;
			case 1:
				this.enemies.add(new EnemyDog(new Position(positions[index % 4])));
				break;
			case 2:
				this.enemies.add(new EnemyDonut(new Position(positions[index % 4])));
				break;
			case 3:
				this.enemies.add(new EnemyKing(new Position(positions[index % 4])));
				break;
			}
			index ++;
			roundNumber --;
		}
	}
	
	private int addTwoWarriors(int index) {
		this.enemies.add(new EnemyPenguin(new Position(positions[index % 4])));
		this.enemies.add(new EnemyPenguin(new Position(positions[(index + 1) % 4])));
		return index + 2;
	}
	private int addTwoDogs(int index) {
		this.enemies.add(new EnemyDog(new Position(positions[index % 4])));
		this.enemies.add(new EnemyDog(new Position(positions[(index + 1) % 4])));
		return index + 2;
	}
	private int addTwoDonuts(int index) {
		this.enemies.add(new EnemyDonut(new Position(positions[index % 4])));
		this.enemies.add(new EnemyDonut(new Position(positions[(index + 1) % 4])));
		return index + 2;
	}
	private int addTwoKings(int index) {
		this.enemies.add(new EnemyKing(new Position(positions[index % 4])));
		this.enemies.add(new EnemyKing(new Position(positions[(index + 1) % 4])));
		return index + 2;
	}
	
	private void addBoss(){
		enemies.add(new Boss());
	}
	
	/**
	 * Loads four enemies in each normal round increasing the difficulty
	 * until the boss round is reached.
	 * 
	 * @param roundNumber
	 * 					determines the difficulty of the round.
	 */
	private void normalRound(int roundNumber) {
		int index = 0;
		switch (roundNumber) {
		case 0:
			index = addTwoWarriors(index);
			index = addTwoWarriors(index);
			break;
		case 1:
			index = addTwoWarriors(index);
			index = addTwoDogs(index);
			break;
		case 2:
			index = addTwoDogs(index);
			index = addTwoDogs(index);
			break;
		case 3:
			index = addTwoDonuts(index);
			index = addTwoWarriors(index);
			break;
		case 4:
			index = addTwoDonuts(index);
			index = addTwoDogs(index);
			break;
		case 5:
			index = addTwoDonuts(index);
			index = addTwoDonuts(index);
			break;
		case 6:
			index = addTwoKings(index);
			index = addTwoDogs(index);
			break;
		case 7:
			index = addTwoKings(index);
			index = addTwoDonuts(index);
			break;
		case 8:
			index = addTwoKings(index);
			index = addTwoKings(index);
			break;
		case 9:
			addBoss();
			break;
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
	 * Generates a random sized batch of enemies of the round. This way, is
	 * easier to manage batches inside a unique round.
	 * 
	 * @return Array of enemies
	 */
	/*
	public List<EnemyCharacter> getEnemiesBatch() {
		if (enemies.size() == 0)
			return null;

		int randEnemiesSize = ThreadLocalRandom.current().nextInt(1, enemies.size() + 1);
		List<EnemyCharacter> enemiesBatch = new ArrayList<>();

		for (int i = randEnemiesSize - 1; i >= 0; i--) {
			enemiesBatch.add(enemies.get(i));
			enemies.remove(i);
		}

		return enemiesBatch;
	}
	*/
	
	/**
	 * Gets the list of enemies of the current Round.
	 * 
	 * @return list of enemies.
	 */
	public List<EnemyCharacter> getRoundEnemies(){
		return enemies;
	}
	
}
