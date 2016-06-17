package theLegendOfFinn.model.entity.character.enemy;

import java.util.concurrent.ThreadLocalRandom;

import theLegendOfFinn.model.entity.character.enemy.boss.Boss;
import theLegendOfFinn.model.utils.Position;

public class EnemyFactory {
	public enum EnemyType {
		PENGUIN, DOG, DONUT, KING, BOSS;
	}
	
	public EnemyCharacter getEnemy(EnemyType enemyType) {
		Position defaultPosition = new Position(0, 0);
		switch(enemyType) {
		case PENGUIN:
			return new EnemyPenguin(defaultPosition);
		case DOG:
			return new EnemyDog(defaultPosition);
		case DONUT:
			return new EnemyDonut(defaultPosition);
		case KING:
			return new EnemyKing(defaultPosition);
		case BOSS:
			return new Boss();
		default:
			return null;
		}
	}
	
	public EnemyCharacter getRandomEnemy() {
		int randomIndex = ThreadLocalRandom.current().nextInt(0, 4);
		switch (randomIndex) {
		case 0:
			return getEnemy(EnemyType.PENGUIN);
		case 1:
			return getEnemy(EnemyType.DOG);
		case 2:
			return getEnemy(EnemyType.DONUT);
		case 3:
			return getEnemy(EnemyType.KING);
		default:
			// Never should happen
			return null;
		}
	}
}
