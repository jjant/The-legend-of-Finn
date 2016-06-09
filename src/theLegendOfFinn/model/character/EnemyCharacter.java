package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.CharacterGrid;
import java.util.concurrent.ThreadLocalRandom;

public abstract class EnemyCharacter extends Character {
	// Decidir como se van a guardar las propiedades de cada enemigo y donde. (aca o en la factory)
	//Probablemente sacar velocity.
	public static final int WARRIOR_MAX_HP = 1;
	public static final int WARRIOR_ATTACK = 1;
	public static final int WARRIOR_VELOCITY = 1;
	
	public static final int DOG_MAX_HP = 2;
	public static final int DOG_ATTACK = 2;
	public static final int DOG_VELOCITY = 1;
		
	 
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
	// Ready, Something else boss?
	// When we finish, we might take into account multiple players
	// capabilities.
	public void chasePlayer(Position pos, CharacterGrid grid) {
	//public void chasePlayer(int playerX, int playerY) {
		
		int playerX = pos.getX();
		int playerY = pos.getY();

		boolean flipDirection = ThreadLocalRandom.current().nextInt(0, 11) <= 5;
		
		if (playerY > getPosition().getY() && playerX > getPosition().getX())
			tryToMove(flipDirection ? Direction.DOWN : Direction.RIGHT, grid);
		else if (playerY == getPosition().getY() && playerX > getPosition().getX())
			tryToMove(Direction.RIGHT, grid);
		else if (playerY == getPosition().getY() && playerX < getPosition().getX())
			tryToMove(Direction.LEFT, grid);
		else if (playerY > getPosition().getY() && playerX == getPosition().getX())
			tryToMove(Direction.DOWN, grid);
		else if (playerY < getPosition().getY() && playerX == getPosition().getX())
			tryToMove(Direction.UP, grid);
		else if (playerY < getPosition().getY() && playerX < getPosition().getX())
			tryToMove(flipDirection ? Direction.UP : Direction.LEFT, grid);
		else if (playerY < getPosition().getY() && playerX > getPosition().getX())
			tryToMove(flipDirection ? Direction.UP : Direction.RIGHT, grid);
		else if (playerY > getPosition().getY() && playerX < getPosition().getX())
			tryToMove(flipDirection ? Direction.DOWN : Direction.LEFT, grid);
		else {} // THIS HAPPENS ONLY IF ARE IN SAME POSITION. (NEVER SHOULD HAPPEN)
		
		/*
		if (playerY > getPosition().getY() && playerX > getPosition().getX())
			tryToMove(flipDirection ? Direction.DOWN : Direction.RIGHT);
		else if (playerY == getPosition().getY() && playerX > getPosition().getX())
			tryToMove(Direction.RIGHT);
		else if (playerY == getPosition().getY() && playerX < getPosition().getX())
			tryToMove(Direction.LEFT);
		else if (playerY > getPosition().getY() && playerX == getPosition().getX())
			tryToMove(Direction.DOWN);
		else if (playerY < getPosition().getY() && playerX == getPosition().getX())
			tryToMove(Direction.UP);
		else if (playerY < getPosition().getY() && playerX < getPosition().getX())
			tryToMove(flipDirection ? Direction.UP : Direction.LEFT);
		else if (playerY < getPosition().getY() && playerX > getPosition().getX())
			tryToMove(flipDirection ? Direction.UP : Direction.RIGHT);
		else if (playerY > getPosition().getY() && playerX < getPosition().getX())
			tryToMove(flipDirection ? Direction.DOWN : Direction.LEFT);
		else {} // THIS HAPPENS ONLY IF ARE IN SAME POSITION. (NEVER SHOULD HAPPEN)
		*/
	}
	//probando
	public void attackNearbyPlayer(PlayerCharacter player){
		if(getPosition().isNearby(player.getPosition()) && getState() == State.IDLE ){
			attack(player);
		}
	}
}
