package theLegendOfFinn.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import theLegendOfFinn.view.MenuRenderer.Option;

//Cambiar toda la clase basicamente...
public class PauseRenderer implements Renderer {
    private BufferedImage backGroundImage;
    public enum Option {
		RESUME, SAVE, EXIT;
	}
    
    private Option option = Option.RESUME;
    
    
    public void render(Graphics g) {
        try {
            backGroundImage = ImageIO.read(new File("./Assets/pause1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        g.drawImage(backGroundImage, 0, 0, MasterRenderer.WIDTH, MasterRenderer.HEIGHT, null);
        g.setColor(Color.RED);
		if (option.equals(Option.RESUME)) {
			g.drawRect(224, 160, 193, 50);
		} else if (option.equals(Option.SAVE)){
			g.drawRect(224, 231, 193, 50);
		} else {
			g.drawRect(224, 302, 193, 50);
		}
    }
    
	public void changeOption() {
		if (this.option.equals(Option.RESUME))
			this.option = Option.SAVE;
		else
			this.option = Option.RESUME;
	}
	
	public Option getOption() {
		return option;
	}
}
