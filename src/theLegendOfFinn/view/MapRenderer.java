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

/**
 * Manages the map renderization
 */
public class MapRenderer implements Renderer {
	private static final String backGroudImagePath = "./Assets/maps/";

	private BufferedImage backGroundImage;

	public MapRenderer(Map map, Arena arena) {
		try {
			backGroundImage = ImageIO.read(new File(backGroudImagePath + arena.toString() + "_Map.png"));
		} catch (IOException e) {
			Manager.LOGGER.log(Level.FINE, "Background image missing", e);
		}

	}

	/**
	 * Render a given map graphic.
	 */
	public void render(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, MasterRenderer.WIDTH, MasterRenderer.HEIGHT, null);
		g.setColor(new Color(0, 0f, 0.5f));
	}
}