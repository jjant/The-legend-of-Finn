package theLegendOfFinn.view;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.model.character.EnemyHorse;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;


public class SpriteLoader {
	private Map<Class<?>, Map<Direction, Image>> map;
	
	private Map<Direction, Image> playerSprites;
	private Map<Direction, Image> playerMovSprites;
	private Map<Direction, Image> horseSprites;
	private Map<Direction, Image> warriorSprites;
	private Map<Direction, Image> warriorMovSprites;
	
	public SpriteLoader(){	
		playerSprites = new HashMap<>();
		//playerMovSprites = new HashMap<>();
		horseSprites = new HashMap<>();
		warriorSprites = new HashMap<>();
		//warriorMovSprites = new HashMap<>();
		
		try {
			playerSprites.put(Direction.UP, ImageIO.read(new File("./Assets/finnUp.png")));
			playerSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/finnDown.png")));
			playerSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/finnRight.png")));
			playerSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/finnLeft.png")));
			playerSprites.put(Direction.DOWN_MOV, new ImageIcon("./Assets/finnDown.gif").getImage());
			playerSprites.put(Direction.UP_MOV, new ImageIcon("./Assets/finnUp.gif").getImage());
			playerSprites.put(Direction.RIGHT_MOV, new ImageIcon("./Assets/finnRight.gif").getImage());
			playerSprites.put(Direction.LEFT_MOV, new ImageIcon("./Assets/finnLeft.gif").getImage());
			/*playerMovSprites.put(Direction.DOWN, new ImageIcon("./Assets/finnDown.gif").getImage());
			playerMovSprites.put(Direction.UP, new ImageIcon("./Assets/finnDown.gif").getImage());
			playerMovSprites.put(Direction.RIGHT, new ImageIcon("./Assets/finnDown.gif").getImage());
			playerMovSprites.put(Direction.LEFT, new ImageIcon("./Assets/finnDown.gif").getImage());*/
			
			warriorSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/warrior-down.png")));
			warriorSprites.put(Direction.UP, ImageIO.read(new File("./Assets/warrior-up.png")));
			warriorSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/warrior-left.png")));
			warriorSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/warrior-right.png")));
			warriorSprites.put(Direction.DOWN_MOV, ImageIO.read(new File("./Assets/warrior-down.png")));
			warriorSprites.put(Direction.UP_MOV, ImageIO.read(new File("./Assets/warrior-up.png")));
			warriorSprites.put(Direction.LEFT_MOV, ImageIO.read(new File("./Assets/warrior-left.png")));
			warriorSprites.put(Direction.RIGHT_MOV, ImageIO.read(new File("./Assets/warrior-right.png")));
			/*warriorMovSprites.put(Direction.DOWN, new ImageIcon("./Assets/finnDown.gif").getImage());
			warriorMovSprites.put(Direction.UP, new ImageIcon("./Assets/finnDown.gif").getImage());
			warriorMovSprites.put(Direction.RIGHT, new ImageIcon("./Assets/finnDown.gif").getImage());
			warriorMovSprites.put(Direction.LEFT, new ImageIcon("./Assets/finnDown.gif").getImage());*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		map = new HashMap<>();
		map.put(PlayerCharacter.class, playerSprites);
		//map.put(PlayerCharacter.class, playerMovSprites);
		map.put(EnemyHorse.class, horseSprites);
		map.put(EnemyWarrior.class, warriorSprites);
		//map.put(EnemyWarrior.class, warriorMovSprites);
	}

	public Map<Direction, Image> getSprites(Character character){
		return map.get(character.getClass());
	}
}
