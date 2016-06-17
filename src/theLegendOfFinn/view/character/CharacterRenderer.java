package theLegendOfFinn.view.character;

import java.awt.Graphics;
import java.util.List;

import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.enemy.boss.BossProjectile;
import theLegendOfFinn.view.Renderer;

/**
 * Manages the character renderization
 */
public class CharacterRenderer implements Renderer {
	private ImageData imageData;

	public CharacterRenderer() {
		this.imageData = new ImageData();
	}

	/**
	 * Draws and set size for the characters displayed
	 */
	public void render(Graphics g) {
		for (int i = 0; i < imageData.size(); i++) {
			g.drawImage(imageData.getImage(i), imageData.getPosition(i).getX(), imageData.getPosition(i).getY(),
					imageData.getWidth(i), imageData.getHeight(i), null);
		}
	}

	/**
	 * Draws a given character
	 * @param character character to draw
	 */
	public void draw(Character character) {
		imageData.add(character);
	}
	
	/**
	 * Draws a list of projectiles
	 * @param projectiles projectiles to draw.
	 */
	public void draw(List<BossProjectile> projectiles) {
		imageData.add(projectiles);
	}

	/**
	 * Disposes the image data
	 */
	public void dispose() {
		imageData = new ImageData();
	}
}
