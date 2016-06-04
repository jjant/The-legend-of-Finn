package theLegendOfFinn.model;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
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
}
