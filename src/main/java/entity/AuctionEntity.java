package entity;

import jakarta.persistence.*;
import model.Auction;
import model.Bid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "auctions")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Version
    private Long version;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BidEntity> bids = new ArrayList<>();

    public AuctionEntity() {}


    public Auction toDomain() {
        List<Bid> domainBids = this.bids.stream()
                .map(b -> new Bid(b.getId(), b.getBuyerId(), b.getAmount(), b.getTime()))
                .collect(Collectors.toList());

        return new Auction(this.id, this.currentPrice, domainBids, this.startDate, this.endDate, this.version);
    }


    public static AuctionEntity fromDomain(Auction auction) {
        if (auction == null) {
            return null;
        }

        AuctionEntity entity = new AuctionEntity();
        entity.setId(auction.getId());
        entity.setCurrentPrice(auction.getCurrentPrice());
        entity.setStartDate(auction.getStartDate());
        entity.setEndDate(auction.getEndDate());
        entity.setVersion(auction.getVersion());

        if (auction.getBids() != null) {
            entity.setBids(auction.getBids().stream()
                    .map(bid -> {
                        BidEntity bidEntity = new BidEntity();
                        bidEntity.setId(bid.getId());
                        bidEntity.setAmount(bid.getAmount());
                        bidEntity.setBuyerId(bid.getBuyerId());
                        bidEntity.setTime(bid.getTime());
                        bidEntity.setAuction(entity);
                        return bidEntity;
                    })
                    .collect(Collectors.toList()));
        }
        return entity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BigDecimal getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    public List<BidEntity> getBids() { return bids; }
    public void setBids(List<BidEntity> bids) { this.bids = bids; }
}