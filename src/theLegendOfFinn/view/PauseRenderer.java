package theLegendOfFinn.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

//Cambiar toda la clase basicamente...
public class PauseRenderer implements Renderer {
    private BufferedImage backGroundImage;

    public void render(Graphics g) {
        try {
            backGroundImage = ImageIO.read(new File("./Assets/pause.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        g.drawImage(backGroundImage, 0, 0, MasterRenderer.WIDTH, MasterRenderer.HEIGHT, null);
        g.setColor(Color.RED);
    }
}
