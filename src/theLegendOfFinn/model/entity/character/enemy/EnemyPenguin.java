package theLegendOfFinn.model.entity.character.enemy;

import theLegendOfFinn.model.utils.Position;

public class EnemyPenguin extends EnemyCharacter {
	private static final long serialVersionUID = 1L;

	// Penguin enemy default attributes.
	private static final int WARRIOR_MAX_HP = 1;
	private static final int WARRIOR_ATTACK = 1;
	private static final int WARRIOR_VELOCITY = 1;
	private static final int WARRIOR_HP_BOUNTY = 1;

	public EnemyPenguin(Position pos, Direction direction) {
		super(pos, direction, WARRIOR_MAX_HP, WARRIOR_ATTACK,
				WARRIOR_VELOCITY, WARRIOR_HP_BOUNTY);
	}
	
	public EnemyPenguin(Position pos) {
		this(pos, Direction.DOWN);
	}

}

