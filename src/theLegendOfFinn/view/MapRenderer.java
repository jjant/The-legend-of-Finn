package theLegendOfFinn.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.model.Ticker.Arena;
import theLegendOfFinn.model.gameData.Map;

import javax.imageio.ImageIO;

public class MapRenderer implements Renderer {
	private static final String backGroudImagePath = "./Assets/maps/";

	private BufferedImage backGroundImage;
	/*
	 * private Map map;
	 * 
	 * REVISAR se crea una instancia de map y no se utiliza. RTA: Se va a usar
	 * para los obstaculos.
	 */

	public MapRenderer(Map map, Arena arena) {
		try {
			backGroundImage = ImageIO.read(new File(backGroudImagePath + arena.toString() + "_Map.png"));
		} catch (IOException e) {
			Manager.LOGGER.log(Level.FINE, "Background image missing", e);
		}

	}

	public void render(Graphics g) {

		g.drawImage(backGroundImage, 0, 0, MasterRenderer.WIDTH, MasterRenderer.HEIGHT, null);
		g.setColor(new Color(0, 0f, 0.5f));

		renderGrid(g);
	}

	/**
	 * Renders the map grid, used for debugging.
	 */
	private void renderGrid(Graphics g) {
		for (int i = 0; i <= Map.WIDTH; i++) {
			g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, Map.HEIGHT * CELL_SIZE);
			if (i <= Map.WIDTH)
				g.drawLine(0, i * CELL_SIZE, Map.WIDTH * CELL_SIZE, i * CELL_SIZE);
		}
	}
}