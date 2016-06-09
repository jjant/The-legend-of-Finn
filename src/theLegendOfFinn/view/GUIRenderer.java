package theLegendOfFinn.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import theLegendOfFinn.model.character.PlayerCharacter;

/**
 * Renders the GUI.
 * 
 * @author Julian Antonielli
 *
 */
public class GUIRenderer implements Renderer {

	private PlayerCharacter player;
	private BufferedImage emptyHeart;
	private BufferedImage halfHeart;
	private BufferedImage fullHeart;

	private int hearts;
	private int fullHearts;
	private int halfHearts;
	private int emptyHearts;

	public GUIRenderer(PlayerCharacter player) {
		this.player = player;
		emptyHeart = halfHeart = fullHeart = null;

		try {
			emptyHeart = ImageIO.read(new File("./Assets/emptyHeart.png"));
			halfHeart = ImageIO.read(new File("./Assets/halfHeart.png"));
			fullHeart = ImageIO.read(new File("./Assets/fullHeart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		int x = 32;
		hearts = player.getMaxHP() / 2;
		fullHearts = player.getCurrentHP() / 2;
		halfHearts = player.getCurrentHP() % 2;
		emptyHearts = hearts - (fullHearts + halfHearts);
		System.out.println("Current hp:" + player.getCurrentHP());
		System.out.println("fullhearts: " + fullHearts + " halfhearts: " + halfHearts + " empty: " + emptyHearts);
		
		for (int i = 0; i < fullHearts; i++, x += 32)
			g.drawImage(fullHeart, x, 5, 32, 26, null);
		for (int i = 0; i < halfHearts; i++, x += 32)
			g.drawImage(halfHeart, x, 5, 32, 26, null);
		for (int i = 0; i < emptyHearts; i++, x += 32)
			g.drawImage(emptyHeart, x, 5, 32, 26, null);
	}
}
