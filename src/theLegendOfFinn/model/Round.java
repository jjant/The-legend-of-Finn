package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.EnemyDog;
import theLegendOfFinn.model.character.EnemyWarrior;

/**
 * Manages rounds in the game
 *
 * @author rama
 *
 */
public class Round implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private List<EnemyCharacter> enemies;
	private final Position[] positions = {Map.TOP_LEFT_CORNER, Map.TOP_RIGHT_CORNER, Map.BOTTOM_LEFT_CORNER, Map.BOTTOM_RIGHT_CORNER};
	private static final int TOTAL_ENEMIES = 4;
	
	public Round(List<EnemyCharacter> enemies) {
		this.enemies = new ArrayList<>();
		for (EnemyCharacter enemy : enemies) {
			this.enemies.add(enemy);
		}
	}

	public Round(int difficulty) {
		//Should throw an exception if difficulty is 0 or negative
		this.enemies = new ArrayList<>();
		int index = 0;
		
		while (difficulty >= 0) {
			this.enemies.add(new EnemyDog(new Position(positions[index%4])));
			index ++;
			difficulty --;
		}
		
		while (index < TOTAL_ENEMIES) {
			this.enemies.add(new EnemyWarrior(new Position(positions[index])));
			index ++;
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
	public static Round round1(){
		List<EnemyCharacter> enemies = new ArrayList<>();
		enemies.add(new EnemyDog(new Position(0, 0)));
		enemies.add(new EnemyWarrior(new Position(32+320, 0)));
		Round round1 = new Round(enemies);
		return round1;
	}
	
	public static Round round2(){
		List<EnemyCharacter> enemies = new ArrayList<>();
		for(int i = 0; i < 6; i++)
			enemies.add(new EnemyWarrior(new Position(32* i, 32 * i)));
		Round round2 = new Round(enemies);
	
		return round2;
	}
	
	//for debugging
	public static Round emptyRound(){
		List<EnemyCharacter> enemies = new ArrayList<>();
		Round emptyRound = new Round(enemies);
		return emptyRound;
	}
}
