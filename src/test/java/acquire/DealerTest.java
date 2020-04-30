package acquire;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class DealerTest {

    @Test
    public void dealAllTilesTest(){
        //deal 108 tiles
        //try to deal a 109th tile <-- dealAllTilesTest_2
        //fail();
        Dealer d = Dealer.getDealerInstance();
        LinkedList<Tile> l = new LinkedList<>();
        for (int i = 0; i<108; i++){
            l.add(d.dealTile());
        }
        assertEquals(108, l.size());
    }

    @Test
    public void dealAllTilesTest_2(){
        Dealer d = Dealer.getDealerInstance();
        LinkedList<Tile> l = new LinkedList<>();
        for (int i = 0; i<108; i++){
            l.add(d.dealTile());
        }
        l.add(d.dealTile());
        assertEquals(null, l.get(108));
    }

    @Test
    public void duplicateTileDealtTest(){
        //deal all 108 tiles, and make sure there are no duplicates
        //fail();

        Dealer d = Dealer.getDealerInstance();
        ArrayList<String> l = new ArrayList<>();
        for (int i = 0; i<108; i++){
            l.add(d.dealTile().getLocation());
        }

        Set<String> temp = new HashSet();
        for(String value : l){
            if(!temp.contains(value)){
                temp.add(value);
            }
        }

        assertEquals(108, temp.size());
    }

    @Test
    public void dealTileTest() {
        Dealer dealer = Dealer.getDealerInstance();

        LinkedList pile = new LinkedList<Tile>();
        //creates the pile
        for (char c = 'A'; c < 'J'; c++){
            for (int r = 1; r < 13; r++){
                //Tile t = new Tile(c +Integer.toString(r));
                pile.add(c +Integer.toString(r));
            }
        }
        Tile tile = dealer.dealTile();
        //System.out.println(tile.getLocation());
        for (char c = 'A'; c < 'J'; c++){
            for (int r = 1; r < 13; r++){
                if (tile.getLocation().equals(c +Integer.toString(r)))
                    pile.remove(c +Integer.toString(r));
            }
        }
        assertEquals(107, pile.size());
    }


    @Test
    public void moveTurnTest() {
        Dealer d = Dealer.getDealerInstance();
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
    public void addPlayerTest() {
        Dealer d = Dealer.getDealerInstance();
        assertEquals(0, d.getPlayerList().size());

        d.addPlayer(new Player("p1"));
        d.addPlayer(new Player("p2"));
        assertEquals(2, d.getPlayerList().size());

    }

    @Test
    public void getPlayerTest() {
        Dealer d = Dealer.getDealerInstance();
        assertEquals(0, d.getPlayerList().size());

        d.addPlayer(new Player("p1"));
        d.addPlayer(new Player("p2"));
        assertEquals("p1", d.getPlayer().getID());

    }
    
}
