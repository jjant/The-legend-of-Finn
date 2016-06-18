package theLegendOfFinn.view.character;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.ImageIcon;

import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyDog;
import theLegendOfFinn.model.entity.character.enemy.EnemyDonut;
import theLegendOfFinn.model.entity.character.enemy.EnemyKing;
import theLegendOfFinn.model.entity.character.enemy.EnemyPenguin;
import theLegendOfFinn.model.entity.character.enemy.boss.Boss;
import theLegendOfFinn.model.entity.character.enemy.boss.BossProjectile;
import theLegendOfFinn.model.entity.Entity.Direction;

public class SpriteLoader {
	private Map<Class<?>, Map<Integer, Map<Direction, Image>>> classMap;
	
	// Enemy names to index assets
	private enum EnemyName {
		Dog, Penguin, Donut, King;
	}
	
	// Directories to look for assets
	private final static String playerSpritesPath = "./Assets/characters/player/";
	private final static String enemySpritesPath = "./Assets/characters/enemies/";
	private final static String BossSpritesPath = "./Assets/characters/boss/";
	private final static String BossProjectilePath = "./Assets/characters/boss/Boss_STAR.gif";
	
	// Possible states for renderizable sprites
	private final static int[] characterStates = new int[] { Character.ATTACKING, Character.IDLE, Character.MOVING };

	public SpriteLoader() {
		Map<Integer, Map<Direction, Image>> playerMap = loadPlayerSprites();
		Map<Integer, Map<Direction, Image>> dogMap = loadEnemySprites(EnemyName.Dog);
		Map<Integer, Map<Direction, Image>> penguinMap = loadEnemySprites(EnemyName.Penguin);
		Map<Integer, Map<Direction, Image>> donutMap = loadEnemySprites(EnemyName.Donut);
		Map<Integer, Map<Direction, Image>> kingMap = loadEnemySprites(EnemyName.King);
		Map<Integer, Map<Direction, Image>> bossMap = loadBossSprites();

		classMap = new HashMap<>();
		classMap.put(PlayerCharacter.class, playerMap);
		classMap.put(EnemyDog.class, dogMap);
		classMap.put(EnemyPenguin.class, penguinMap);
		classMap.put(EnemyDonut.class, donutMap);
		classMap.put(EnemyKing.class, kingMap);
		classMap.put(Boss.class, bossMap);
	}
	
	/**
	 * Returns a map which maps a direction to an image
	 * @param character character from which load the image
	 * @return a map
	 */
	public Map<Direction, Image> getSprites(Character character) {
		return classMap.get(character.getClass()).get(character.getState());
	}
	
	/**
	 * Return an image from a list of projectiles
	 * @param projectiles list of projectiles
	 * @return the image
	 */
	public Image getSprites(List<BossProjectile> projectiles) {
		return new ImageIcon(BossProjectilePath).getImage();
	}

	/**
	 * Returns a map which maps states to the map of directions->images
	 * @param spritesPath path for a given sprite
	 * @param characterName name for characters
	 * @return a map
	 */
	private Map<Integer, Map<Direction, Image>> loadSprites(String spritesPath, String characterName) {
		Map<Integer, Map<Direction, Image>> characterMap = new HashMap<>();
		for (int state : characterStates) {
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

	/**
	 * Loads sprites for the player into the map
	 * @return the map
	 */
	private Map<Integer, Map<Direction, Image>> loadPlayerSprites() {
		return loadSprites(playerSpritesPath, "Finn");
	}

	/**
	 * Loads the sprite for a given enemy into the map
	 * @param enemyName name of the enemy
	 * @return the map
	 */
	private Map<Integer, Map<Direction, Image>> loadEnemySprites(EnemyName enemyName) {
		return loadSprites(enemySpritesPath, enemyName.toString());
	}

	/**
	 * Loads the sprites for the boss into the map.
	 * @return the map.
	 */
	private Map<Integer, Map<Direction, Image>> loadBossSprites() {
		Map<Integer, Map<Direction, Image>> characterMap = new HashMap<>();
		for (int state : characterStates) {
			Map<Direction, Image> directionMap = new HashMap<>();
			for (Direction direction : Direction.values()) {
				String fileName = BossSpritesPath + "Boss" + "_" + stateToString(state) + ".gif";
				directionMap.put(direction, new ImageIcon(fileName).getImage());
			}
			characterMap.put(state, directionMap);
		}
		return characterMap;
	}

	/**
	 * Casts a state into a string.
	 * @param state State to cast
	 * @return the string
	 */
	private String stateToString(int state) {
		String stateString = null;
		switch (state) {
		case Character.ATTACKING:
			stateString = "ATTACKING";
			break;
		case Character.IDLE:
			stateString = "IDLE";
			break;
		case Character.MOVING:
			stateString = "MOVING";
			break;
		default:
			break;
		}
		return stateString;
	}
}
