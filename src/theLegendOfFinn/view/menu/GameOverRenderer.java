package theLegendOfFinn.view.menu;

import theLegendOfFinn.view.Renderer;

/**
 * Manages the renderization of the game over view
 */
public class GameOverRenderer extends MenuRenderer implements Renderer {
	// Options to control the flow.
	public static final MenuOption MAIN_MENU = new MenuOption(198, 161, 193, 39);
	public static final MenuOption EXIT = new MenuOption(198, 223, 193, 39);

	private static final String backGroundImagePath = "./Assets/menus/GameOverMenu.png";

	public GameOverRenderer() {
		super(GameOverRenderer.backGroundImagePath);
		addOption(GameOverRenderer.MAIN_MENU);
		addOption(GameOverRenderer.EXIT);
	}
}
