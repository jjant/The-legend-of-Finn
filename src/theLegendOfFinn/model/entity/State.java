package theLegendOfFinn.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//para probar dsp. no le den bola.
//Probablemente ni la use.

public enum State {
	IDLE,
	ATTACKING,
	MOVING;
	
	private static final List<State> CHARACTER_STATES = setCharacterStates();
	
	public static State getRandomCharacterState(){
		Random r = new Random();
		int index = r.nextInt(CHARACTER_STATES.size());
		return CHARACTER_STATES.get(index);
	}
	
	private static List<State> setCharacterStates(){
		List<State> charStates = new ArrayList<>();
		
		charStates.add(IDLE);
		charStates.add(ATTACKING);
		charStates.add(MOVING);
		
		return charStates;
	}
}
