package theLegendOfFinn.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import theLegendOfFinn.controller.Delegate;
import theLegendOfFinn.controller.RenderManager;
import theLegendOfFinn.model.character.EnemyCharacter;

public class MasterRenderer extends Canvas implements KeyListener {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH * 3 / 4;
	public static final String TITLE = "The legend of Finn";

	private Delegate delegate;

	private JFrame frame;
	private BufferStrategy bs;
	private Graphics g;

	private CharacterRenderer characterRenderer;
	private MapRenderer mapRenderer;
	private MenuRenderer menuRenderer;
	private PauseRenderer pauseRenderer;

	public MasterRenderer(Delegate delegate) {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.delegate = delegate;

		characterRenderer = new CharacterRenderer();
		menuRenderer = new MenuRenderer();
		pauseRenderer = new PauseRenderer();

		frame = new JFrame();
		addKeyListener(this);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		requestFocus();
	}

	public void render(RenderManager.Stage stage) {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		switch (stage) {
		case MENU:
			menuRenderer.render(g);
			break;
		case MAP:
			mapRenderer.render(g);
			characterRenderer.draw(delegate.getPlayer());
			
			for (EnemyCharacter enemy : delegate.getEnemies())
				characterRenderer.draw(enemy);

			characterRenderer.render(g);
			characterRenderer.dispose();
			break;
		case PAUSE:
			pauseRenderer.render(g);
			break;
		}

		bs.show();
		g.dispose();
	}

	public void setMapRenderer(MapRenderer mapR) {
		this.mapRenderer = mapR;
	}

	public void setMenuRenderer(MenuRenderer menuR) {
		this.menuRenderer = menuR;
	}

	public void setPauseRenderer(PauseRenderer pauseRenderer) {
		this.pauseRenderer = pauseRenderer;
	}

	/**
	 * Returns the current MenuRenderer
	 * 
	 * @return menuRenderer - Current menu renderer.
	 */
	public MenuRenderer getMenuRenderer() {
		return menuRenderer;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// System.out.println("key pressed");
		delegate.passKeyPressed(keyCode);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}