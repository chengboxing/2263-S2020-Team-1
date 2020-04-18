package acquire;

public class Player {
  
  private String ID;
  private int money;
<<<<<<< Updated upstream
  private Tile[] hand;
=======
  private LinkedList<Tile> hand;
>>>>>>> Stashed changes
  private int stock;
  private int share;
  
  
  public Player(String ID){
    
    this.ID = ID;
    this.money = 6000; //$6000 is the starting cash
<<<<<<< Updated upstream
=======
      this.d = Dealer.getDealerInstance();
      this.hand = new LinkedList<Tile>();
      for (int i = 0; i < 6; i++) {
          hand.add(d.dealTile());
      }
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public Tile[] getHand(){
=======
    public LinkedList<Tile> getHand(){
>>>>>>> Stashed changes
      return this.hand;
    }

    public void subtractMoney(int v){
      if(this.money >= v){
          this.money -= v;
      }else{
          this.money = 0;
      }
    }
}
