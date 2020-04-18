package acquire;

import java.util.HashMap;

public class Board {
    private Tile[][] tiles = new Tile[9][12];
    private Chain[] activeChains = new Chain[8];
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

    //These branches can be automatically merged.

    public boolean canPlayTile(Tile toPlay){
        return false;
    }

    public void placeTile(Tile toPlay){

    }

    //placeTile will call this if the placed tile would cause a merge
    private void mergeChains(){

    }

<<<<<<< Updated upstream
=======
    public void addChain(String n, Color c, int chainNum){
        if(getActiveChains().length < 7){
            this.activeChains[chainNum] = new Chain(n, c, chainNum);
        }
    }

    public Chain[] getActiveChains(){
        return this.activeChains;
    }

>>>>>>> Stashed changes
    public boolean canEnd(){
        //checks if end conditions are met
        return false;
    }
}
