package theLegendOfFinn.model.entity.character.enemy;

import theLegendOfFinn.model.utils.Position;

public class EnemyDog extends EnemyCharacter {
	private static final long serialVersionUID = 1L;
	
	// Dog enemy default attributes
	private static final int DOG_MAX_HP = 2;
	private static final int DOG_ATTACK = 2;
	private static final int DOG_VELOCITY = 1;
	private static final int DOG_HP_BOUNTY = 1;
	
	public EnemyDog(Position pos, Direction direction) {
		super(pos, direction, DOG_MAX_HP, DOG_ATTACK,
				DOG_VELOCITY, DOG_HP_BOUNTY);
	}

	public EnemyDog(Position pos) {
		this(pos, Direction.DOWN);
	}
}
