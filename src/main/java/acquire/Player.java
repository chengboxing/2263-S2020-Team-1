package acquire;

public class Player {
  
  private int ID;
  private int money;
  private int tile;
  private int stock;
  private int share;
  
  
  public Player(int ID){
    
    this.ID = ID;
    this.money = 6000; //$6000 is the starting cash
    }
  
  public int getID() {
    
        return ID;
    }
  
  public int getMoney() {
    
        return money;
    }
  
  private int holdShare() {
    
        return share;
    }
  
  public int buyShare() {
    
        return share;
    }
  
  private int tradeShare() {
    
        return share;
    }
  
  private int sellShare() {
    
        return share;
    }
}
