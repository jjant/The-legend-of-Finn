package theLegendOfFinn.view;

import theLegendOfFinn.model.character.Character;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/* cree esta clase porque el RenderFactory tiene que devolver un objeto
 * de algun clase como esta, se me ocurrio hacerlo asi pero despues se
 * vera si hay alguna manera mejor
 */



/////Poner el mapa: clase -> sprite.

public class CharacterRenderer implements Renderer {
	private Character character;
	private Character.CharacterType type;
	private Color color;
	private BufferedImage zeldaDown;
	private BufferedImage warriorDown;
	/* La idea es que en lugar de recibir el color reciba tipo WARRIOR o PLAYER 
	 * y entonces se renderiza utilizando los sprites correspondientes
	 */
	public CharacterRenderer(Character character, Character.CharacterType type) {
		this.character = character;
		this.type = type;
		switch(type) {
			case PLAYER: {
				try {
					zeldaDown = ImageIO.read(new File("./Assets/zelda-down.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			case WARRIOR: {
				try {
					warriorDown = ImageIO.read(new File("./Assets/warrior-down.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(color);
		switch (type) {
			case PLAYER: {
				g.drawImage(zeldaDown, character.getPosition().getX(), character.getPosition().getY(), CELL_SIZE, CELL_SIZE, null);
				break;
			}
			case WARRIOR: {
				g.drawImage(warriorDown, character.getPosition().getX(), character.getPosition().getY(), CELL_SIZE, CELL_SIZE, null);
				break;
			}
		}
		/*else{
			g.drawOval(character.getPosition().getX(), character.getPosition().getY(), CELL_SIZE, CELL_SIZE);
			g.fillOval(character.getPosition().getX(), character.getPosition().getY(), CELL_SIZE, CELL_SIZE);
		}*/
	}
}
