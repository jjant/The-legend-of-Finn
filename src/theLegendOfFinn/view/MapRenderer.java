package theLegendOfFinn.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import theLegendOfFinn.model.Map;

import javax.imageio.ImageIO;

public class MapRenderer implements Renderer {
	private BufferedImage backGroundImage;
	private Map map;

	/*
	 * REVISAR se crea una instancia de map y no se utiliza.
	 */
	public MapRenderer(Map map) {
		this.map = map;
	}

	public void render(Graphics g) {
		try {
			backGroundImage = ImageIO.read(new File("./Assets/grass.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		g.drawImage(backGroundImage, 0, 0, MasterRenderer.WIDTH, MasterRenderer.HEIGHT, null);
		g.setColor(new Color(0, 0f, 0.5f));
		// Renderiza las lineas del tablero. Borrar despues.
		for (int i = 0; i <= Map.WIDTH; i++) {
			g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, Map.HEIGHT * CELL_SIZE);
			if (i <= Map.WIDTH)
				g.drawLine(0, i * CELL_SIZE, Map.WIDTH * CELL_SIZE, i * CELL_SIZE);
		}
	}
}