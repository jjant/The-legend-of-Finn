package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.CharacterGrid;
import java.util.concurrent.ThreadLocalRandom;

public abstract class EnemyCharacter extends Character {
	private static final long serialVersionUID = 1L;

	public static final int WARRIOR_MAX_HP = 1;
	public static final int WARRIOR_ATTACK = 1;
	public static final int WARRIOR_VELOCITY = 1;
	public static final int WARRIOR_HP_BOUNTY = 1;

	public static final int DOG_MAX_HP = 2;
	public static final int DOG_ATTACK = 2;
	public static final int DOG_VELOCITY = 1;
	public static final int DOG_HP_BOUNTY = 2;
	
	public static final int DONUT_MAX_HP = 3;
	public static final int DONUT_ATTACK = 2;
	public static final int DONUT_VELOCITY = 1;
	public static final int DONUT_HP_BOUNTY = 2;

	public int hpBounty = 1;

	public EnemyCharacter(Position pos, Direction direction, int velocity, int maxHP, int attack, int hpBounty) {
		super(pos, direction, velocity, maxHP, attack);
		this.hpBounty = hpBounty;
	}

	public EnemyCharacter(Position pos, int velocity, int maxHP, int attack, int hpBounty) {
		super(pos, Direction.DOWN, velocity, maxHP, attack);
		this.hpBounty = hpBounty;
	}

	/*
	 * public EnemyCharacter(int x, int y, Direction direction, int velocity,
	 * int maxHP, int attack) { super(x, y, direction, velocity, maxHP, attack);
	 * }
	 */

	/**
	 * author: Ramiro Olivera Fedi
	 * 
	 * @param playerX
	 *            Position X for player
	 * @param playerY
	 *            Position Y for the player
	 */
	// Maybe we should be passing a Position object instead of two ints.
	// Ready, Something else boss?
	// When we finish, we might take into account multiple players
	// capabilities.
	public void chasePlayer(Position pos, CharacterGrid grid) {
		// public void chasePlayer(int playerX, int playerY) {

		int playerX = pos.getX();
		int playerY = pos.getY();
		
		Character.Direction directionX = null;
		Character.Direction directionY = null;

		boolean flipDirection = ThreadLocalRandom.current().nextInt(0, 11) <= 5;
		if (playerY > getPosition().getY())
			directionY = Character.Direction.DOWN;
		else if (playerY < getPosition().getY())
			directionY = Character.Direction.UP;
		if (playerX > getPosition().getX())
			directionX = Character.Direction.RIGHT;
		else if (playerX < getPosition().getX())
			directionX = Character.Direction.LEFT;
		if (directionX == null)
			tryToMove(directionY, grid);
		else if (directionY == null)
			tryToMove(directionX, grid);
		else
			tryToMove(flipDirection ? directionX : directionY, grid);
		
		/*
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
		else {
		} // THIS HAPPENS ONLY IF ARE IN SAME POSITION. (NEVER SHOULD HAPPEN)
		*/
		
		/*
		 * if (playerY > getPosition().getY() && playerX > getPosition().getX())
		 * tryToMove(flipDirection ? Direction.DOWN : Direction.RIGHT); else if
		 * (playerY == getPosition().getY() && playerX > getPosition().getX())
		 * tryToMove(Direction.RIGHT); else if (playerY == getPosition().getY()
		 * && playerX < getPosition().getX()) tryToMove(Direction.LEFT); else if
		 * (playerY > getPosition().getY() && playerX == getPosition().getX())
		 * tryToMove(Direction.DOWN); else if (playerY < getPosition().getY() &&
		 * playerX == getPosition().getX()) tryToMove(Direction.UP); else if
		 * (playerY < getPosition().getY() && playerX < getPosition().getX())
		 * tryToMove(flipDirection ? Direction.UP : Direction.LEFT); else if
		 * (playerY < getPosition().getY() && playerX > getPosition().getX())
		 * tryToMove(flipDirection ? Direction.UP : Direction.RIGHT); else if
		 * (playerY > getPosition().getY() && playerX < getPosition().getX())
		 * tryToMove(flipDirection ? Direction.DOWN : Direction.LEFT); else {}
		 * // THIS HAPPENS ONLY IF ARE IN SAME POSITION. (NEVER SHOULD HAPPEN)
		 */
	}

	// probando
	public void attackNearbyPlayer(PlayerCharacter player) {
		if (getPosition().isNearby(player.getPosition()) && getState() == State.IDLE) {
			attack(player);
		}
	}

	public int getHPBounty() {
		return hpBounty;
	}

}
