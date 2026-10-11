import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Auction {

    private final Long id;
    private final String title;
    private BigDecimal currentPrice;
    private final List<Bid> bids;

    public Auction(Long id, String title, BigDecimal currentPrice, List<Bid> bids) {
        this.id = id;
        this.title = title;
        this.currentPrice = currentPrice;
        this.bids = new ArrayList<>(bids);
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

    public void placeBid(Money bidAmount, BuyerId buyerid, LocalDateTime now){
        if (now.isBefore(startDate) || now.isAfter(endDate)){
            throw new IllegalArgumentException("Aukcja nie jest w tym momencie aktywna");
        }

        if (!bidAmount.isGreaterThan(currentPrice)){
            throw new IllegalArgumentException("Oferta musi byc wyzsza nic obecna cena");
        }

        this.currentPrice = bidAmount;
        Bid newBid = new Bid(bidAmount, buyerid, now);
        this.bids.add(newBid);
    }
}