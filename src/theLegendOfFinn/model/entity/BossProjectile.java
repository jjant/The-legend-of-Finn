package theLegendOfFinn.model.entity;

import theLegendOfFinn.model.Grid;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.Entity.Direction;

public class BossProjectile extends MovingEntity {
	private static final long serialVersionUID = 1L;

	private static final int PROJECTILE_VELOCITY = 1;

	public BossProjectile(Position position, Direction direction) {
		super(position, direction, PROJECTILE_VELOCITY);
	}

	public void tryToMove(Direction direction, Grid grid) {
		Position destination = null;

		switch (direction) {
		case LEFT:
			destination = new Position(getPosition().getX() - Map.CELL_SIZE, getPosition().getY());
			break;
		case RIGHT:
			destination = new Position(getPosition().getX() + Map.CELL_SIZE, getPosition().getY());
			break;
		case UP:
			destination = new Position(getPosition().getX(), getPosition().getY() - Map.CELL_SIZE);
			break;
		case DOWN:
			destination = new Position(getPosition().getX(), getPosition().getY() + Map.CELL_SIZE);
			break;
		}
		/*
		 * if (!grid.isFreePosition(destination)) { Entity entity =
		 * grid.getEntity(destination); if (entity instanceof Character) {
		 * Character character = (Character) grid.getEntity(destination);
		 * character.receiveAttack(null); } }
		 */

		moveRemaining = Map.CELL_SIZE;
		// lastMoveTime = System.currentTimeMillis();
	}

	public void move() {
		int yIncrement = 0, xIncrement = 0;

		long nowTime = System.currentTimeMillis();
		System.out.println("Now: " + nowTime);
		System.out.println("Last time: " + getTimer().getLastMoveTime());
		System.out.println("Now - lastTime: " + (nowTime - getTimer().getLastMoveTime()));
		
		if (getTimer().moveTimePassed(nowTime)) {
			getTimer().updateLastMoveTime(nowTime);
			System.out.println("me estoy moviendo");
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
