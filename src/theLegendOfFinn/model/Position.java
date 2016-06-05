package theLegendOfFinn.model;

import theLegendOfFinn.model.character.Character.Direction;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position toGridIndexes(Direction direction) {
		Position pos = null;
		if (direction == null) return null;
		
		switch (direction){
		case LEFT:
			pos = new Position(this.getX()/Map.CELL_SIZE - 1, this.getY()/Map.CELL_SIZE);
			break;
		case UP:
			pos = new Position(this.getX()/Map.CELL_SIZE, this.getY()/Map.CELL_SIZE - 1);
			break;
		case RIGHT:
			pos = new Position(this.getX()/Map.CELL_SIZE + 1, this.getY()/Map.CELL_SIZE);
			break;
		case DOWN:
			pos = new Position(this.getX()/Map.CELL_SIZE, this.getY()/Map.CELL_SIZE + 1);
			break;
		default:
			break;
		}
		
		if (pos.getX() < 0 || pos.getX() >= Map.WIDTH || pos.getY() < 0 || pos.getY() >= Map.HEIGHT) {
			return null;
		}
		
		return pos;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void incX(int increment) {
		x += increment;
	}
	
	public void incY(int increment) {
		y += increment;
	}
	
	public void incPos(int incrementX, int incrementY) {
		x += incrementX;
		y += incrementY;
	}
	
	public String toString(){
		return "(" + this.getX() + ", " + this.getY() + ")";
	}
}
