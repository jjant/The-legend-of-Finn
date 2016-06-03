package theLegendOfFinn.view;

import theLegendOfFinn.model.character.Character;
import java.awt.Graphics;
import java.awt.Color;
/* cree esta clase porque el RenderFactory tiene que devolver un objeto
 * de algun clase como esta, se me ocurrio hacerlo asi pero despues se
 * vera si hay alguna manera mejor
 */



/////Poner el mapa: clase -> sprite.

public class CharacterRenderer implements Renderer {
	private Character character;
	private Color color;
	/* La idea es que en lugar de recibir el color reciba tipo WARRIOR o PLAYER 
	 * y entonces se renderiza utilizando los sprites correspondientes
	 */
	public CharacterRenderer(Character character, Color color) {
		this.character = character;
		this.color = color;
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(new Color(0.5f, 0f, 0f));
		g.setColor(color);
		g.drawOval(character.getX(), character.getY(), CELL_SIZE, CELL_SIZE);
		g.fillOval(character.getX(), character.getY(), CELL_SIZE, CELL_SIZE);
	}
}
