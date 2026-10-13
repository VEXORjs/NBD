package repository;

import model.Auction;
import java.util.Optional;

public interface AuctionRepository {
    Optional<Auction> findById(Long id);
    void save(Auction auction);
}