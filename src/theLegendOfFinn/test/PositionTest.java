package theLegendOfFinn.test;

import org.junit.Before;
import org.junit.Test;
import theLegendOfFinn.model.entity.Entity;
import theLegendOfFinn.model.gameData.Map;
import theLegendOfFinn.model.utils.Position;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by joaquin on 16/06/16.
 */
public class PositionTest {
    Position pos;

    @Before
    public void createPosition() {
        pos = new Position(0,0);
    }

    @Test
    public void checkToGridIndexesNull() {
        assertTrue(pos.toGridIndexes(null) == null);
    }

    @Test
    public void checkToGridIndexesAvailablePos() {
        assertTrue(pos.toGridIndexes(Entity.Direction.DOWN).equals(new Position(0,1)));
    }

    @Test
    public void checkToGridIndexesNotAvailablePos() {
        assertTrue(pos.toGridIndexes(Entity.Direction.UP) == null);
    }

    @Test
    public void shouldBeNearby() {
        Position pos2 = new Position(Map.CELL_SIZE,0);
        assertTrue(pos.isNearby(pos2));
    }

    @Test
    public void shouldntBeNearby() {
        Position pos2 = new Position(Map.CELL_SIZE+1,0);
        assertFalse(pos.isNearby(pos2));
    }
}
