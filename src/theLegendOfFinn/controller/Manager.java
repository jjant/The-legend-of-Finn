package theLegendOfFinn.controller;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.EnemyFactory;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.view.MapRenderer;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.RenderFactory;

public class Manager {
	private MasterRenderer masterRenderer;
	private EnemyFactory enemyFactory;
	private RenderManager renderManager;
	private EventManager eventManager;
	//probando
	private ModelManager modelManager;
	
	// Esto va aca??
	// Respuesta: No creo. No se donde todavia.
	private RenderFactory renderFactory;

	private Ticker ticker;
	


	public Manager() {
		ticker = new Ticker(new Map(new PlayerCharacter(1)));
		masterRenderer = new MasterRenderer(new Delegate(this));
		
		renderManager = new RenderManager(masterRenderer);
		eventManager = new EventManager(masterRenderer, ticker);
		modelManager = new ModelManager(ticker);

		//Cambiar luego, estoy de acuerdo con el comment de arriba.
		enemyFactory = new EnemyFactory();
		
		// No se si esto va aca pero por ahora..
		renderFactory = new RenderFactory();

		renderManager.initialize();
	}

	public void initialize() {
		masterRenderer.addCharacterRenderer(renderFactory.getPlayerRenderer(ticker.getPlayer()));
		/*
		Iterator<EnemyCharacter> iter = enemies.iterator();
		while (iter.hasNext()) {
			Character character = iter.next();
			masterRenderer.addCharacterRenderer(rFactory.getCharacterRenderer(character, character.getType()));
		}
		*/
		masterRenderer.setMapRenderer(new MapRenderer(ticker.getMap()));
	}


	public void setStage(RenderManager.Stage stage) {
		renderManager.setStage(stage);
	}

	public RenderManager.Stage getStage() {
		return renderManager.getStage();
	}

	public void keyChange(int key) {
		setStage(eventManager.handleEvent(key, getStage()));
	}
	
	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.initialize();
	}
}
