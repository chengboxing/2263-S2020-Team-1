package acquire;

import java.util.HashMap;

public class Board {
    private Tile[][] tiles = new Tile[9][12];
    private Chain[] activeChains = new Chain[6];
    private TileFactory factory = TileFactory.getTileFactory();

    public Board(){
        //creates 108 tiles
        for (char c = 'A'; c < 'J'; c++){
            for (int r = 0; r < 12; r++){
                //stores the tiles in a 2d array
                tiles[c][r] = factory.getTile("c"+"r");

            }
        }
    }


    public boolean canPlayTile(Tile toPlay){
        return false;
    }

    public void placeTile(Tile toPlay){

    }

    //placeTile will call this if the placed tile would cause a merge
    private void mergeChains(){

    }

    public boolean canEnd(){
        //checks if end conditions are met
        return false;
    }
}
