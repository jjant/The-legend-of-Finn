package theLegendOfFinn.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Terminar
 * @author asd
 *
 */
@Deprecated
public class GUIRenderer implements Renderer {

	public void render(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("./Assets/lifebar3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, 100, 100, null);
	}

}
