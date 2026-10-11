public class AuctionService
{
    private AuctionRepository repository;
    public AuctionService(AuctionRepository auctionRepository)
    {
        this.repository = auctionRepository;
    }
    public void placeBid(AuctionId auctionId, BuyerId buyerId, Money bidAmount){
        Auction auction = this.repository.findById(auctionId);
        auction.placeBid(bidAmount, buyerId, LocalDateTime.now());
        this.repository.save(auction);
    }
}