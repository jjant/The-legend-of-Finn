package theLegendOfFinn.view.character;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import theLegendOfFinn.model.entity.Entity.Direction;
import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.character.enemy.boss.Boss;
import theLegendOfFinn.model.entity.character.enemy.boss.BossProjectile;
import theLegendOfFinn.model.utils.Position;

/**
 * Class holding the information of a sprite to be rendered.
 */
public class ImageData {
	
	// List containing the information of images.
	private List<Image> images;
	private List<Position> positions;
	private List<Integer> heights;
	private List<Integer> widths;

	private SpriteLoader spriteLoader;

	public ImageData() {
		initialize();
	}

	/**
	 * Clean every field by creating a new object.
	 */
	private void initialize() {
		images = new ArrayList<>();
		positions = new ArrayList<>();
		heights = new ArrayList<>();
		widths = new ArrayList<>();

		spriteLoader = new SpriteLoader();
	}
	
	/**
	 * Gets the image for a given index
	 * @param index
	 * @return the image
	 */
	public Image getImage(int index) {
		return images.get(index);
	}
	
	/**
	 * Gets the position for a given index
	 * @param index
	 * @return the position
	 */
	public Position getPosition(int index) {
		return positions.get(index);
	}

	/**
	 * Gets the height for a given index
	 * @param index
	 * @return the height
	 */
	public int getHeight(int index) {
		return heights.get(index);
	}

	/**
	 * Gets the width for a given index
	 * @param index
	 * @return the width
	 */
	public int getWidth(int index) {
		return widths.get(index);
	}

	/**
	 * Gets the amount of images
	 * @return
	 */
	public int size() {
		return images.size();
	}

	/**
	 * Adds a character to the map.
	 * @param character character to add
	 */
	public void add(Character character) {
		Map<Direction, Image> sprites = spriteLoader.getSprites(character);
		images.add(sprites.get(character.getDirection()));
		positions.add(character.getPosition());

		// Hack to considerate size for character attack gifs.
		if (character.getClass() == PlayerCharacter.class && character.getState() == Character.ATTACKING) {
			heights.add(48);
			widths.add(48);
		} else if (character.getClass().equals(Boss.class)) {
			heights.add(32);
			widths.add(32);
		} else {
			heights.add(32);
			widths.add(32);
		}
	}

	/**
	 * Adds a list of projectiles into the map
	 * @param projectiles
	 */
	public void add(List<BossProjectile> projectiles) {
		Image sprite = spriteLoader.getSprites(projectiles);
		for (BossProjectile projectile : projectiles) {
			images.add(sprite);
			positions.add(projectile.getPosition());
			heights.add(32);
			widths.add(32);
		}

	}
	
	/** 
	 * Disposes the image data.
	 */
	public void dispose() {
		initialize();
	}
}
