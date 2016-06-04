package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;

public class EnemyHorse extends EnemyCharacter {

	/*
	public EnemyHorse(int x, int y, Direction direction) {
		super(x, y, direction, EnemyCharacter.HORSE_VELOCITY, EnemyCharacter.HORSE_MAX_HP,
				EnemyCharacter.HORSE_ATTACK);
	}

	public EnemyHorse(int x, int y) {
		this(x, y, Direction.DOWN);
	}
	*/
	
	public EnemyHorse(Position pos, Direction direction) {
		super(pos, direction, EnemyCharacter.HORSE_VELOCITY, EnemyCharacter.HORSE_MAX_HP,
				EnemyCharacter.HORSE_ATTACK);
	}

	public EnemyHorse(Position pos) {
		this(pos, Direction.DOWN);
	}
}
