package theLegendOfFinn.controller;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.communicators.Delegate;
import theLegendOfFinn.controller.communicators.Notifier;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.view.MapRenderer;
import theLegendOfFinn.view.MasterRenderer;

public class Manager {
	private MasterRenderer masterRenderer;

	private RenderManager renderManager;
	private EventManager eventManager;
	private ModelManager modelManager;

	private Notifier notifier;

	private Ticker ticker;


	public Manager() {
		notifier = new Notifier(this);
		//mostrar menu
		masterRenderer = new MasterRenderer(new Delegate(this));
		renderManager = new RenderManager(this);
		eventManager = new EventManager(this);
		
		renderManager.initialize();
	}

	//cambiar nombre al metodo, esto hace q se vea el juego.
	/**
	 * Initializes the game. Must be called when a new game is requested.
	 * 
	 */
	public void initialize() {
		modelManager = new ModelManager(this);
		modelManager.initialize();
		masterRenderer.initialize();
	}

	public void setStage(Stage stage) {
		renderManager.setStage(stage);
		if(!stage.equals(Stage.MENU))
			toggleMovement();
	}

	public void loadTicker(Ticker ticker) {
		this.ticker = ticker;
	}

	public void toggleMovement() {
		if (getStage().equals(RenderManager.Stage.MAP))
			ticker.changeModifier(true);
		else
			ticker.changeModifier(false);
	}

	public Stage getStage() {
		return renderManager.getStage();
	}

	public Ticker getTicker() {
		return ticker;
	}

	public void gameOver() {
		setStage(eventManager.handlePlayerDeath());
	}

	public void keyChange(int key) {
		setStage(eventManager.handleEvent(key, getStage()));
	}

	public MasterRenderer getMasterRenderer() {
		return masterRenderer;
	}
	//should ONLY be called when game is loaded
	public void setTicker(Ticker ticker) {
		this.ticker = ticker;
		ticker.setNotifier(notifier);
		
	}

	public Notifier getNotifier() {
		return notifier;
	}

	public static void main(String[] args) {
		new Manager();
	}

}