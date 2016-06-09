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

	private List<EnemyCharacter> enemies;
	
	public Round(List<EnemyCharacter> enemies) {
		this.enemies = new ArrayList<>();
		for (EnemyCharacter enemy : enemies) {
			this.enemies.add(enemy);
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
