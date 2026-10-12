import java.math.BigDecimal;

public class Bid  {
    private final int bidID;
    private final int buyerID;
    private BigDecimal bidAmount;

    public Bid(int bidID, int buyerID, BigDecimal bidAmount) {
        this.bidID = bidID;
        this.buyerID = buyerID;
        this.bidAmount = bidAmount;
    }

    public int getBidID() { return bidID; }

    public int getBuyerID() { return buyerID; }

    public BigDecimal getBidAmount() { return bidAmount; }


    public void setBidAmount(BigDecimal bidAmount) { this.bidAmount = bidAmount; }

}