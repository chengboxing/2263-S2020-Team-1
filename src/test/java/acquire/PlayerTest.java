package acquire;

import org.junit.*;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void playerIdTest1(){
        Player p = new Player("p");
        assertEquals("Didn't create single character name correctly", "p", p.getID());
    }

    @Test
    public void playerIdTest2(){
        Player p = new Player("p l");
        assertEquals("Didn't handle spaces correctly", "p l", p.getID());
    }

    @Test
    public void playerIdTest3(){
        Player p = new Player("1bh+-=/as{]'\"^%;");
        assertEquals("Didn't handle random Characters correctly", "1bh+-=/as{]'\"^%;", p.getID());
    }

    @Test
    public void negativeNetWorthTest(){
        Player p = new Player("p");
        int subtraction = p.getWorth();
        p.subtractMoney(subtraction + 100);
        assertEquals("Was able to get a negative worth", 0, p.getWorth());
    }

    @Test
    public void maxTiles(){
        Dealer d = Dealer.getDealerInstance();
        fail();
    }
}
