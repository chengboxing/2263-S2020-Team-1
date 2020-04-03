package acquire;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DealerTest {

    @Test
    public void dealerTestOne(){
        fail();
    }

    @Test
    public void dealTile() {
    }

    @Test
    public void removeFromPile() {
    }

    @Test
    public void moveTurn() {
        Dealer d = new Dealer();
        Player[] players = new Player[3];
        for (int i = 0; i <3; i++){
            players[i] = new Player(i);
        }
        for (int i = 0; i <3; i++){
            assertEquals(players[i], d.getPlayer());
            moveTurn();
        }

    }
}
