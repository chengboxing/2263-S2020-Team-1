package acquire;

import java.util.LinkedList;

public class Dealer {
    Tile[][] pile;
    LinkedList playerOrder = new LinkedList(); //Needs to be changed to CircularlyLinkedList
    Player currentPlayer = null;

    //Dealer checks and assigns an eligible tile to a player after they place a tile on the board. 
    public Tile dealTile(){
        return null;
    }
    
    //Dealer removes the tile, which has been assigned to a player, from the pile in the bank.
    public void removeFromPile(){

    }

    //After a player plays their turn, they end the turn and the dealer moves the turn over to the
    //next player.
    public void moveTurn(){

    }


    public void addPlayer(Player p){
        this.playerOrder.add(p);
    }


    public Player getPlayer(){
        return null;
    }


}
