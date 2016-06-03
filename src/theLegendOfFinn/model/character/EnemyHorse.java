package theLegendOfFinn.model.character;


public class EnemyHorse extends EnemyCharacter {

	public EnemyHorse(int x, int y, Direction direction) {
		super(x, y, direction, EnemyCharacter.HORSE_VELOCITY, EnemyCharacter.HORSE_MAX_HP,
				EnemyCharacter.HORSE_ATTACK);
	}

	public EnemyHorse(int x, int y) {
		this(x, y, Direction.DOWN);
	}

}
