package theLegendOfFinn.view.menu;

/**
 * Represent an option for the flow of the game
 */
public class MenuOption {
	// Attributes of the option
	private final int x;
	private final int y;
	private final int width;
	private final int height;

	public MenuOption(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the x coordinate
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y coordinate
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the width of the option
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height of the option
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
}
