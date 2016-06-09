package theLegendOfFinn.view;

public class StartingMenuRenderer extends MenuRenderer implements Renderer {
	public static final MenuOption NEW = new MenuOption(34, 314, 164, 60);
	public static final MenuOption LOAD = new MenuOption(456, 314, 164, 60);

	private static final String backGroundImagePath = "./Assets/menus/main.png";

	public StartingMenuRenderer() {
		super(StartingMenuRenderer.backGroundImagePath);
		addOption(StartingMenuRenderer.NEW);
		addOption(StartingMenuRenderer.LOAD);
	}
}
