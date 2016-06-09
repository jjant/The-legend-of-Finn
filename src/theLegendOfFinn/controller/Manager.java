package theLegendOfFinn.controller;


import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.view.MapRenderer;
import theLegendOfFinn.view.MasterRenderer;

public class Manager {
	private MasterRenderer masterRenderer;
	
	private RenderManager renderManager;
	private EventManager eventManager;
	@SuppressWarnings("unused")
	private ModelManager modelManager;
	
	private Ticker ticker;



	public Manager() {
		ticker = new Ticker(new PlayerCharacter(0), new Notifier(this));

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
	//RTA: para el thread del modelo si stage no es MAP
	public void changeModTick(){
		if(getStage().equals(RenderManager.Stage.MAP))
			ticker.changeModifier(true);
		else
			ticker.changeModifier(false);
	}

	public Stage getStage() {
		return renderManager.getStage();
	}

	public Ticker getTicker(){
		return ticker;
	}
	//probando
	public void gameOver(){
		setStage(eventManager.handlePlayerDeath());
	}
	
	public void keyChange(int key) {
		setStage(eventManager.handleEvent(key, getStage()));
	}
	
	public static void main(String[] args) {
		new Manager();
	}
}