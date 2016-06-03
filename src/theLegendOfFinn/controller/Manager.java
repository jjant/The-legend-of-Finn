package theLegendOfFinn.controller;

import java.util.ArrayList;
import java.util.Iterator;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.EnemyFactory;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.view.MapRenderer;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.MenuRenderer;
import theLegendOfFinn.view.PauseRenderer;
import theLegendOfFinn.view.RenderFactory;
import theLegendOfFinn.view.Renderer;

// VER DE MODULARIZAR
public class Manager implements Runnable {
	public enum Stage {
		MENU, MAP, PAUSE;
	}
	
	private boolean running;
	private Thread thread;
	
	// Cambiar la manera de realacionar con Delegate
	private MasterRenderer masterRenderer;
	private EnemyFactory enemyFactory;
	private TimeManager timeManager;
	//probando
	private EventManager eventManager;
	
	// Esto va aca??
	// Respuesta: No creo. No se donde todavia.
	private RenderFactory renderFactory;

	// Data necesaria(?) del modelo.
	private Ticker ticker;
	private Stage stage;

	public Manager() {
		ticker = new Ticker(new Map(new PlayerCharacter(1)));

		masterRenderer = new MasterRenderer(new Delegate(this));

		timeManager = new TimeManager();
		eventManager = new EventManager(masterRenderer, ticker);

		/*
		 * Lo hago asi nomas para probar tema enemigos, creo que convendria
		 * tener un CharacterManager que maneje todo lo relacionado con el
		 * spawning, removal etc.
		 */
		//Cambiar luego, estoy de acuerdo con el comment de arriba.
		enemyFactory = new EnemyFactory();
		
		// No se si esto va aca pero por ahora..
		renderFactory = new RenderFactory();

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
		
		//para que todo esto? porq no lo haces en el constructor de master rend?

		masterRenderer.setMapRenderer(new MapRenderer(ticker.getMap()));
		masterRenderer.setMenuRenderer(new MenuRenderer());
		masterRenderer.setPauseRenderer(new PauseRenderer());
		
		running = true;
		
		setStage(Stage.MENU);
		
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		while (running) {
			if (timeManager.update()) {
				// Cambiar dsp
				tick(timeManager.getDelta(), 9);
				render();
			}
		}
	}

	// Probando...
	public void tick(double delta, int key) {
		// Aca deberia hacer los cambios en el modelo.
	}

	public void render() {
		masterRenderer.render(stage);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void keyChange(int key) {
		setStage(eventManager.handleEvent(key, stage));
	}

	
	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.initialize();
	}
}
