package theLegendOfFinn.view.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.Renderer;

public abstract class MenuRenderer implements Renderer {

	private List<MenuOption> options;
	private BufferedImage backGroundImage;
	private MenuOption option;

	public MenuRenderer(String backGroundImagePath) {
		options = new ArrayList<>();
		try {
			this.backGroundImage = ImageIO.read(new File(backGroundImagePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, MasterRenderer.WIDTH, MasterRenderer.HEIGHT, null);
		g.setColor(Color.RED);
		g.drawRect(option.getX(), option.getY(), option.getWidth(), option.getHeight());
	}

	// NO ANDA NO OSE PORQ
	public void nextOption() {
		int nextIndex = options.indexOf(option) + 1;
		if (nextIndex >= options.size()) {
			this.option = options.get(0);
		} else {
			this.option = options.get(nextIndex);
		}
	}

	// ESTE SI ANDA
	public void previousOption() {
		int prevIndex = options.indexOf(option) - 1;
		if (prevIndex < 0)
			option = options.get(options.size() - 1);
		else
			option = options.get(prevIndex);
	}

	public MenuOption getOption() {
		return option;
	}

	public void addOption(MenuOption option) {
		options.add(option);
		if (options.size() == 1)
			this.option = option;
	}
}
