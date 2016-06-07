package theLegendOfFinn.controller;

import java.util.ArrayList;
import java.util.List;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.view.MapRenderer;
import theLegendOfFinn.view.MasterRenderer;

public class Manager {
	private MasterRenderer masterRenderer;
	
	private RenderManager renderManager;
	private EventManager eventManager;
	private ModelManager modelManager;
	
	private Ticker ticker;



	public Manager() {
		List<EnemyCharacter> enemyList = new ArrayList<EnemyCharacter>();
		enemyList.add(new EnemyWarrior(new Position(0, 0), Direction.LEFT));			
		enemyList.add(new EnemyWarrior(new Position(32 * 14, 32 * 14), Direction.LEFT));
		
		ticker = new Ticker(new Map(new PlayerCharacter(1), enemyList));
		//BORRAR DSP
		//ticker.getPlayer().tryToMove(Direction.DOWN, ticker.getMap().getGrid());
		//
		masterRenderer = new MasterRenderer(new Delegate(this));
		masterRenderer.setMapRenderer(new MapRenderer(ticker.getMap()));

		
		renderManager = new RenderManager(masterRenderer);
		eventManager = new EventManager(masterRenderer, ticker);
		modelManager = new ModelManager(ticker);

		renderManager.initialize();
	}


	public void setStage(Stage stage) {
		renderManager.setStage(stage);
		changeModTick();
	}
	
	//que hace esto??
	public void changeModTick(){
		if(getStage().equals(RenderManager.Stage.MAP))
			ticker.changeModifier(true);
		else
			ticker.changeModifier(false);
	}

	public Stage getStage() {
		return renderManager.getStage();
	}
	//probando.
	public Ticker getTicker(){
		return ticker;
	}
	
	public void keyChange(int key) {
		setStage(eventManager.handleEvent(key, getStage()));
	}
	
	public static void main(String[] args) {
		Manager manager = new Manager();
	}
}