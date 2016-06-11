package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.EnemyDog;
import theLegendOfFinn.model.character.EnemyPenguin;

/**
 * Represents and stores the round in the game.
 *
 * @author rama
 *
 */
public class Round implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static enum RoundTypes {
		NORMAL, BOSS, SURVIVAL;
	}
	
	private List<EnemyCharacter> enemies;
	private final Position[] positions = {Map.TOP_LEFT_CORNER, Map.TOP_RIGHT_CORNER, Map.BOTTOM_LEFT_CORNER, Map.BOTTOM_RIGHT_CORNER};
	//private static final int NORMAL_ROUND_ENEMIES = 4;
	
	/*
	public Round(List<EnemyCharacter> enemies) {
		this.enemies = new ArrayList<>();
		for (EnemyCharacter enemy : enemies) {
			this.enemies.add(enemy);
		}
	}
	*/
	
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
	private int addTwoThirds(int index) {
		/*
		this.enemies.add(new EnemyThird(new Position(positions[index % 4])));
		this.enemies.add(new EnemyThird(new Position(positions[(index + 1) % 4])));
		*/
		return index + 2;
	}
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
			index = addTwoThirds(index);
			index = addTwoWarriors(index);
			break;
		case 4:
			index = addTwoThirds(index);
			index = addTwoDogs(index);
			break;
		case 5:
			index = addTwoThirds(index);
			index = addTwoThirds(index);
			break;
		default:
			break;
		}
	}
	
	private void survivalRound(int roundNumber) {
		int index = 0;
		while (roundNumber >= 0) {
			boolean flipEnemy = ThreadLocalRandom.current().nextInt(0, 11) <= 5;
			this.enemies.add(flipEnemy ? new EnemyPenguin(new Position(positions[index%4])) : new EnemyDog(new Position(positions[index%4])));
			index ++;
			roundNumber --;
		}
	}
	
	public Round(RoundTypes roundType, int roundNumber) {
		this.enemies = new ArrayList<>();
		switch (roundType) {
		case NORMAL:
			this.normalRound(roundNumber);
			break;
		case BOSS:
			//this.enemies.add(new EnemyBoss(new Position(Map.TOP_LEFT_CORNER)));
			break;
		case SURVIVAL:
			this.survivalRound(roundNumber);
			break;
		default:
			// should start survival i guess.
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

	// re villero, pero bueno, para probar
	public List<EnemyCharacter> getEnemies(){
		return enemies;
	}
	
	/*
	public static Round round1(){
		List<EnemyCharacter> enemies = new ArrayList<>();
		enemies.add(new EnemyDog(new Position(0, 0)));
		enemies.add(new EnemyWarrior(new Position(32+320, 0)));
		Round round1 = new Round(enemies);
		return round1;
	}
	*/
	
	/*
	public static Round round2(){
		List<EnemyCharacter> enemies = new ArrayList<>();
		for(int i = 0; i < 6; i++)
			enemies.add(new EnemyWarrior(new Position(32* i, 32 * i)));
		Round round2 = new Round(enemies);
	
		return round2;
	}
	*/
	
	/*
	//for debugging
	public static Round emptyRound(){
		List<EnemyCharacter> enemies = new ArrayList<>();
		Round emptyRound = new Round(enemies);
		return emptyRound;
	}
	*/
}
