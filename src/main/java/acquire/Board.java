package acquire;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class Board {
    private Tile[][] tiles = new Tile[9][12];
    private Chain[] activeChains = new Chain[7];
    private TileFactory factory = TileFactory.getTileFactory();

    public Board(){
        //creates 108 tiles
        for (char c = 'A'; c < 'J'; c++){
            for (int r = 1; r < 13; r++){
                //stores the tiles in a 2d array
                tiles[c-65][r-1] = factory.getTile( (c +Integer.toString(r)));
                //System.out.println(c + Integer.toString(r) + "was created");

            }
        }
    }

    public Tile getTile(String s){
        int x = (s.charAt(0) - 65);
        int y = Integer.parseInt(s.substring(1)) -1 ;
        return this.tiles[x][y];
    }


    public boolean canPlayTile(Tile toPlay){
        return false;
    }

    public void placeTile(Tile toPlay){
        // culmination of methods Janita made, then send it to the ui frontend.
    }

    //placeTile will call this if the placed tile would cause a merge
    private void mergeChains(){

    }

    public void addChain(String n, Color c, Tile[] tiles, int chainNum){
        if(getActiveChains().length <= 7){
            this.activeChains[getActiveChains().length +1] = new Chain(n, c, tiles, chainNum);
        }
    }

    public Chain[] getActiveChains(){
        return this.activeChains;
    }

    public boolean canEnd(){
        //checks if end conditions are met
        return false;
    }
}
