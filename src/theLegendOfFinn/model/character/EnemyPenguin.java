package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;

public class EnemyPenguin extends EnemyCharacter {
	private static final long serialVersionUID = 1L;


	public EnemyPenguin(Position pos, Direction direction) {
		super(pos, direction, EnemyCharacter.WARRIOR_MAX_HP, EnemyCharacter.WARRIOR_ATTACK,
				EnemyCharacter.WARRIOR_VELOCITY, EnemyCharacter.WARRIOR_HP_BOUNTY);
	}
	
	public EnemyPenguin(Position pos) {
		this(pos, Direction.DOWN);
	}

}

