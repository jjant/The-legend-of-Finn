package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;
import java.util.concurrent.ThreadLocalRandom;

public abstract class EnemyCharacter extends Character {
	//Esto esta todo ok!
	public enum EnemyType{
		Warrior,
		Horse;
	}
	
	
	// Decidir como se van a guardar las propiedades de cada enemigo y donde. (aca o en la factory)
	//Probablemente sacar velocity.
	public static final int WARRIOR_MAX_HP = 1;
	public static final int WARRIOR_ATTACK = 1;
	public static final int WARRIOR_VELOCITY = 1;
	
	public static final int HORSE_MAX_HP = 2;
	public static final int HORSE_ATTACK = 2;
	public static final int HORSE_VELOCITY = 2;
		
	 
	public EnemyCharacter(Position pos, Direction direction, int velocity, int maxHP, int attack) {
		super(pos, direction, velocity, maxHP, attack);
	}
	
	/*
	public EnemyCharacter(int x, int y, Direction direction, int velocity, int maxHP, int attack) {
		super(x, y, direction, velocity, maxHP, attack);
	}
	*/

	/**
	 * author: Ramiro Olivera Fedi
	 * 
	 * @param playerX Position X for player
	 * @param playerY Position Y for the player
	 */
	// Maybe we should be passing a Position object instead of two ints.
	
	public void chasePlayer(Position pos) {
	//public void chasePlayer(int playerX, int playerY) {
		
		int playerX = pos.getX();
		int playerY = pos.getY();

		boolean flipDirection = ThreadLocalRandom.current().nextInt(0, 11) <= 5;
		
		// map Parameter added
		
		if (playerY > getY() && playerX > getX())
			tryToMove(flipDirection ? Direction.DOWN : Direction.RIGHT);
		else if (playerY == getY() && playerX > getX())
			tryToMove(Direction.RIGHT);
		else if (playerY == getY() && playerX < getX())
			tryToMove(Direction.LEFT);
		else if (playerY > getY() && playerX == getX())
			tryToMove(Direction.DOWN);
		else if (playerY < getY() && playerX == getX())
			tryToMove(Direction.UP);
		else if (playerY < getY() && playerX < getX())
			tryToMove(flipDirection ? Direction.UP : Direction.LEFT);
		else if (playerY < getY() && playerX > getX())
			tryToMove(flipDirection ? Direction.UP : Direction.RIGHT);
		else if (playerY > getY() && playerX < getX())
			tryToMove(flipDirection ? Direction.DOWN : Direction.LEFT);
		else {} // THIS HAPPENS ONLY IF ARE IN SAME POSITION. (NEVER SHOULD HAPPEN)
	}

}
