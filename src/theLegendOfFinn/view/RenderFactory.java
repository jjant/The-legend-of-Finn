package theLegendOfFinn.view;
/*
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
*/
public class RenderFactory {
	/*
	 * //Rompi este metodo por separar los enemigos en clases... /* public
	 * CharacterRenderer getCharacterRenderer(Character character) { switch
	 * (type) { case WARRIOR: return new CharacterRenderer(character,
	 * Color.RED); case HORSE: return new CharacterRenderer(character,
	 * Color.GREEN); case PLAYER: return new CharacterRenderer(character,
	 * Color.BLUE); default: throw new IllegalArgumentException(); } }
	 *
	 * Private HashMap<Character.Direction, BufferedImage> characterSprites;
	 * private HashMap<Character.CharacterType, HashMap<Character.Direction,
	 * BufferedImage>> spriteMap;*
	 * 
	 * public RenderFactory() { spriteMap = new HashMap<Character.CharacterType,
	 * HashMap<Character.Direction, BufferedImage>>(4);
	 * HashMap<Character.Direction, BufferedImage> characterSprites = new
	 * HashMap<Character.Direction, BufferedImage>(4); try {
	 * characterSprites.put(Character.Direction.DOWN, ImageIO.read(new
	 * File("./Assets/finnDown.png")));
	 * characterSprites.put(Character.Direction.UP, ImageIO.read(new
	 * File("./Assets/finnUp.png")));
	 * characterSprites.put(Character.Direction.LEFT, ImageIO.read(new
	 * File("./Assets/finnLeft.png")));
	 * characterSprites.put(Character.Direction.RIGHT, ImageIO.read(new
	 * File("./Assets/finnRight.png"))); } catch (IOException e) {
	 * e.printStackTrace(); } spriteMap.put(Character.CharacterType.PLAYER,
	 * characterSprites); characterSprites = new HashMap<Character.Direction,
	 * BufferedImage>(4); try { characterSprites.put(Character.Direction.DOWN,
	 * ImageIO.read(new File("./Assets/warrior-down.png")));
	 * characterSprites.put(Character.Direction.UP, ImageIO.read(new
	 * File("./Assets/warrior-up.png")));
	 * characterSprites.put(Character.Direction.LEFT, ImageIO.read(new
	 * File("./Assets/warrior-left.png")));
	 * characterSprites.put(Character.Direction.RIGHT, ImageIO.read(new
	 * File("./Assets/warrior-right.png"))); } catch (IOException e) {
	 * e.printStackTrace(); } spriteMap.put(Character.CharacterType.WARRIOR,
	 * characterSprites); }***
	 * 
	 * public CharacterRenderer getPlayerRenderer(PlayerCharacter player){
	 * return new CharacterRenderer(player,
	 * spriteMap.get(Character.CharacterType.PLAYER)); //return new
	 * CharacterRenderer(player, Character.CharacterType.PLAYER); } // borrar
	 * luego
	 * 
	 * public CharacterRenderer getWarriorRenderer(EnemyCharacter enemy){ return
	 * new CharacterRenderer(enemy,
	 * spriteMap.get(Character.CharacterType.WARRIOR)); //return new
	 * CharacterRenderer(enemy, Character.CharacterType.WARRIOR); }
	 */

}