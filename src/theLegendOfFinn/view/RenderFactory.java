package theLegendOfFinn.view;

import java.awt.Color;

import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.PlayerCharacter;

public class RenderFactory {
	/* REVISAR!!
	 * en vez de pasarle el color, se le podrian pasar los sprites en un vector, nose.
	 * porque asi como esta, no tiene mucho sentido usar una factory
	 * porai faltan cosas o puse algo mal yo
	 */
	//Rompi este metodo por separar los enemigos en clases...
	/*
	public CharacterRenderer getCharacterRenderer(Character character) {
		switch (type) {
		case WARRIOR:
			return new CharacterRenderer(character, Color.RED);
		case HORSE:
			return new CharacterRenderer(character, Color.GREEN);
		case PLAYER:
			return new CharacterRenderer(character, Color.BLUE);
		default:
			throw new IllegalArgumentException();
		}
	}
	*/
	
	public CharacterRenderer getPlayerRenderer(PlayerCharacter player){
		return new CharacterRenderer(player, Color.BLUE);
	}
	//borrar luego
	public CharacterRenderer getHorseRenderer(EnemyCharacter enemy){
		return new CharacterRenderer(enemy, Color.RED);
	}
}
