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
import theLegendOfFinn.model.entity.character.Character.State;

public class SpriteLoader {
	private Map<Class<?>, Map<State, Map<Direction, Image>>> classMap;

	private final static String playerSpritesPath = "./Assets/characters/player/";
	private final static String enemySpritesPath = "./Assets/characters/enemies/";

	public SpriteLoader() {
		Map<State, Map<Direction, Image>> playerMap = loadPlayerSprites();
		Map<State, Map<Direction, Image>> dogMap = loadDogSprites();
		Map<State, Map<Direction, Image>> penguinMap = loadPenguinSprites();
		Map<State, Map<Direction, Image>> donutMap = loadDonutSprites();
		

		classMap = new HashMap<>();
		classMap.put(PlayerCharacter.class, playerMap);
		classMap.put(EnemyDog.class, dogMap);
		classMap.put(EnemyPenguin.class, penguinMap);
		classMap.put(EnemyDonut.class, donutMap);
	}

	public Map<Direction, Image> getSprites(Character character) {
		return classMap.get(character.getClass()).get(character.getState());
	}

	private Map<State, Map<Direction, Image>> loadSprites(String spritesPath, String characterName) {
		Map<State, Map<Direction, Image>> characterMap = new HashMap<>();
		for (State state : State.values()) {
			Map<Direction, Image> directionMap = new HashMap<>();
			for (Direction direction : Direction.values()) {
				String fileName = spritesPath + characterName + "_" + state.toString() + "_" + direction.toString()
						+ ".gif";
				directionMap.put(direction, new ImageIcon(fileName).getImage());
			}
			characterMap.put(state, directionMap);
		}
		return characterMap;
	}

	private Map<State, Map<Direction, Image>> loadDogSprites() {
		return loadSprites(enemySpritesPath, "Dog");
	}

	private Map<State, Map<Direction, Image>> loadPenguinSprites() {
		return loadSprites(enemySpritesPath, "Penguin");
	}

	private Map<State, Map<Direction, Image>> loadPlayerSprites() {
		return loadSprites(playerSpritesPath, "Finn");
	}
	
	private Map<State, Map<Direction, Image>> loadDonutSprites() {
		return loadSprites(enemySpritesPath, "Donut");
	}
}
