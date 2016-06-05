package theLegendOfFinn.view;

import theLegendOfFinn.model.character.Character;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;


/////Poner el mapa: clase -> sprite.

public class CharacterRenderer implements Renderer {
	private Character character;
	
	/*
	private Character.CharacterType type;
	
	private BufferedImage finnDown;
	private BufferedImage finnUp;
	private BufferedImage finnRight;
	private BufferedImage finnLeft;
	private BufferedImage warriorDown;
	*/
	
	private HashMap<Character.Direction, BufferedImage> characterSprites;
	
	public CharacterRenderer(Character character, HashMap<Character.Direction, BufferedImage> characterSprites) {
		this.character = character;
		this.characterSprites = characterSprites;
	}

	/*
	public CharacterRenderer(Character character, Character.CharacterType type) {
		this.character = character;
		this.type = type;
		switch(type) {
			case PLAYER: {
				try {
					finnDown = ImageIO.read(new File("./Assets/finnDown.png"));
					finnUp = ImageIO.read(new File("./Assets/finnUp.png"));
					finnRight = ImageIO.read(new File("./Assets/finnRight.png"));
					finnLeft = ImageIO.read(new File("./Assets/finnLeft.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			case WARRIOR: {
				try {
					warriorDown = ImageIO.read(new File("./Assets/warrior-down.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	*/

	public Character getCharacter(){
		return character;
	}
	
	@Override
	public void render(Graphics g) {
		BufferedImage img;
		if (character.getDirection()==null)
			img = characterSprites.get(Character.Direction.DOWN);
		else
			switch (character.getDirection()) {
				case DOWN: {
					img = characterSprites.get(Character.Direction.DOWN);
					break;
				}
				case UP: {
					img = characterSprites.get(Character.Direction.UP);
					break;
				}
				case RIGHT: {
					img = characterSprites.get(Character.Direction.RIGHT);
					break;
				}
				case LEFT: {
					img = characterSprites.get(Character.Direction.LEFT);
					break;
				}
				default: img = characterSprites.get(Character.Direction.DOWN);
			}
		g.drawImage(img, character.getPosition().getX(), character.getPosition().getY(), CELL_SIZE, CELL_SIZE, null);
	}
	
	//No sé si en render mismo se hacen los chequeos, pero así funca bien para arrancar..
	//@Override
	/*
	public void render(Graphics g) {

		BufferedImage img;
		switch (type) {
			case PLAYER: {
				//getDirection devuelve el movDirection, que inicialmente apunta a null. Ver cómo mejorar.
				//Chequear también los defaults...
				if (character.getDirection()==null)
					img = finnDown;
				else
					switch (character.getDirection()) {
						case DOWN: {
							img = finnDown;
							break;
						}
						case UP: {
							img = finnUp;
							break;
						}
						case RIGHT: {
							img = finnRight;
							break;
						}
						case LEFT: {
							img = finnLeft;
							break;
						}
						default: img = finnDown;
					}
				break;
			}
			case WARRIOR: {
				img = warriorDown;
				break;
			}
			default: img = finnDown;
		}
		g.drawImage(img, character.getPosition().getX(), character.getPosition().getY(), CELL_SIZE, CELL_SIZE, null);

	}
	*/
}
