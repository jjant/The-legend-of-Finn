package theLegendOfFinn.model.entity.character.enemy;

import theLegendOfFinn.model.utils.Position;

public class EnemyKing extends EnemyCharacter {
	private static final long serialVersionUID = 1L;
	
	public EnemyKing(Position pos, Direction direction) {
		super(pos, direction, EnemyCharacter.KING_MAX_HP, EnemyCharacter.KING_ATTACK,
				EnemyCharacter.KING_VELOCITY, EnemyCharacter.KING_HP_BOUNTY);
	}
	
	public EnemyKing(Position pos) {
		this(pos, Direction.DOWN);
	}

}
