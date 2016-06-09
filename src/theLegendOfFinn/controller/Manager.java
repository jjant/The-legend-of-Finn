package theLegendOfFinn.controller;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.communicators.Communicator;
import theLegendOfFinn.controller.communicators.Delegate;
import theLegendOfFinn.controller.communicators.Notifier;
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

	private Communicator communicator;

	private Ticker ticker;

	private boolean firstRun = true;

	public Manager() {
		communicator = new Communicator(this);
		initialize(false);
	}

	/**
	 * Initializes the game. Must be called when a new game is requested.
	 * 
	 */
	public void initialize(boolean newGame) {
		if (firstRun) {
			firstRun = false;

			ticker = new Ticker(new Notifier(this));

			masterRenderer = new MasterRenderer(new Delegate(this));
			masterRenderer.initialize();

			renderManager = new RenderManager(masterRenderer);
			eventManager = new EventManager(masterRenderer, ticker, communicator);
			modelManager = new ModelManager(ticker);

			renderManager.initialize();
		}
		if(newGame){
			ticker.renew();
			masterRenderer.initialize();
		}
			
	}

	public void setStage(Stage stage) {
		renderManager.setStage(stage);
		changeModTick();
	}

	public void loadTicker(Ticker ticker) {
		this.ticker = ticker;
	}

	// que hace esto??
	// RTA: para el thread del modelo si stage no es MAP
	public void changeModTick() {
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

	// probando
	public void gameOver() {
		setStage(eventManager.handlePlayerDeath());
	}

	public void keyChange(int key) {
		setStage(eventManager.handleEvent(key, getStage()));
	}

	public static void main(String[] args) {
		new Manager();
	}
}