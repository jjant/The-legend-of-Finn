package theLegendOfFinn.view;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.model.character.EnemyHorse;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;


public class SpriteLoader {
	private Map<Class<?>, Map<Direction, BufferedImage>> map;
	
	private Map<Direction, BufferedImage> playerSprites;
	private Map<Direction, BufferedImage> horseSprites;
	private Map<Direction, BufferedImage> warriorSprites;
	
	public SpriteLoader(){	
		playerSprites = new HashMap<>();
		horseSprites = new HashMap<>();
		warriorSprites = new HashMap<>();
		
		try {
			playerSprites.put(Direction.UP, ImageIO.read(new File("./Assets/finnUp.png")));
			playerSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/finnDown.png")));
			playerSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/finnRight.png")));
			playerSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/finnLeft.png")));
			
			warriorSprites.put(Character.Direction.DOWN, ImageIO.read(new File("./Assets/warrior-down.png")));
			warriorSprites.put(Character.Direction.UP, ImageIO.read(new File("./Assets/warrior-up.png")));
			warriorSprites.put(Character.Direction.LEFT, ImageIO.read(new File("./Assets/warrior-left.png")));
			warriorSprites.put(Character.Direction.RIGHT, ImageIO.read(new File("./Assets/warrior-right.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		map = new HashMap<>();
		
		map.put(PlayerCharacter.class, playerSprites);
		map.put(EnemyHorse.class, horseSprites);
		map.put(EnemyWarrior.class, warriorSprites);
	}
	public Map<Direction, BufferedImage> getSprites(Character character){
		return map.get(character.getClass());
		
	}
}
