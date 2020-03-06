package acquire;

import org.junit.*;
import static org.junit.Assert.*;

public class TileTest {

    @Test
    public void tileLocationTest(){
        TileFactory tf = TileFactory.getTileFactory();
        Tile actual = tf.getTile("a1");
        assertEquals("Does not return the location correctly", "a1", actual.getLocation());
    }

    @Test
    public void tileCreationTest(){
        TileFactory tf = TileFactory.getTileFactory();
        Tile expected = tf.getTile("a3");
        Tile other = tf.getTile("a3");
        assertEquals("The tiles are not the same object", expected, other);
    }

    @Test
    public void tileMergeTest(){
        fail();
    }
}
