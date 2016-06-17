package theLegendOfFinn.model.entity.character.enemy;

import theLegendOfFinn.model.utils.Position;

public class EnemyDonut extends EnemyCharacter {
	private static final long serialVersionUID = 1L;
	
	// Donut enemy default attributes
	private static final int DONUT_MAX_HP = 3;
	private static final int DONUT_ATTACK = 2;
	private static final int DONUT_VELOCITY = 1;
	private static final int DONUT_HP_BOUNTY = 2;

	
	public EnemyDonut(Position pos, Direction direction) {
		super(pos, direction, DONUT_MAX_HP, DONUT_ATTACK,
				DONUT_VELOCITY, DONUT_HP_BOUNTY);
	}
	
	public EnemyDonut(Position pos) {
		this(pos, Direction.DOWN);
	}


}
