package theLegendOfFinn.controller;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.communicators.Delegate;
import theLegendOfFinn.controller.communicators.Notifier;
import theLegendOfFinn.controller.exceptions.TickerMissingException;
import theLegendOfFinn.model.Ticker;
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
		masterRenderer = new MasterRenderer(new Delegate(this));
		renderManager = new RenderManager(this);
		eventManager = new EventManager(this);
		renderManager.initialize();
	}

	/** Initializes the game. Must be called when a new game
	 *  is requested or a game is to be loaded. 
	 *  Calling this method before having a ticker set is unsafe and will throw an exception.
	 *  
	 */
	public void initialize() throws TickerMissingException{
		if(ticker == null)
			throw new TickerMissingException("Ticker has not been set.");
		modelManager = new ModelManager(this);
		modelManager.initialize();
		masterRenderer.initialize();
	}

	public void setStage(Stage stage) {
		renderManager.setStage(stage);
		if (!(stage.equals(Stage.MENU) || stage.equals(Stage.MAPSELECTION)))
			toggleMovement();
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

	/**	Sets the current ticker to the one specified.
	 * 	This method should be called if and only if the game is to be loaded.
	 * 
	 * @param ticker The loaded ticker.
	 */
	public void loadTicker(Ticker ticker) {
		this.ticker = Ticker.loadTicker(ticker);
		this.ticker.setNotifier(notifier);

	}

	public Notifier getNotifier() {
		return notifier;
	}

	public static void main(String[] args) {
		new Manager();
	}

}