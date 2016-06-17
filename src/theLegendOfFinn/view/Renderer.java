package theLegendOfFinn.view;

import java.awt.Graphics;

/**
 * Renderer interface
 */
public interface Renderer {
	public static final int CELL_SIZE = 32;

	public void render(Graphics g);
}
