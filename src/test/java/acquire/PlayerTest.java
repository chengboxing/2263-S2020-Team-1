package acquire;

import org.junit.*;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void playerTestOne(){
        fail();
    }

    @Test
    public void negativeNetWorthTest(){
        Player p = new Player("p");
        p.getWorth();
        fail();
    }

    @Test
    public void maxTiles(){
        fail();
    }
}
