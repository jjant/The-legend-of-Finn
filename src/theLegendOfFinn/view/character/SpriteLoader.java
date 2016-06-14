package theLegendOfFinn.view.character;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.ImageIcon;

import theLegendOfFinn.model.entity.character.Boss;
import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.entity.character.EnemyDog;
import theLegendOfFinn.model.entity.character.EnemyDonut;
import theLegendOfFinn.model.entity.character.EnemyKing;
import theLegendOfFinn.model.entity.character.EnemyPenguin;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.BossProjectile;
import theLegendOfFinn.model.entity.Entity.Direction;

public class SpriteLoader {
	private Map<Class<?>, Map<Integer, Map<Direction, Image>>> classMap;

	private enum EnemyName {
		Dog, Penguin, Donut, King;
	}

	private final static String playerSpritesPath = "./Assets/characters/player/";
	private final static String enemySpritesPath = "./Assets/characters/enemies/";
	private final static String BossSpritesPath = "./Assets/characters/boss/";
	private final static String BossProjectilePath = "./Assets/characters/boss/Boss_STAR.gif";

	// cambiar dsp
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

	public Map<Direction, Image> getSprites(Character character) {
		return classMap.get(character.getClass()).get(character.getState());
	}

	public Image getSprites(List<BossProjectile> projectiles) {
		return new ImageIcon(BossProjectilePath).getImage();
	}

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

	private Map<Integer, Map<Direction, Image>> loadPlayerSprites() {
		return loadSprites(playerSpritesPath, "Finn");
	}

	private Map<Integer, Map<Direction, Image>> loadEnemySprites(EnemyName enemyName) {
		return loadSprites(enemySpritesPath, enemyName.toString());
	}

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

	// revisar. medio villero q este aca, pero bue.
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
		// revisar dsp
		default:
			break;
		}
		return stateString;
	}
}
