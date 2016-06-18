package theLegendOfFinn.view.menu;

import theLegendOfFinn.view.Renderer;

/**
 * Manages the map selection renderization
 */
public class MapSelectionRenderer extends MenuRenderer implements Renderer {
	// Options to control the flow.
	public static final MenuOption GRASS = new MenuOption(71, 89, 214, 161);
	public static final MenuOption ICE = new MenuOption(335, 89, 214, 161);
	public static final MenuOption MOUNTAIN = new MenuOption(71, 288, 214, 161);
	public static final MenuOption LAVA = new MenuOption(335, 288, 214, 161);
	
	private static final String backGroundImagePath = "./Assets/menus/MapSelect.png";

	public MapSelectionRenderer() {
		super(MapSelectionRenderer.backGroundImagePath);
		addOption(MapSelectionRenderer.GRASS);
		addOption(MapSelectionRenderer.ICE);
		addOption(MapSelectionRenderer.MOUNTAIN);
		addOption(MapSelectionRenderer.LAVA);
	}

}
