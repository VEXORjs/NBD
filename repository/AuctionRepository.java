public interface AuctionRepository {
    Auction findById(Long id);
    void save(Auction auction);
}