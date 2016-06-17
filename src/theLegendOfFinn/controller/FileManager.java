package theLegendOfFinn.controller;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

import theLegendOfFinn.model.Ticker;

/**
 * Manages file saving capabilities. File manager is a singleton.
 */
public class FileManager {

	private Manager manager;
	
	// Overwrites constuctor
	private FileManager() {};

	private static FileManager instance;
	
	/**
	 * Creates a manager from a given one.
	 * @param manager to create from
	 */
	public static void createFileManager(Manager manager){
		getFileManager().manager = manager;
	}
	
	/**
	 * Gets the file manager or builds one.
	 * @return the file manager.
	 */
	public static FileManager getFileManager() {
		return (instance != null) ? instance : (instance = new FileManager());
	}

	/**
	 * Saves the game from a given ticker.
	 * @param ticker ticker to save.
	 */
	public void saveGame(Ticker ticker) {
		try {
			ObjectOutputStream fileStream = new ObjectOutputStream(new FileOutputStream("savegame.finn"));
			fileStream.writeObject(ticker);
			fileStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads a game from a file.
	 * @return the generated ticker.
	 * @throws ClassNotFoundException if class is not found. This should not happen.
	 */
	public Ticker loadGame() throws ClassNotFoundException {
		try {
			ObjectInputStream fileStream = new ObjectInputStream(new FileInputStream("savegame.finn"));
			Ticker ticker = (Ticker) fileStream.readObject();
			fileStream.close();
			return ticker;
		} catch (ClassNotFoundException e) {
			// This exception will never happen.
			Manager.LOGGER.log(Level.FINE, "Ticker class file is missing", e);
		} catch (FileNotFoundException e) {
			manager.loadFileMissing();
		} catch (IOException e) {
			Manager.LOGGER.log(Level.FINER, "Closing the savefile threw an exception.", e);
		}
		return null;
	}
}
