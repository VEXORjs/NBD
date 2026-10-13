package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Auction {
    private Long id;
    private Long version;
    private String title;
    private BigDecimal currentPrice;
    private List<Bid> bids;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Auction(Long id, Long version, String title, BigDecimal currentPrice, List<Bid> bids, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.currentPrice = currentPrice;
        this.bids = bids;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void placeBid(BigDecimal bidAmount, Long buyerId, LocalDateTime now) {
        if (now.isBefore(startDate) || now.isAfter(endDate)) {
            throw new IllegalArgumentException("Aukcja nie jest w tym momencie aktywna");
        }

        if (bidAmount.compareTo(currentPrice) <= 0) {
            throw new IllegalArgumentException("Oferta musi byc wyzsza niz obecna cena");
        }

        this.currentPrice = bidAmount;
        Bid newBid = new Bid(null, buyerId, bidAmount, now);
        this.bids.add(newBid);
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    public String getTitle() { return title; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public List<Bid> getBids() { return bids; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
}