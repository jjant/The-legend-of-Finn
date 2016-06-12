package theLegendOfFinn.view.character;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.EnemyDog;
import theLegendOfFinn.model.entity.character.EnemyDonut;
import theLegendOfFinn.model.entity.character.EnemyPenguin;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.Entity.Direction;

public class SpriteLoader {
	private Map<Class<?>, Map<Integer, Map<Direction, Image>>> classMap;

	private final static String playerSpritesPath = "./Assets/characters/player/";
	private final static String enemySpritesPath = "./Assets/characters/enemies/";

	private final static int[] characterStates = new int[]{Character.ATTACKING, Character.IDLE, Character.MOVING};
	
	public SpriteLoader() {
		Map<Integer, Map<Direction, Image>> playerMap = loadPlayerSprites();
		Map<Integer, Map<Direction, Image>> dogMap = loadDogSprites();
		Map<Integer, Map<Direction, Image>> penguinMap = loadPenguinSprites();
		Map<Integer, Map<Direction, Image>> donutMap = loadDonutSprites();
		

		classMap = new HashMap<>();
		classMap.put(PlayerCharacter.class, playerMap);
		classMap.put(EnemyDog.class, dogMap);
		classMap.put(EnemyPenguin.class, penguinMap);
		classMap.put(EnemyDonut.class, donutMap);
	}

	public Map<Direction, Image> getSprites(Character character) {
		return classMap.get(character.getClass()).get(character.getState());
	}

	private Map<Integer, Map<Direction, Image>> loadSprites(String spritesPath, String characterName) {
		Map<Integer, Map<Direction, Image>> characterMap = new HashMap<>();
		for (int state: characterStates) {
			Map<Direction, Image> directionMap = new HashMap<>();
			for (Direction direction : Direction.values()) {
				String fileName = spritesPath + characterName + "_" + stateToString(state) + "_" + direction.toString()
						+ ".gif";
				directionMap.put(direction, new ImageIcon(fileName).getImage());
			}
			characterMap.put(state, directionMap);
		}
		return characterMap;
	}

	private Map<Integer, Map<Direction, Image>> loadDogSprites() {
		return loadSprites(enemySpritesPath, "Dog");
	}

	private Map<Integer, Map<Direction, Image>> loadPenguinSprites() {
		return loadSprites(enemySpritesPath, "Penguin");
	}

	private Map<Integer, Map<Direction, Image>> loadPlayerSprites() {
		return loadSprites(playerSpritesPath, "Finn");
	}
	
	private Map<Integer, Map<Direction, Image>> loadDonutSprites() {
		return loadSprites(enemySpritesPath, "Donut");
	}
	
	//revisar. medio villero q este aca, pero bue.
	private String stateToString(int state){
		String stateString = null;
		switch(state){
		case Character.ATTACKING:
			stateString = "ATTACKING";
			break;
		case Character.IDLE:
			stateString = "IDLE";
			break;
		case Character.MOVING:
			stateString = "MOVING";
			break;
		//revisar dsp
		default:
			break;
		}
		return stateString;
	}
}
