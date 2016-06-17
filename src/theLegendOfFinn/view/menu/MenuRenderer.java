package theLegendOfFinn.view.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.Renderer;

/**
 * Parent of all the menu renderers in the game.
 * 
 * Poner algo mas...
 */
public abstract class MenuRenderer implements Renderer {

	private List<MenuOption> options;
	private BufferedImage backGroundImage;
	private MenuOption option;

	public MenuRenderer(String backGroundImagePath) {
		options = new ArrayList<>();
		try {
			this.backGroundImage = ImageIO.read(new File(backGroundImagePath));
		} catch (IOException e) {
			Manager.LOGGER.log(Level.FINE, "Couldn't read background image", e);
		}
	}

	/**
	 * Draws the menu to the given graphics buffer.
	 * 
	 * @param g
	 *            The graphics onto which the menu is going to be drawn.
	 */
	public void render(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, MasterRenderer.WIDTH, MasterRenderer.HEIGHT, null);
		g.setColor(Color.RED);
		g.drawRect(option.getX(), option.getY(), option.getWidth(), option.getHeight());
	}

	/**
	 * Sets the currently selected option to next one.
	 */
	public void nextOption() {
		selectOption(1);
	}

	/**
	 * Sets the currently selected option to the previous one.
	 */
	public void previousOption() {
		selectOption(-1);
	}

	/**
	 * Sets the currently selected option to the one <code>i</code> places
	 * after.
	 * 
	 * @param i
	 *            the number of places to be skipped.
	 */
	public void selectOption(int i) {
		int nextIndex = (options.indexOf(option) + i) % options.size();
		if (nextIndex < 0)
			nextIndex += options.size();
		this.option = options.get(nextIndex);
	}

	/**
	 * Returns the currently selected option.
	 * 
	 * @return Currently selected option.
	 */
	public MenuOption getOption() {
		return option;
	}

	/**
	 * Adds an option object to the menu.
	 * 
	 * @param option
	 *            The option to be added.
	 */
	public void addOption(MenuOption option) {
		options.add(option);
		if (options.size() == 1)
			this.option = option;
	}

}
