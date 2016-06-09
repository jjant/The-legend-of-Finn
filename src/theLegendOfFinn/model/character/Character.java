package theLegendOfFinn.model.character;

import theLegendOfFinn.model.CharacterGrid;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;

public class Character {
	public static enum Direction {
		UP, RIGHT, DOWN, LEFT;
	}

	public enum State {
		IDLE, MOVING, ATTACKING;
	}
	
	public static final int MOVE_COOLDOWN = 15;

	// está esto acá para diferenciar al renderizar, ver dónde va
	private int maxHP;
	private Position position;
	private int velocity;
	private int currentHP;

	protected State state = State.IDLE;

	private int attack;
	private long lastAttackTime;
	private long attackCooldown = 500; // 1/2 segundo

	private Direction direction;
	private long lastMoveTime;
	private long nowMoveTime;
	private int moveRemaining;

	public Character(Position pos, Direction direction, int maxHP, int attack, int velocity) {
		this.position = pos;
		this.direction = direction;
		this.velocity = velocity;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.attack = attack;
		lastMoveTime = 0;
	}

	public boolean isAlive() {
		return currentHP > 0;
	}

	public boolean isMoving() {
		return state == State.MOVING;
	}

	public static void test() {
		return;
	}

	public Position getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public int getAttack() {
		return attack;
	}

	public State getState() {
		return state;
	}

	protected void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	protected void setX(int x) {
		position.setX(x);
	}

	protected void setY(int y) {
		position.setY(y);
	}

	protected void setPosition(Position pos) {
		this.position = pos;
	}

	/**
	 * Tries to move the character to the specified direction. If movement is
	 * impossible, it does nothing.
	 * 
	 * @param direction
	 *            the direction towards the movement is desired.
	 * @param grid
	 *            the character grid.
	 */
	public void tryToMove(Direction direction, CharacterGrid grid) {
		Position destination;
		boolean canMove = true;

		if (state == State.MOVING)
			return;

		this.direction = direction;
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
		default:
			// should throw an exception, this is madness.
			destination = new Position(0, 0);
		}

		if (destination.getX() < 0 || destination.getX() >= Map.WIDTH * Map.CELL_SIZE || destination.getY() < 0
				|| destination.getY() >= Map.HEIGHT * Map.CELL_SIZE || !grid.isFreePosition(destination))
			canMove = false;

		if (canMove) {
			// if (canMove(direction)) {
			state = State.MOVING;
			moveRemaining = 32;
			lastMoveTime = System.currentTimeMillis();
			grid.occupyPosition(this, destination); // This should be done in
													// move. To not attack the
													// enemy
			grid.freePosition(this.getPosition()); // in an empty space. Maybe
													// when MoveRemaining == 10
													// i.e.
		}
	}
	// Hay que tener cuidado con el sleep(10), quizas habria que hacer
	// otro thread que maneje ticker, pero no estoy seguro de coomo se hace todo
	// eso.

	public void move() {
		int yIncrement = 0, xIncrement = 0;
		if (moveRemaining == 0) {
			if(state == State.MOVING)
				state = State.IDLE;
			return;
		}

		nowMoveTime = System.currentTimeMillis();
		//if (nowMoveTime - lastMoveTime >= 15 / getVelocity()) {
		if (nowMoveTime - lastMoveTime >= MOVE_COOLDOWN / getVelocity()) {
			lastMoveTime = nowMoveTime;
			moveRemaining--;

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
			position.incPos(xIncrement, yIncrement);
		}
	}


	public void attack(Character character) {
		long now = System.currentTimeMillis();
		if (now - lastAttackTime <= attackCooldown && state != State.IDLE) {
			return;
		}
		state = State.ATTACKING;
		lastAttackTime = System.currentTimeMillis();
		/*
		if (character == null || character.getPosition().getX() % Map.CELL_SIZE != 0
				|| character.getPosition().getY() % Map.CELL_SIZE != 0)
			return;
		*/
		if (character == null || !closeEnough(character))
			return;
		System.out.println(character);
		character.receiveAttack(this);
	}

	private void receiveAttack(Character character) {
		setCurrentHP(getCurrentHP() - character.getAttack());
	}

	//probando, sacar dsp.
	public void updateStatus() {
		long now = System.currentTimeMillis();
		if (state == State.ATTACKING && now - lastAttackTime >= attackCooldown)
			state = State.IDLE;
	}
	
	public boolean closeEnough(Character character) {
		if (getPosition().distanceX(character.getPosition()) == 0 
				&& getPosition().distanceY(character.getPosition()) >= Map.CELL_SIZE * 3 / 2)
			return false;
		if (getPosition().distanceX(character.getPosition()) == Map.CELL_SIZE 
				&& getPosition().distanceY(character.getPosition()) >= Map.CELL_SIZE / 2)
			return false;
		if (getPosition().distanceX(character.getPosition()) >= Map.CELL_SIZE * 3 / 2
				&& getPosition().distanceY(character.getPosition()) == 0)
			return false;
		if (getPosition().distanceX(character.getPosition()) >= Map.CELL_SIZE / 2 
				&& getPosition().distanceY(character.getPosition()) == Map.CELL_SIZE)
			return false;
		return true;
	}
}
