package theLegendOfFinn.view;

import java.awt.Graphics;

/* Me parece que la constante CELL_SIZE deberia ir junto a WIDTH y HEIGHT
 * porque son todos datos de la pantalla, dimensiones.
 * pero nose, despues lo vemos.
 */
public interface Renderer {
	public static final int CELL_SIZE = 32;

	public void render(Graphics g);
}
