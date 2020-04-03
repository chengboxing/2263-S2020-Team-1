package acquire;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DealerTest {

    @Test
    public void dealAllTilesTest(){
        //deal 108 tiles
        //try to deal a 109th tile
        fail();
    }

    @Test
    public void duplicateTileDealtTest(){
        //deal a tile, find that same tile in the board, and see if they are both undealable
        fail();
    }

    @Test
    public void dealTileTests() {
    }

    @Test
    public void removeFromPileTests() {
    }

    @Test
    public void moveTurnTest() {
        Dealer d = new Dealer();
        Player[] players = new Player[3];
        for (int i = 0; i < 3; i++) {
            players[i] = new Player(Integer.toString(i));
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(players[i], d.getPlayer());
            d.moveTurn();
        }
    }

    @Test
    public void moveTurnTest(){
        fail();
    }
}
