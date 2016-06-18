package theLegendOfFinn.model.entity.character.enemy;

import theLegendOfFinn.model.utils.Position;

public class EnemyKing extends EnemyCharacter {
	private static final long serialVersionUID = 1L;

	// King enemy default attributes
	private static final int KING_MAX_HP = 3;
	private static final int KING_ATTACK = 2;
	private static final int KING_VELOCITY = 2;
	private static final int KING_HP_BOUNTY = 2;

	
	public EnemyKing(Position pos, Direction direction) {
		super(pos, direction, KING_MAX_HP, KING_ATTACK,
				KING_VELOCITY, KING_HP_BOUNTY);
	}
	
	public EnemyKing(Position pos) {
		this(pos, Direction.DOWN);
	}

}
