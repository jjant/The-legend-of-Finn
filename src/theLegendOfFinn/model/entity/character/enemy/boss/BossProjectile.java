package theLegendOfFinn.model.entity.character.enemy.boss;

import theLegendOfFinn.model.entity.ActingEntity;
import theLegendOfFinn.model.entity.Entity;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.utils.Position;

/**
 * Boss projectiles. Represent the attack elements of the boss.
 */
public class BossProjectile extends ActingEntity {
	private static final long serialVersionUID = 1L;
	
	// Projectile default attributes
	private static final int PROJECTILE_VELOCITY = 1;
	private static final int PROJECTILE_DAMAGE = 1;
	private static final int PROJECTILE_RANGE = 0;

	public BossProjectile(Position position, Direction direction) {
		super(position, direction, PROJECTILE_VELOCITY, PROJECTILE_DAMAGE);
	}

	/**
	 * Attacks an entity player. Returns true if and only if it was able to
	 * attack the entity.
	 */
	public boolean attack(Entity entity) {
		if (!(entity instanceof PlayerCharacter && closeEnough(entity, PROJECTILE_RANGE)))
			return false;

		PlayerCharacter player = (PlayerCharacter) entity;
		player.receiveAttack(this);
		return true;
	}

	/**
	 * Replaces the move method in ActingEntity.
	 * Increments the corresponding coordinate.
	 */
	public void move() {
		int yIncrement = 0, xIncrement = 0;

		long nowTime = System.currentTimeMillis();

		if (getTimer().moveTimePassed(nowTime)) {
			getTimer().updateLastMoveTime(nowTime);
			switch (direction) {
			case UP:
				yIncrement = -1;
				xIncrement = 0;
				break;
			case LEFT:
				yIncrement = 0;
				xIncrement = -1;
				break;
			case DOWN:
				yIncrement = 1;
				xIncrement = 0;
				break;
			case RIGHT:
				yIncrement = 0;
				xIncrement = 1;
				break;
			default:
				break;
			}
			getPosition().incPos(xIncrement, yIncrement);
		}
	}
}
