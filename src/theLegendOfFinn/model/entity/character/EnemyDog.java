package theLegendOfFinn.model.entity.character;

import theLegendOfFinn.model.Position;

public class EnemyDog extends EnemyCharacter {
	private static final long serialVersionUID = 1L;
	
	public EnemyDog(Position pos, Direction direction) {
		super(pos, direction, EnemyCharacter.DOG_MAX_HP, EnemyCharacter.DOG_ATTACK,
				EnemyCharacter.DOG_VELOCITY, EnemyCharacter.DOG_HP_BOUNTY);
	}

	public EnemyDog(Position pos) {
		this(pos, Direction.DOWN);
	}
}
