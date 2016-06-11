package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;

public class EnemyDonut extends EnemyCharacter {
	private static final long serialVersionUID = 1L;
	
	public EnemyDonut(Position pos, Direction direction) {
		super(pos, direction, EnemyCharacter.DONUT_MAX_HP, EnemyCharacter.DONUT_ATTACK,
				EnemyCharacter.DONUT_VELOCITY, EnemyCharacter.DONUT_HP_BOUNTY);
	}
	
	public EnemyDonut(Position pos) {
		this(pos, Direction.DOWN);
	}


}
