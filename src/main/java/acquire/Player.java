package acquire;

public class Player {
  
  private int ID;
  private int money;
  private Tile[] tile;
  private int stock;
  private int share;
  
  
  public Player(int ID){
    
    this.ID = ID;
    this.money = 6000; //$6000 is the starting cash
    }
  
  public int getID() {
        //Player's identity
        return ID;
    }
  
  public int getMoney() {
        // Players will get money from the bank
        return money;
    }
  
  private int holdShare() {
        // Players own their share
        return share;
    }
  
  public int buyShare() {
        // Players can purchase share
        return share;
    }
  
  private int tradeShare() {
        // Players can trade shares
        return share;
    }
  
  private int sellShare() {
        // Players can sell shares
        return share;
    }
}
