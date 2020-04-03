package acquire;

import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void boardCanPlayTest(){
        //requires chains to be implemented.
        fail();
    }

    @Test
    public void boardCreationTest(){
        Board b = new Board();
        TileFactory tf = TileFactory.getTileFactory();

        for (char c = 'A'; c < 'J'; c++){
            for (int r = 1; r <= 11; r++){
                assertEquals((c +Integer.toString(r)), b.getTile(c + Integer.toString(r)).getLocation());
            }
        }

    }

    @Test
    public void boardMaxChainTest(){
        //create 7 chains
        //attempt to create another, but will not get created
        fail();
    }
}
