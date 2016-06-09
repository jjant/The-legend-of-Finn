package theLegendOfFinn.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import theLegendOfFinn.model.Ticker;

public class FileManager {
	private FileManager(){};
	private static FileManager instance;
	
	public static FileManager getFileManager(){
		return (instance != null) ? instance : (instance = new FileManager());
	}
	
	public void saveGame(Ticker ticker) {
		try {
			ObjectOutputStream fileStream = new ObjectOutputStream(new FileOutputStream("savegame.finn"));
			fileStream.writeObject(ticker);
			fileStream.close();
			System.out.println("SAVED!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Me genero banda de catchs automaticamente para salvar errores.
	public Ticker loadGame() throws ClassNotFoundException {
		try {
			ObjectInputStream fileStream = new ObjectInputStream(new FileInputStream("savegame.finn"));
			Ticker ticker = (Ticker)fileStream.readObject();
			fileStream.close();
			return ticker;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
