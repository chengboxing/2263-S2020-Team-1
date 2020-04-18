package acquire;

import javafx.scene.paint.Color;

public class Tile {
    //Location X and Y initialized for the location on the Board
    private TileFactory tf = TileFactory.getTileFactory();
    private String locationX;
    private String locationY;
    private boolean canDeal = true;
    private boolean isPlayed = false;
    private TileType tiletype;

    //Constructor
    public Tile(String x){
        this.locationX = String.valueOf(x.charAt(0));
        this.locationY = String.valueOf(x.subSequence(1, x.length()));
        //0 is the null chain value
        this.tiletype = tf.getTileType(0);
    }

    public String getLocation(){
        return this.locationX + this.locationY;
    }

    public Tile getTile(String s){
        return tf.getTile(s);
    }

    public Color getColor(){
        if(this.isPlayed) {
            return tiletype.getColor();
        }
        return Color.WHITE;
    }

    public boolean dealable(){
        return this.canDeal;
    }

    public void play(){
        this.canDeal = false;
        this.isPlayed = true;
    }

    public void changeChain(int chain){
        this.tiletype = tf.getTileType(chain);
    }

}
