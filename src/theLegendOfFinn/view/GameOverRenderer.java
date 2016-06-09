package theLegendOfFinn.view;

public class GameOverRenderer extends MenuRenderer implements Renderer {
	//cambiar luego.
	public static final MenuOption MAIN_MENU = new MenuOption(224, 160, 193, 50);
	public static final MenuOption EXIT = new MenuOption(224, 231, 193, 50);

	private static final String backGroundImagePath = "./Assets/menus/gameOver.png";

	public GameOverRenderer() {
		super(GameOverRenderer.backGroundImagePath);
		addOption(GameOverRenderer.MAIN_MENU);
		addOption(GameOverRenderer.EXIT);
	}
}
