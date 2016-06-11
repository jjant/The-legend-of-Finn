package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Position;

public class Boss extends EnemyCharacter{
	private static final long serialVersionUID = 1L;
	
	//probando
	private static final int RESTING_TIME = 1000;
	//cambiar dsp
	private static final Position BOSS_POSITION = new Position(0, 120);
	private static final int BOSS_VELOCITY = 3;
	private static final int BOSS_MAX_HP = 10;
	private static final int BOSS_ATTACK = 5;
	private static final int BOSS_HP_BOUNTY = 0; 
	
	public Boss(){
		super(BOSS_POSITION, BOSS_VELOCITY, BOSS_MAX_HP, BOSS_ATTACK, BOSS_HP_BOUNTY);
	}
	
	//probando
	public void teleport(Position newPosition){
		
	}
}
