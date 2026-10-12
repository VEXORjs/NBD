import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Auction {

    private final Long id;
    private final String title;
    private BigDecimal currentPrice;
    private final List<Bid> bids;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Auction(Long id, String title, BigDecimal currentPrice, List<Bid> bids, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.currentPrice = currentPrice;
        this.bids = new ArrayList<>(bids);
        this.startTime = startDate;
        this.endTime = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public LocalDateTime getStartDate() { return startDate; }

    public LocalDateTime getEndDate() { return endDate; }

    public void placeBid(BigDecimal bidAmount, Long buyerid, LocalDateTime now){
        if (now.isBefore(startDate) || now.isAfter(endDate)){
            throw new IllegalArgumentException("Aukcja nie jest w tym momencie aktywna");
        }

        if (bidAmount.compareTo(currentPrice) <= 0){
            throw new IllegalArgumentException("Oferta musi byc wyzsza nic obecna cena");
        }

        this.currentPrice = bidAmount;
        Bid newBid = new Bid(bidAmount, buyerid, now);
        this.bids.add(newBid);
    }
}