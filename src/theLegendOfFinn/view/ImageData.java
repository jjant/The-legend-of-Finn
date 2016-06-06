package theLegendOfFinn.view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.character.Character;

public class ImageData {
	private List<BufferedImage> images;
	private List<Position> positions;
	private List<Integer> heights;
	private List<Integer> widths;
	
	private SpriteLoader spriteLoader;
	
	public ImageData(){
		initialize();
	}
	
	private void initialize(){
		images = new ArrayList<>();
		positions = new ArrayList<>();
		heights = new ArrayList<>();
		widths = new ArrayList<>();
		
		spriteLoader = new SpriteLoader();
	}
	
	public BufferedImage getImage(int index){
		return images.get(index);
	}
	
	public Position getPosition(int index ){
		return positions.get(index);
	}
	
	public int getHeight(int index){
		return heights.get(index);
	}
	
	public int getWidth(int index){
		return widths.get(index);
	}
	
	public int size(){
		return images.size();
	}
	
	public void add(Character character){
		images.add(spriteLoader.getSprites(character).get(character.getDirection()));
		positions.add(character.getPosition());
		
		//CAMBIAR LUEGO
		heights.add(32);
		widths.add(32);
	}
	
	public void dispose(){
		initialize();
	}
}
