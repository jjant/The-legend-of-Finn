package theLegendOfFinn.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import java.util.Iterator;
import java.util.ArrayList;

import theLegendOfFinn.controller.Delegate;
import theLegendOfFinn.controller.Manager;
//import theLegendOfFinn.model.character.Character.CharacterType;

public class MasterRenderer extends Canvas implements KeyListener {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH * 3 / 4;
	public static final String TITLE = "The legend of Finn";

	// probando
	private Delegate delegate;

	private JFrame frame;
	private BufferStrategy bs;
	private Graphics g;
	private ArrayList<CharacterRenderer> charactersR;
	// private CharacterRenderer playerR;
	private MapRenderer mapRenderer;
	private MenuRenderer menuRenderer;
	private PauseRenderer pauseRenderer;

	public MasterRenderer(Delegate delegate) {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// probando
		this.delegate = delegate;

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

		charactersR = new ArrayList<CharacterRenderer>();
	}

	public void render(Manager.Stage stage) {

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
			Iterator<CharacterRenderer> iter = charactersR.iterator();
			while (iter.hasNext())
				iter.next().render(g);
			break;
		case PAUSE:
			pauseRenderer.render(g);
			break;
		}
		/*
		 * if (stage.equals(Manager.Stage.MENU)) { menuR.render(g); } else if
		 * (stage.equals(Manager.Stage.MAP)){ mapR.render(g); playerR.render(g);
		 * } else if (stage.equals(Manager.Stage.PAUSE)){ pauseR.render(g); }
		 */
		bs.show();
		g.dispose();
	}

	public void setMapRenderer(MapRenderer mapR) {
		this.mapRenderer = mapR;
	}

	
	/*
	 * se deberian renderizar todos los characters con un iterador.
	 * MasterRenderer no deberia hacer distincion entre player y enemy.
	 */
	public void addCharacterRenderer(CharacterRenderer characterR) {
		charactersR.add(characterR);
		// this.playerR = playerR;
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
		System.out.println("key pressed");
		delegate.passKeyPressed(keyCode);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}