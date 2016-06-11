package theLegendOfFinn.view.character;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.model.character.Character.State;

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
		
		//CAMBIAR DSP
		if(character.getClass() == PlayerCharacter.class &&character.getState() == State.ATTACKING){
			heights.add(55);
			widths.add(64);
		}
		else{
		heights.add(32);
		widths.add(32);}
	}

	public void dispose() {
		initialize();
	}
}
