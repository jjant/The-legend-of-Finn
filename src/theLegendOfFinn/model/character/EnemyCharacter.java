package theLegendOfFinn.model.character;

public abstract class EnemyCharacter extends Character {
	enum EnemyType{
		Warrior,
		Horse;
	}
	// Decidir como se van a guardar las propiedades de cada enemigo y donde. (aca o en la factory)
	public static final int WARRIOR_MAX_HP = 1;
	public static final int WARRIOR_ATTACK = 1;
	public static final int WARRIOR_VELOCITY = 1;
	
	public static final int HORSE_MAX_HP = 2;
	public static final int HORSE_ATTACK = 2;
	public static final int HORSE_VELOCITY = 2;
		
	 
	
	public EnemyCharacter(int x, int y, Direction direction, int velocity, int maxHP, int attack) {
		super(x, y, direction, velocity, maxHP, attack);
	}

	public void chasePlayer(int playerX, int playerY) {
		if (playerY > getY())
			tryToMove(Direction.UP);
		else if (playerY < getY())
			tryToMove(Direction.DOWN);
		if (playerX < getX())
			tryToMove(Direction.LEFT);
		else if (playerX > getX())
			tryToMove(Direction.RIGHT);
	}
}
