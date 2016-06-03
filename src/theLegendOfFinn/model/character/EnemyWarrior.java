package theLegendOfFinn.model.character;

public class EnemyWarrior extends EnemyCharacter {

	public EnemyWarrior(int x, int y, Direction direction) {
		super(x, y, direction, EnemyCharacter.WARRIOR_VELOCITY, EnemyCharacter.WARRIOR_MAX_HP,
				EnemyCharacter.WARRIOR_ATTACK);
	}
	public EnemyWarrior(int x, int y) {
		this(x, y, Direction.DOWN);
	}

}
