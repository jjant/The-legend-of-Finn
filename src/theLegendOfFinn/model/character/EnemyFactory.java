package theLegendOfFinn.model.character;

import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.model.character.EnemyCharacter.EnemyType;

public class EnemyFactory {
	
	public static final int WARRIOR = 1;
	public static final int HORSE = 2;

	public EnemyCharacter getEnemy(EnemyType type, int x, int y, Direction direction) {
		switch (type) {
		case Warrior:
			return new EnemyWarrior(x, y, direction);
		case Horse:
			return new EnemyHorse(x, y, direction);
		default:
			throw new IllegalArgumentException();
		}
	}
	
}
