package acquire;

import java.util.LinkedList;
import java.util.List;

public class Player {
  
  private String ID;
  private int money;
  private LinkedList<Tile> hand;
  private int stock;
  private int share;
  private Dealer d;
  
  
  public Player(String ID){
    
    this.ID = ID;
    this.money = 6000; //$6000 is the starting cash
      this.d = Dealer.getDealerInstance();
      this.hand = new LinkedList<Tile>();
      for (int i = 0; i < 6; i++) {
          hand.add(d.dealTile());
      }
    }
  
  public String getID() {
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

    public int getWorth(){
      //when we add in the stock, this will calculate the stocks value and add it to money
      return this.money;
    }

    public LinkedList<Tile> getHand(){
      return this.hand;
    }

    public void playTile(Tile t){
      if(this.hand.contains(t)){
          hand.remove(t);
          hand.add(d.dealTile());
          t.play();
      }
    }

    public void subtractMoney(int v){
      if(this.money >= v){
          this.money -= v;
      }else{
          this.money = 0;
      }
    }
}
