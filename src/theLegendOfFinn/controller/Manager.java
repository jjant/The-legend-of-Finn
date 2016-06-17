package theLegendOfFinn.controller;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.communicators.Delegate;
import theLegendOfFinn.controller.communicators.Notifier;
import theLegendOfFinn.controller.exceptions.TickerMissingException;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.view.MasterRenderer;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Controls the flow of the entire game, every event is either resolved by this
 * class or one of the sub-managers.
 * 
 * @author LCDPCJL
 *
 */
public class Manager {
	public static final Logger LOGGER = initializeLogger();

	private MasterRenderer masterRenderer;

	private RenderManager renderManager;
	private EventManager eventManager;
	private ModelManager modelManager;

	private Notifier notifier;

	private Ticker ticker;

	private static Logger initializeLogger() {
		Logger logger = Logger.getAnonymousLogger();
		String logPath = "./Log/log";
		logger.setUseParentHandlers(false);
		try {
			FileHandler fh = new FileHandler(logPath);
			logger.addHandler(fh);
			logger.setLevel(Level.FINEST);
			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException | IOException e) {
			// It's okay for the program to blow up at this point,
			// as if the logger could not be loaded, no exceptions will be
			// registered properly during the execution.
			e.printStackTrace();
		}
		return logger;
	}

	public Manager() {
		notifier = new Notifier(this);
		FileManager.createFileManager(this);
		masterRenderer = new MasterRenderer(new Delegate(this));
		renderManager = new RenderManager(this);
		eventManager = new EventManager(this);

		renderManager.initialize();
	}

	/**
	 * Initializes the game. Must be called when a new game is requested or a
	 * game is to be loaded. Calling this method before having a ticker set is
	 * unsafe and will throw an exception.
	 */
	public void initialize() throws TickerMissingException {
		if (ticker == null)
			throw new TickerMissingException("Ticker has not been set.");
		ticker.setNotifier(notifier);
		modelManager = new ModelManager(this);
		modelManager.initialize();
		masterRenderer.initialize();
	}

	/**
	 * Updates the game's stage, if the stage
	 * 
	 * @param stage
	 */
	public void updateStage(Stage stage) {
		renderManager.setStage(stage);
		if (!(stage.equals(Stage.MENU) || stage.equals(Stage.MAPSELECTION) || stage.equals(Stage.MODE)))
			toggleMovement();
	}

	public void toggleMovement() {
		Stage stage = getStage();
		if (stage.equals(Stage.MAP))
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
		updateStage(eventManager.handlePlayerDeath());
	}

	public void keyChange(int key) {
		updateStage(eventManager.handleEvent(key, getStage()));
	}

	public MasterRenderer getMasterRenderer() {
		return masterRenderer;
	}

	/**
	 * Sets the current ticker to the one specified. This method should be
	 * called if and only if the game is to be loaded.
	 * 
	 * @param ticker
	 *            The loaded ticker.
	 */
	public void loadTicker(Ticker ticker) {
		this.ticker = Ticker.loadTicker(ticker);
	}

	public Notifier getNotifier() {
		return notifier;
	}

	public void loadFileMissing() {
		masterRenderer.displayFileMissing();
	}

	public static void main(String[] args) {
		new Manager();
	}

}