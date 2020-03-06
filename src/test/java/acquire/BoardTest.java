package acquire;

import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void boardCanPlayTest(){
        fail();
    }

    @Test
    public void boardCreationTest(){
        Board b = new Board();
        TileFactory tf = TileFactory.getTileFactory();
        Tile[][] tiles = new Tile[9][12];
        for (char c = 'A'; c < 'J'; c++){
            for (int r = 0; r < 12; r++){
                assertEquals(tf.getTile("c"+"r"), c+r);

            }
        }

    }

    @Test
    public void boardMaxChainTest(){
        fail();
    }
}
