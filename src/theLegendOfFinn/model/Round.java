  package theLegendOfFinn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import theLegendOfFinn.model.character.EnemyCharacter;
/**
 * Manages rounds in the game
 *
 * @author rama
 *
 */
public class Round {

	private List<EnemyCharacter> enemies;
	
	public Round(List<EnemyCharacter> enemies) {
		for(EnemyCharacter enemy: enemies) {
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
	 * Generates a random sized batch of enemies of the round.
	 * This way, is easier to manage batches inside a unique round.
	 * 
	 * @return Array of enemies
	 */
	public List<EnemyCharacter> getEnemiesBatch() {
		if (enemies.size() == 0) return null;
		
		int randEnemiesSize = ThreadLocalRandom.current().nextInt(1, enemies.size() + 1);
		List<EnemyCharacter> enemiesBatch = new ArrayList<>();
		
		for (int i = randEnemiesSize - 1; i >= 0 ; i--) {
			enemiesBatch.add(enemies.get(i));
			enemies.remove(i);
		}

		return enemiesBatch;
	}
}
