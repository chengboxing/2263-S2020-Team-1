package acquire;

import java.util.LinkedList;
import javafx.scene.paint.Color;
import sun.awt.image.ImageWatched;

public class Chain {
    private String name;
    private int chainNumber;
    private LinkedList tilesInChain;
    private int stocksOwned;
    private Color chainColor;
    private boolean hasButton = false;


    public Chain(String n, Color c, int chainNum){
        this.name = n;
        this.chainColor = c;
        this.chainNumber = chainNum;
        this.tilesInChain = new LinkedList();
    }

    //This method checks to see if stocks are available under a certain chain and can be sold to the player.
    private boolean canSellStock(){
        return false;
    }
    
    //This method merges two or more hotel chains into the one that is bigger.
    //targetChain is the chain to merge into
    public void merge(Chain targetChain){
        //this chain should no longer exist, and the chain merged into should be the size of both put together.
        while(!this.tilesInChain.isEmpty()){
            targetChain.addTile((Tile) this.tilesInChain.removeFirst());
        }
    }

    public int chainSize(){
        return this.tilesInChain.size();
    }

    public String getName(){
        return this.name;
    }

    public LinkedList<Tile> getTilesInChain(){
        return this.tilesInChain;
    }
    public void addTile(Tile t){
        t.changeChain(this.chainNumber, this);
        this.tilesInChain.add(t);
    }


}
