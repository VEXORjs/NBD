package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bid  {
    private final Long bidID;
    private final Long buyerID;
    private BigDecimal bidAmount;
    private final LocalDateTime bidDate;

    public Bid(Long bidID, Long buyerID, BigDecimal bidAmount, LocalDateTime bidDate) {
        this.bidID = bidID;
        this.buyerID = buyerID;
        this.bidAmount = bidAmount;
        this.bidDate = bidDate;
    }

    public Long getId() { return bidID; }

    public Long getBuyerId() { return buyerID; }

    public BigDecimal getAmount() { return bidAmount; }

    public void setBidAmount(BigDecimal bidAmount) { this.bidAmount = bidAmount; }

    public LocalDateTime getTime() { return bidDate; }


}