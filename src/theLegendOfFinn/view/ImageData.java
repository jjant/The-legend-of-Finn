package theLegendOfFinn.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.character.Character;

public class ImageData {
	private List<Image> images;
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
	
	public Image getImage(int index){
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
	//Ver la forma de implementar mejor, pasa que con el hashmap de hashmaps como está hecho es
	//complicado diferenciar si está en movimiento teniendo en cuenta que es la misma dirección
	public void add(Character character){
		if(!character.isMoving())
			images.add(spriteLoader.getSprites(character).get(character.getDirection()));
		else{
			switch(character.getDirection()){
				case DOWN:
					images.add(spriteLoader.getSprites(character).get(Character.Direction.DOWN_MOV));
					break;
				case UP:
					images.add(spriteLoader.getSprites(character).get(Character.Direction.UP_MOV));
					break;
				case RIGHT:
					images.add(spriteLoader.getSprites(character).get(Character.Direction.RIGHT_MOV));
					break;
				case LEFT:
					images.add(spriteLoader.getSprites(character).get(Character.Direction.LEFT_MOV));
					break;
				default:
					images.add(spriteLoader.getSprites(character).get(character.getDirection()));
					break;
			}
		}
		positions.add(character.getPosition());
		
		//CAMBIAR LUEGO
		heights.add(32);
		widths.add(32);
	}
	
	public void dispose(){
		initialize();
	}
}
