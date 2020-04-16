package acquire;

public class MergeHandler {

    //is this class going to interact with the players directly
    //or is it going to pass the info to the board, which
    //in turn sends it to the players?

    //do we want the chain to hold which players have stock in them
    //to make this easier?

    //we will likely need to pass the players who own stock in the chain to here
    public void mergeChains(Chain[] chains){
        //find which chain is larger.
         chain 1 is 7 large
            chain 2 is 9 large

            chain 2 is bigger

                chain1.merge(chain2);
        //call merge on the chain.

        //will call all the private methods to determine how to merge

    }

    //checks the Players count of stocks to search for ties
    private void determineTies(Player[] players){

    }

    //If there is no tie for the largest, Find the largest
    private Player findLargestShareholder(Player[] players){
        return null;
        //returns whoever has the highest share value
    }

    //If there is no tie for second, finds the Second largest
    private Player findSecondShareholder(Player[] players){
        return null;
        //returns whoever has the second highest value
    }

    //distributes bonus based on ties. We will probably need to pass
    //some value to it besides bonus to tell it which
    //kind of tie it is.
    private void distributeTieBonus(Player[] players){

    }

    //divides the bonus based on the above functions.
    private void giveBonuses(){
        // do we want this to return an int?
    }
}
