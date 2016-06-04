package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.view.Renderer;

public class PlayerCharacter extends Character {
	//Datos segun nivel de jugador?
	public static final int[] PLAYER_MAX_HP = {3, 6};
	public static final int[] PLAYER_ATTACK = {1, 2};
	public static final int[] PLAYER_VELOCITY = {2, 3};
	
	
	/*
	 * README Me parecio que no hacia falta pasarle x, y, direccion al
	 * inicializarlo ya que deberia aparecer en un lugar y direccion fija Por
	 * ejemplo en el centro y con direccion para abajo. Deje el las demas cosas
	 * por si se quiere elegir alguna dificultad que incluya tener mas/menos
	 * vida, mas/menos velocidad etc.
	 * 
	 * REspuesta: Se, no pasa nada. Cualquier cosa lo cambiamos dsp, total de
	 * ultima es agregar un par de constructores nomas.
	 */

	public PlayerCharacter(int level) {
		super(new Position(Map.WIDTH * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE,
				Map.HEIGHT * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE / 2), Direction.DOWN, PLAYER_MAX_HP[level], PLAYER_ATTACK[level], PLAYER_VELOCITY[level]);
		//super(Map.WIDTH * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE,
		//		Map.HEIGHT * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE / 2, Direction.DOWN, PLAYER_MAX_HP[level], PLAYER_ATTACK[level], PLAYER_VELOCITY[level]);
	}
}