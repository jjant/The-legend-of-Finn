package theLegendOfFinn.view.character;

import java.awt.Graphics;
import java.util.List;

import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.enemy.boss.BossProjectile;
import theLegendOfFinn.view.Renderer;

public class CharacterRenderer implements Renderer {
	private ImageData imageData;

	public CharacterRenderer() {
		this.imageData = new ImageData();
	}

	public void render(Graphics g) {
		for (int i = 0; i < imageData.size(); i++) {
			g.drawImage(imageData.getImage(i), imageData.getPosition(i).getX(), imageData.getPosition(i).getY(),
					imageData.getWidth(i), imageData.getHeight(i), null);
		}
	}

	public void draw(Character character) {
		imageData.add(character);
	}

	public void draw(List<BossProjectile> projectiles) {
		imageData.add(projectiles);
	}

	public void dispose() {
		imageData = new ImageData();
	}
}
