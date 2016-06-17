package theLegendOfFinn.test;

import static org.junit.Assert.*;

import org.junit.Test;

import theLegendOfFinn.model.entity.character.enemy.EnemyCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyPenguin;
import theLegendOfFinn.model.entity.character.enemy.boss.Boss;
import theLegendOfFinn.model.gameData.Round;
import theLegendOfFinn.model.utils.Position;

public class RoundTest {

	@Test
	public void addingARoundWorks() {
		Round round = new Round(Round.RoundType.NORMAL);
		EnemyCharacter penguin = new EnemyPenguin(new Position());
		
		assertTrue(round.enemiesLeft());
		assertTrue(round.getRoundEnemies().size() == 4);
		
		// Enemies should be the simplest enemy.
		for (EnemyCharacter enemy: round.getRoundEnemies())
			assertEquals(enemy.getClass(), penguin.getClass());
	}
	
	@Test
	public void survivalRoundWorks() {
		Round round = new Round(Round.RoundType.SURVIVAL, 20);
		assertTrue(round.getRoundEnemies().size() == 21);
	}
	
	@Test
	public void bossRoundWorks()  {
		Round round = new Round(Round.RoundType.BOSS);
		EnemyCharacter boss = new Boss();
		assertTrue(round.getRoundEnemies().size() == 1);
		
		for (EnemyCharacter enemy: round.getRoundEnemies())
			assertEquals(enemy.getClass(), boss.getClass());
			
	}

}
