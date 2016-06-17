package theLegendOfFinn.view.menu;

import theLegendOfFinn.view.Renderer;

/**
 * Managed the selection of the game mode
 *
 */
public class ModeRenderer extends MenuRenderer implements Renderer {
	// Options to control the flow.
	public static final MenuOption CAMPAIGN = new MenuOption(0, 0, 320, 480);
	public static final MenuOption SURVIVAL = new MenuOption(320, 0, 320, 480);

	private static final String backGroundImagePath = "./Assets/menus/ModeMenu.png";

	public ModeRenderer() {
		super(ModeRenderer.backGroundImagePath);
		addOption(ModeRenderer.CAMPAIGN);
		addOption(ModeRenderer.SURVIVAL);
	}

}
