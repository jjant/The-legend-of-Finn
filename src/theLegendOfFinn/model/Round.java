package theLegendOfFinn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import theLegendOfFinn.model.character.EnemyCharacter;

public class Round {

	private List<EnemyCharacter> enemies;
	
	public Round(List<EnemyCharacter> enemies) {
		for(EnemyCharacter enemy: enemies) {
			this.enemies.add(enemy);
		}
	}
	
	public boolean enemiesLeft() {
		return enemies.size() > 0;
	}
	
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
