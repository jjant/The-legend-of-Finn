package theLegendOfFinn.view;

import java.awt.Color;
import theLegendOfFinn.model.character.Character.*;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.model.character.Character;

public class RenderFactory {
	/* REVISAR!!
	 * en vez de pasarle el color, se le podrian pasar los sprites en un vector, nose.
	 * porque asi como esta, no tiene mucho sentido usar una factory
	 * porai faltan cosas o puse algo mal yo
	 */
	//Rompi este metodo por separar los enemigos en clases...
	/*
	public CharacterRenderer getCharacterRenderer(Character character, CharacterType type) {
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
}
