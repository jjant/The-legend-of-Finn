package theLegendOfFinn.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import theLegendOfFinn.model.entity.Entity.Direction;
import theLegendOfFinn.view.Renderer;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyDog;
import theLegendOfFinn.model.entity.character.Character;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;
import theLegendOfFinn.model.gameData.Map;
import theLegendOfFinn.model.utils.Position;

public class CharacterTest {
	private PlayerCharacter player;
	private EnemyCharacter enemy;

	@Before
	public void createCharacters() {
		player = new PlayerCharacter();
		enemy = new EnemyDog(new Position(Map.WIDTH * Renderer.CELL_SIZE / 2,
				Map.HEIGHT * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE / 2), Direction.LEFT);
	}

	@Test
	public void playerShouldDie() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			enemy.attack(player);
			Thread.sleep(510);
		}
		assertFalse("Player died when killed by the enemy.", player.isAlive());
	}

	@Test
	public void enemyShouldDie() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			player.attack(enemy);
			Thread.sleep(510);
		}
		assertFalse("Enemy died when killed by the Player.", enemy.isAlive());
	}

	@Test
	public void shouldNotFlagellate() throws InterruptedException {
		int playerHP = player.getCurrentHP();
		int enemyHP = enemy.getCurrentHP();

		player.attack(player);
		Thread.sleep(510);
		enemy.attack(enemy);
		Thread.sleep(510);

		assertTrue("Player dont hurt himself", player.getCurrentHP() == playerHP);
		assertTrue("Enemy dont hurt himself", enemy.getCurrentHP() == enemyHP);
	}

	@Test
	public void shouldNotAttackNull() {
		player.attack(null);
		enemy.attack(null);
		// If throws an exception, the test fails.
	}

	@Test
	public void playerShouldRecoverLife() throws InterruptedException {
		for (int i = 0; i < 2; i++) {
			enemy.attack(player);
			Thread.sleep(510);
		}

		int playerHP = player.getCurrentHP();
		assertTrue("Player loss life", playerHP < player.getMaxHP());

		for (int i = 0; i < 5; i++) {
			player.attack(enemy);
			Thread.sleep(510);
		}
		boolean recoveredCorrectLife = player.getCurrentHP() == playerHP + enemy.getHPBounty()
				|| player.getCurrentHP() == player.getMaxHP();
		assertTrue("Player recovers life from enemy", recoveredCorrectLife);
	}

	@Test
	public void closeEnoughIsAccurate() {
		EnemyCharacter newEnemy = new EnemyDog(new Position(0, 0), Direction.LEFT);
		assertFalse("Some enemies are far away", newEnemy.closeEnough(player, 1));
		assertTrue("Some enemies are close", enemy.closeEnough(player, 1));
	}

	@Test
	public void playerShouldMove() throws InterruptedException {
		Position position = new Position(player.getPosition());
		Map map = new Map(player);

		player.tryToMove(Direction.DOWN, map.getGrid());
		for (int i = 0; i < 33; i++) {
			player.move();
			Thread.sleep(20);
		}

		boolean movedCorrectly = player.getPosition().equals(new Position(position.getX(), position.getY() + 32));
		assertTrue("Player moved correctly", movedCorrectly);
	}

	@Test
	public void enemyShouldMove() throws InterruptedException {
		Position position = new Position(enemy.getPosition());
		ArrayList<EnemyCharacter> enemies = new ArrayList<>();
		enemies.add(enemy);

		Map map = new Map(player, enemies);

		enemy.tryToMove(Direction.DOWN, map.getGrid());
		for (int i = 0; i < 33; i++) {
			enemy.move();
			Thread.sleep(20);
		}

		boolean movedCorrectly = enemy.getPosition().equals(new Position(position.getX(), position.getY() + 32));
		assertTrue("Enemy moved correctly", movedCorrectly);
	}

	@Test
	public void playerShouldntMove() {

		Map map = new Map(player);
		try {
			map.getGrid().add(enemy);
		} catch (PositionOccupiedException e) {
			e.printStackTrace();
		}
		player.tryToMove(Direction.RIGHT, map.getGrid());
		assertFalse("Player shouldn't move to occupied position", player.getState() == Character.MOVING);
	}

	@Test
	public void enemyShouldntMove() {

		Map map = new Map(player);
		try {
			map.getGrid().add(enemy);
		} catch (PositionOccupiedException e) {
			e.printStackTrace();
		}
		enemy.tryToMove(Direction.LEFT, map.getGrid());
		assertFalse("Player shouldn't move to occupied position", enemy.getState() == Character.MOVING);
	}
}
