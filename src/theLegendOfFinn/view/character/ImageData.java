package theLegendOfFinn.view.character;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.BossProjectile;
import theLegendOfFinn.model.entity.Entity.Direction;
import theLegendOfFinn.model.entity.character.Boss;
import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.PlayerCharacter;

/**
 * Class holding the information of a sprite to be rendered.
 * 
 * @author LCDPCJL
 *
 */
public class ImageData {
	private List<Image> images;
	private List<Position> positions;
	private List<Integer> heights;
	private List<Integer> widths;

	private SpriteLoader spriteLoader;

	public ImageData() {
		initialize();
	}

	private void initialize() {
		images = new ArrayList<>();
		positions = new ArrayList<>();
		heights = new ArrayList<>();
		widths = new ArrayList<>();

		spriteLoader = new SpriteLoader();
	}

	public Image getImage(int index) {
		return images.get(index);
	}

	public Position getPosition(int index) {
		return positions.get(index);
	}

	public int getHeight(int index) {
		return heights.get(index);
	}

	public int getWidth(int index) {
		return widths.get(index);
	}

	public int size() {
		return images.size();
	}

	// la misma dirección
	public void add(Character character) {
		Map<Direction, Image> sprites = spriteLoader.getSprites(character);
		images.add(sprites.get(character.getDirection()));
		positions.add(character.getPosition());

		// CAMBIAR DSP
		if (character.getClass() == PlayerCharacter.class && character.getState() == Character.ATTACKING) {
			heights.add(48);
			widths.add(48);
		}

		else if (character.getClass().equals(Boss.class)) {
			heights.add(32);
			widths.add(32);
		} else {
			heights.add(32);
			widths.add(32);
		}
	}

	public void add(List<BossProjectile> projectiles) {
		Image sprite = spriteLoader.getSprites(projectiles);
		for (BossProjectile projectile : projectiles) {
			images.add(sprite);
			positions.add(projectile.getPosition());
			heights.add(32);
			widths.add(32);
		}

	}

	public void dispose() {
		initialize();
	}
}
