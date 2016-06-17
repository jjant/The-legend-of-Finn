package theLegendOfFinn.model.entity.character.enemy;

import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.gameData.Grid;
import theLegendOfFinn.model.utils.Position;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Parent class of all the enemies.
 * 
 */
public abstract class EnemyCharacter extends Character {
	private static final long serialVersionUID = 1L;

	// Enemies' attributes.
	public static final int WARRIOR_MAX_HP = 1;
	public static final int WARRIOR_ATTACK = 1;
	public static final int WARRIOR_VELOCITY = 1;
	public static final int WARRIOR_HP_BOUNTY = 1;
	
	public static final int DOG_MAX_HP = 2;
	public static final int DOG_ATTACK = 2;
	public static final int DOG_VELOCITY = 1;
	public static final int DOG_HP_BOUNTY = 1;
	
	public static final int DONUT_MAX_HP = 3;
	public static final int DONUT_ATTACK = 2;
	public static final int DONUT_VELOCITY = 1;
	public static final int DONUT_HP_BOUNTY = 2;
	
	public static final int KING_MAX_HP = 3;
	public static final int KING_ATTACK = 2;
	public static final int KING_VELOCITY = 2;
	public static final int KING_HP_BOUNTY = 2;

	// Amount of hp the enemy restores to the player when killed.
	private int hpBounty = 1;

	public EnemyCharacter(Position pos, Direction direction, int velocity, int maxHP, int attack, int hpBounty) {
		super(pos, direction, velocity, maxHP, attack);
		this.hpBounty = hpBounty;
	}

	public EnemyCharacter(Position pos, int velocity, int maxHP, int attack, int hpBounty) {
		super(pos, Direction.DOWN, velocity, maxHP, attack);
		this.hpBounty = hpBounty;
	}

	/**
	 * Sets the direction towards the player's position and tries to move forward.
	 * 
	 * @param playerPosition
	 * @param grid
	 */
	public void chasePlayer(Position playerPosition, Grid grid) {
		if (playerPosition == null || grid == null) return;
		
		int playerX = playerPosition.getX();
		int playerY = playerPosition.getY();
		int enemyX = getPosition().getX();
		int enemyY = getPosition().getY();
		
		Direction directionX = null;
		Direction directionY = null;

		boolean flipDirection = ThreadLocalRandom.current().nextInt(0, 11) <= 5;
		
		directionY = playerY > enemyY ? Direction.DOWN : Direction.UP;
		directionX = playerX > enemyX ? Direction.RIGHT : Direction.LEFT;
		
		if (playerX == enemyX) tryToMove(directionY, grid);
		else if (playerY == enemyY) tryToMove(directionX, grid);
		else tryToMove(flipDirection ? directionX : directionY, grid);
	}

	/**
	 * Checks if the player is next to the enemy and if so, attacks him.
	 * 
	 * @param player
	 */
	public boolean attack(PlayerCharacter player) {
		if (player != null && getPosition().isNearby(player.getPosition()) && getState() == IDLE) {
			return super.attack(player);
		}
		return false;
	}

	public int getHPBounty() {
		return hpBounty;
	}

}
