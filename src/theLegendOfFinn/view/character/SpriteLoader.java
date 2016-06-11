package theLegendOfFinn.view.character;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.model.character.Character.State;
import theLegendOfFinn.model.character.EnemyDog;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;


public class SpriteLoader {
	private Map<Class<?>, Map<State, Map<Direction, Image>>> classMap;
	
	private Map<State, Map<Direction, Image>> playerMap;
	private Map<State, Map<Direction, Image>> penguinMap;
	private Map<State, Map<Direction, Image>> dogMap;
	
	private Map<Direction, Image> playerIdleSprites;
	private Map<Direction, Image> playerMovingSprites;
	private Map<Direction, Image> playerAttackingSprites;
	
	private Map<Direction, Image> dogIdleSprites;
	private Map<Direction, Image> dogMovingSprites;
	private Map<Direction, Image> dogAttackingSprites;
	
	private Map<Direction, Image> penguinIdleSprites;
	private Map<Direction, Image> penguinMovingSprites;
	private Map<Direction, Image> penguinAttackingSprites;
	
	public SpriteLoader(){	
		playerIdleSprites = new HashMap<>();
		playerMovingSprites = new HashMap<>();
		playerAttackingSprites = new HashMap<>();
		
		dogIdleSprites = new HashMap<>();
		dogMovingSprites = new HashMap<>();
		dogAttackingSprites = new HashMap<>();
	
		penguinIdleSprites = new HashMap<>();
		penguinMovingSprites = new HashMap<>();
		penguinAttackingSprites = new HashMap<>();
		
		try {
			playerIdleSprites.put(Direction.UP, ImageIO.read(new File("./Assets/characters/player/FinnIdleUp2.png")));
			playerIdleSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/characters/player/FinnIdleRight2.png")));
			playerIdleSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/characters/player/FinnIdleDown2.png")));
			playerIdleSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/characters/player/FinnIdleLeft2.png")));
			
			playerMovingSprites.put(Direction.UP, new ImageIcon("./Assets/characters/player/FinnMovingUp2.gif").getImage());
			playerMovingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/characters/player/FinnMovingRight2.gif").getImage());
			playerMovingSprites.put(Direction.DOWN, new ImageIcon("./Assets/characters/player/FinnMovingDown2.gif").getImage());
			playerMovingSprites.put(Direction.LEFT, new ImageIcon("./Assets/characters/player/FinnMovingLeft2.gif").getImage());
			//CAMBIAR, FALTAN LOS SPRITES
			playerAttackingSprites.put(Direction.UP, new ImageIcon("./Assets/characters/player/FinnAttackingRight2.gif").getImage());
			playerAttackingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/characters/player/finnAttackingRight.gif").getImage());
			playerAttackingSprites.put(Direction.DOWN, new ImageIcon("./Assets/characters/player/finnAttackingRight.gif").getImage());
			playerAttackingSprites.put(Direction.LEFT, new ImageIcon("./Assets/characters/player/finnAttackingLeft.gif").getImage());
			
			penguinIdleSprites.put(Direction.UP, ImageIO.read(new File("./Assets/characters/enemies/EnemyIdleUp.png")));
			penguinIdleSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/characters/enemies/EnemyIdleRight.png")));
			penguinIdleSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/characters/enemies/EnemyIdleDown.png")));
			penguinIdleSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/characters/enemies/EnemyIdleLeft.png")));
			
			penguinMovingSprites.put(Direction.UP, new ImageIcon("./Assets/characters/enemies/EnemyMovingUp.gif").getImage());
			penguinMovingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/characters/enemies/EnemyMovingRight.gif").getImage());
			penguinMovingSprites.put(Direction.DOWN, new ImageIcon("./Assets/characters/enemies/EnemyMovingDown.gif").getImage());
			penguinMovingSprites.put(Direction.LEFT, new ImageIcon("./Assets/characters/enemies/EnemyMovingLeft.gif").getImage());
			
			penguinAttackingSprites.put(Direction.UP, new ImageIcon("./Assets/characters/enemies/EnemyAttackingUp.gif").getImage());
			penguinAttackingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/characters/enemies/EnemyAttackingRight.gif").getImage());
			penguinAttackingSprites.put(Direction.DOWN, new ImageIcon("./Assets/characters/enemies/EnemyAttackingDown.gif").getImage());
			penguinAttackingSprites.put(Direction.LEFT, new ImageIcon("./Assets/characters/enemies/EnemyAttackingLeft.gif").getImage());
			
			dogIdleSprites.put(Direction.UP, ImageIO.read(new File("./Assets/characters/enemies/DogIdleUp.png")));
			dogIdleSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/characters/enemies/DogIdleRight.png")));
			dogIdleSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/characters/enemies/DogIdleDown.png")));
			dogIdleSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/characters/enemies/DogIdleLeft.png")));
			
			dogMovingSprites.put(Direction.UP, new ImageIcon("./Assets/characters/enemies/DogMovingUp.gif").getImage());
			dogMovingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/characters/enemies/DogMovingRight.gif").getImage());
			dogMovingSprites.put(Direction.DOWN, new ImageIcon("./Assets/characters/enemies/DogMovingDown.gif").getImage());
			dogMovingSprites.put(Direction.LEFT, new ImageIcon("./Assets/characters/enemies/DogMovingLeft.gif").getImage());
			//Falta agregar
			dogAttackingSprites.put(Direction.UP, new ImageIcon("./Assets/characters/enemies/DogAttackingUp.gif").getImage());
			dogAttackingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/characters/enemies/DogAttackingRight.gif").getImage());
			dogAttackingSprites.put(Direction.DOWN, new ImageIcon("./Assets/characters/enemies/DogAttackingDown.gif").getImage());
			dogAttackingSprites.put(Direction.LEFT, new ImageIcon("./Assets/characters/enemies/DogAttackingLeft.gif").getImage());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerMap = new HashMap<>();
		playerMap.put(State.IDLE, playerIdleSprites);
		playerMap.put(State.MOVING, playerMovingSprites);
		playerMap.put(State.ATTACKING, playerAttackingSprites);
		
		penguinMap = new HashMap<>();
		penguinMap.put(State.IDLE, penguinIdleSprites);
		penguinMap.put(State.MOVING, penguinMovingSprites);
		penguinMap.put(State.ATTACKING, penguinAttackingSprites);
		
		dogMap = new HashMap<>();
		dogMap.put(State.IDLE, dogIdleSprites);
		dogMap.put(State.MOVING, dogMovingSprites);
		dogMap.put(State.ATTACKING, dogAttackingSprites);
		
		classMap = new HashMap<>();
		classMap.put(PlayerCharacter.class, playerMap);
		classMap.put(EnemyDog.class, dogMap);
		classMap.put(EnemyWarrior.class, penguinMap);
	}

	public Map<Direction, Image> getSprites(Character character){
		return classMap.get(character.getClass()).get(character.getState());
	}
}
