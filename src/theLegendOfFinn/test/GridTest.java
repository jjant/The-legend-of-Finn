package theLegendOfFinn.test;

import static org.junit.Assert.*;

import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @Before
    public void createGrid(){
        grid = new Grid();
    }


    @Test
    public void shouldAddEntity() throws PositionOccupiedException {
        Entity entity = new Entity(new Position(0,0), Entity.Direction.DOWN);
        Position pos = new Position(0,0);
        if(grid.isFreePosition(pos)) {
            try {
                grid.add(entity);
            } catch (PositionOccupiedException e) {
                e.printStackTrace();
            }
        }
        assertTrue("Entity added", grid.getEnemiesAlive() == 1);
        assertTrue("Entity not added again", grid.getEnemiesAlive() == 1);
    }

    @Test
    public void shouldntAddEntity() throws PositionOccupiedException {
        Entity entity = new Entity(new Position(0,0), Entity.Direction.DOWN);
        Position pos = new Position(0,0);
        if(grid.isFreePosition(pos)) {
            try {
                grid.add(entity);
            } catch (PositionOccupiedException e) {
                e.printStackTrace();
            }
        }
        try {
            grid.add(entity);
        } catch (PositionOccupiedException e) {
            //e.printStackTrace();
        }
        assertTrue("Entity not added again", grid.getEnemiesAlive() == 1);
    }

    @Test
    public void shouldRemoveEntity() throws PositionOccupiedException {
        Position pos = new Position(0,0);
        if(grid.isFreePosition(pos)) {
            Entity entity = new Entity(new Position(0,0), Entity.Direction.DOWN);
            grid.add(entity);
        }
        grid.freePosition(pos);
        assertTrue("freePosition succesfull", grid.isFreePosition(pos));
    }


}
