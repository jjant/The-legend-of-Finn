package theLegendOfFinn.model.character;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.view.Renderer;

public class PlayerCharacter extends Character {
	private static final long serialVersionUID = 1L;

	// Datos segun nivel de jugador?
	public static final int[] PLAYER_MAX_HP = { 6, 10 };
	public static final int[] PLAYER_ATTACK = { 1, 2 };
	public static final int[] PLAYER_VELOCITY = { 5, 5 };

	public PlayerCharacter(int level) {
		super(new Position(Map.WIDTH * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE,
				Map.HEIGHT * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE / 2), Direction.DOWN, PLAYER_MAX_HP[level],
				PLAYER_ATTACK[level], PLAYER_VELOCITY[level]);
	}

	// esta feo este metodo. Sacar de aca lo de curarse(creo, meterlo en otro
	// lado)
	public boolean attack(Character character) {
		if (super.attack(character) && character.getCurrentHP() <= 0) {
			int hpRestored = getCurrentHP() + ((EnemyCharacter) character).getHPBounty();
			if (hpRestored <= getMaxHP())
				setCurrentHP(hpRestored);
			else
				setCurrentHP(getMaxHP());
			return true;
		} else
			return false;
	}

}