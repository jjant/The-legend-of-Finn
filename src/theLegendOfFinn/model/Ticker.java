package theLegendOfFinn.model;

import theLegendOfFinn.model.character.PlayerCharacter;

public class Ticker {
	
	Map map;
	
	public Ticker(){
		map = new Map();
	}
	public Ticker(Map map){
		this.map = map;
	}
	public void updateOnEvent(int event) {
		// do stuff
	}
	
	public PlayerCharacter getPlayer(){
		return map.getPlayer();
	}
	
	public Map getMap(){ 
		return map;
	}
}
