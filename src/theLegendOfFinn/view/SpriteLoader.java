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
import theLegendOfFinn.model.character.Character.State;
import theLegendOfFinn.model.character.EnemyHorse;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;


public class SpriteLoader {
	private Map<Class<?>, Map<State, Map<Direction, Image>>> classMap;
	
	private Map<State, Map<Direction, Image>> playerMap;
	private Map<State, Map<Direction, Image>> warriorMap;
	private Map<State, Map<Direction, Image>> horseMap;
	
	private Map<Direction, Image> playerIdleSprites;
	private Map<Direction, Image> playerMovingSprites;
	private Map<Direction, Image> playerAttackingSprites;
	private Map<Direction, Image> horseSprites;
	private Map<Direction, Image> warriorIdleSprites;
	private Map<Direction, Image> warriorMovingSprites;
	
	public SpriteLoader(){	
		playerIdleSprites = new HashMap<>();
		playerMovingSprites = new HashMap<>();
		playerAttackingSprites = new HashMap<>();
		
		horseSprites = new HashMap<>();
		warriorIdleSprites = new HashMap<>();
		//warriorMovSprites = new HashMap<>();
		
		try {
			playerIdleSprites.put(Direction.UP, ImageIO.read(new File("./Assets/finnIdleUp.png")));
			playerIdleSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/finnIdleDown.png")));
			playerIdleSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/finnIdleRight.png")));
			playerIdleSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/finnIdleLeft.png")));
			
			playerMovingSprites.put(Direction.DOWN, new ImageIcon("./Assets/finnMovingDown.gif").getImage());
			playerMovingSprites.put(Direction.UP, new ImageIcon("./Assets/finnMovingUp.gif").getImage());
			playerMovingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/finnMovingRight.gif").getImage());
			playerMovingSprites.put(Direction.LEFT, new ImageIcon("./Assets/finnMovingLeft.gif").getImage());
			//CAMBIAR, FALTAN LOS SPRITES
			playerAttackingSprites.put(Direction.DOWN, new ImageIcon("./Assets/finnMovingDown.gif").getImage());
			playerAttackingSprites.put(Direction.UP, new ImageIcon("./Assets/finnMovingUp.gif").getImage());
			playerAttackingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/finnAttackingRight.gif").getImage());
			playerAttackingSprites.put(Direction.UP, new ImageIcon("./Assets/finnMovingUp.gif").getImage());
			
			warriorIdleSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/warriorIdleDown.png")));
			warriorIdleSprites.put(Direction.UP, ImageIO.read(new File("./Assets/warriorIdleUp.png")));
			warriorIdleSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/warriorIdleLeft.png")));
			warriorIdleSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/warriorIdleRight.png")));
			/*
			warriorMovingSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/warrior-down.png")));
			warriorMovingSprites.put(Direction.UP, ImageIO.read(new File("./Assets/warrior-up.png")));
			warriorMovingSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/warrior-left.png")));
			warriorMovingSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/warrior-right.png")));
		*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerMap = new HashMap<>();
		playerMap.put(State.IDLE, playerIdleSprites);
		playerMap.put(State.MOVING, playerMovingSprites);
		//cambiar dsp
		playerMap.put(State.ATTACKING, playerAttackingSprites);
		
		warriorMap = new HashMap<>();
		warriorMap.put(State.IDLE, warriorIdleSprites);
		//cambiar dsp
		warriorMap.put(State.MOVING, warriorIdleSprites);
		warriorMap.put(State.ATTACKING, warriorIdleSprites);
		
		
		classMap = new HashMap<>();
		classMap.put(PlayerCharacter.class, playerMap);
		classMap.put(EnemyHorse.class, horseMap);
		classMap.put(EnemyWarrior.class, warriorMap);
	}

	public Map<Direction, Image> getSprites(Character character){
		return classMap.get(character.getClass()).get(character.getState());
	}
}
