package theLegendOfFinn.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import theLegendOfFinn.model.character.PlayerCharacter;

/** Renders the GUI.
 * 
 * @author Julian Antonielli
 *
 */
public class GUIRenderer implements Renderer {

	private PlayerCharacter player;
	private BufferedImage life1;
	private BufferedImage life2;
	private BufferedImage life3;
	
	
	public GUIRenderer(PlayerCharacter player){
		this.player = player;
		life1 = life2 = life3 = null;
		
		try {
			life1 = ImageIO.read(new File("./Assets/lifebar1.png"));
			life2 = ImageIO.read(new File("./Assets/lifebar2.png"));
			life3 = ImageIO.read(new File("./Assets/lifebar3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		BufferedImage displayImage;
		switch(player.getCurrentHP()){
		case 1:
			displayImage = life1;
			break;
		case 2:
			displayImage = life2;
			break;
		case 3:
			displayImage = life3;
			break;
		default:
			displayImage = life3;
			break;
		}
		g.drawImage(displayImage, 32, 0, null);
	}

}
