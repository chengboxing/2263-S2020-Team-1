package acquire;

import java.util.HashMap;

public class Board {
    private HashMap<String, Tile> Tiles = new HashMap();
    private Chain[] activeChains = new Chain[6];
    //private HashMap<String, Tile> Tiles = new HashMap();
    //private Chain[] activeChains = new Chain[6];


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
