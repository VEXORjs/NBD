import jakarta.persistence.*;
import pl.lodz.p.auction.domain.model.Auction;
import pl.lodz.p.auction.domain.model.Bid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "auctions")
public class AuctionEntity {
    @Id
    private Long id;

    private String title;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Version
    private Long version; // (ACID)

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BidEntity> bids = new ArrayList<>();

    public Auction toDomain() {
        List<Bid> domainBids = this.bids.stream()
                .map(b -> new Bid(b.getId(), b.getAmount(), b.getBuyerId(), this.id))
                .collect(Collectors.toList());
        return new Auction(this.id, this.title, this.currentPrice, domainBids);
    }

    // Gettery, settery, metody pomocnicze do dodawania BidEntity do listy
}