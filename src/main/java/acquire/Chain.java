package acquire;

import java.util.LinkedList;

public class Chain {
    private String name;
    private LinkedList tilesInChain;
    private int stocksOwned;

    //This method checks to see if stocks are available under a certain chain and can be sold to the player.
    private boolean canSellStock(){
        return false;
    }
    
    //This method merges two or more hotel chains into the one that is bigger.
    //targetChain is the chain to merge into
    public void merge(Chain targetChain){
        //this chain should no longer exist, and the chain merged into should be the size of both put together.
    }

    public int chainSize(){
        return this.tilesInChain.size();
    }

    public void addTile(Tile t){
        this.tilesInChain.add(t);
    }


}
