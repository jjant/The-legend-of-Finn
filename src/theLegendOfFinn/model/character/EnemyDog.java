package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;

public class EnemyDog extends EnemyCharacter {
	private static final long serialVersionUID = 1L;
	/*
	public EnemyHorse(int x, int y, Direction direction) {
		super(x, y, direction, EnemyCharacter.HORSE_VELOCITY, EnemyCharacter.HORSE_MAX_HP,
				EnemyCharacter.HORSE_ATTACK);
	}

	public EnemyHorse(int x, int y) {
		this(x, y, Direction.DOWN);
	}
	*/
	
	

	public EnemyDog(Position pos, Direction direction) {
		super(pos, direction, EnemyCharacter.DOG_MAX_HP, EnemyCharacter.DOG_ATTACK,
				EnemyCharacter.DOG_VELOCITY, EnemyCharacter.DOG_HP_BOUNTY);
	}

	public EnemyDog(Position pos) {
		this(pos, Direction.DOWN);
	}
}
