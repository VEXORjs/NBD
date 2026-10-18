package entity;

import jakarta.persistence.*;
import model.Bid;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
public class BidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name = "bid_time", nullable = false)
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = false)
    private AuctionEntity auction;

    public BidEntity() {}

    public static BidEntity fromDomain(Bid bid, AuctionEntity auctionEntity) {
        if (bid == null) {
            return null;
        }

        BidEntity entity = new BidEntity();
        entity.setId(bid.getId());
        entity.setAmount(bid.getAmount());
        entity.setBuyerId(bid.getBuyerId());
        entity.setTime(bid.getTime());
        entity.setAuction(auctionEntity);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Long getBuyerId() { return buyerId; }
    public void setBuyerId(Long buyerId) { this.buyerId = buyerId; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public AuctionEntity getAuction() { return auction; }
    public void setAuction(AuctionEntity auction) { this.auction = auction; }
}