public class Buyer extends User {
    private float Balance;
    private int BuyerID;

    public Buyer(String username, float Balance, int BuyerID) {
        super(username, Balance, BuyerID)
    }
    public float getBalance() {
        return Balance;
}
    public void setBalance(float Balance) {
        this.Balance = Balance;
}
    public boolean CanBid(public float newBidBalance){

    }
    public int getBuyerID() {
        return BuyerID;
    }
    public void setBuyerID(int BuyerID) {
        this.BuyerID = BuyerID;
    }
    public String getInfo(){

    }
}