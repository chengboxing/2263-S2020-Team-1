package acquire;

import java.util.HashMap;

public class TileFactory {
    private HashMap<String, Tile> tiles = new HashMap();
    private HashMap<String, TileType> types = new HashMap();
    private static TileFactory instance;

    private TileFactory(){

    }

    //making the factory a singleton
    public static TileFactory getTileFactory(){
        if (TileFactory.instance == null){
            instance = new TileFactory();
        }
        return instance;
    }

    public TileType getTileType(String c) {
        if (!this.types.containsKey(c)){
            this.types.put(c, new TileType());
        }
        return this.types.get(c);
    }

    // checks to see if the tile exists, and if it does returns it.
    // if it doesn't exist, create and return it.
    public Tile getTile(String location){
        if (!this.tiles.containsKey(location)) {
            this.tiles.put(location, new Tile(location));
        }
        return this.tiles.get(location);
    }
}
