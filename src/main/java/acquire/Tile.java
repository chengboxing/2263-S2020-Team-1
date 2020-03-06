package acquire;

public class Tile {
    //Location X and Y initialized for the location on the Board
    private TileFactory tf = TileFactory.getTileFactory();
    private String locationX;
    private String locationY;

    private TileType tiletype;

    //Constructor
    public Tile(String x){
        this.locationX = String.valueOf(x.charAt(0));
        this.locationY = String.valueOf(x.subSequence(1, x.length()));
    }

    public String getLocation(){
        return this.locationX + this.locationY;
    }

    public Tile getTile(String s){
        return tf.getTile(s);
    }

}
