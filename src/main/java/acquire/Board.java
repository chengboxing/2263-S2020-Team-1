package acquire;

public class Board {
    Tile[][] pieces = //do we want to build a factory class to make these?
            ; //is here because the comment forced it to be on a separate line
    Chain[] activeChains = new Chain[6];


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
    }
}
