package theLegendOfFinn.model.entity;

import theLegendOfFinn.model.Position;

public class Projectile extends MovingEntity {
	private static final long serialVersionUID = 1L;

	private static final int PROJECTILE_VELOCITY = 1;
	public Projectile(Position position, Direction direction) {
		super(position, direction, PROJECTILE_VELOCITY);
	}
	
	//hace cosas
	public void move() {

	}
}
