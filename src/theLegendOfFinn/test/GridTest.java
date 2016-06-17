package theLegendOfFinn.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import theLegendOfFinn.model.entity.Entity;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;
import theLegendOfFinn.model.gameData.Grid;
import theLegendOfFinn.model.utils.Position;

/**
 * Created by joaquin on 16/06/16.
 */
public class GridTest {
    private Grid grid;
    private Position pos;
    Entity entity;
    @Before
    public void createGrid(){
        grid = new Grid();
        pos = new Position(0,0);
        entity = new Entity(new Position(0,0), Entity.Direction.DOWN);
    }

    @Test
    public void isFreeWorksCorrectly() throws PositionOccupiedException {
    	grid.add(entity);
    	assertFalse(grid.isFree(pos));
    	assertTrue(grid.isFree(new Position(32, 32)));
    }
    
    @Test
    public void shouldAddEntity() throws PositionOccupiedException {
        
        if(grid.isFree(pos)) grid.add(entity);
        assertTrue("Entity added", grid.getEnemiesAlive() == 1);
        assertTrue("Entity not added again", grid.getEnemiesAlive() == 1);
    }

    @Test
    public void shouldntAddEntity() throws PositionOccupiedException {
        if(grid.isFree(pos)) grid.add(entity);
        assertTrue("Entity not added again", grid.getEnemiesAlive() == 1);
    }

    @Test
    public void shouldRemoveEntity() throws PositionOccupiedException {
        if(grid.isFree(pos)) {
            grid.add(entity);
        }
        grid.freePosition(pos);
        assertTrue("freePosition succesfull", grid.isFree(pos));
    }
    
    @Test
    public void shouldGetEntity() throws PositionOccupiedException {
    	grid.add(entity);
    	assertEquals(grid.getEntity(pos), entity);
    }
    
    @Test
    public void enemiesLeftWorksCorrectly() throws PositionOccupiedException {
    	assertFalse(grid.areEnemiesLeft());
    	grid.add(entity);
    	assertTrue(grid.areEnemiesLeft());
    }
}
