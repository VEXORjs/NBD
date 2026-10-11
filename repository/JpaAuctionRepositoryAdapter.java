public class JpaAuctionRepositoryAdapter implements AuctionRepository {

    private EntityManager entityManager;

    public JpaAuctionRepositoryAdapter(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Auction findById(AuctionId id) {
        AuctionEntity auctionEntity = this.entityManager.find(AuctionEntity.class, id.getValue());
        return auctionEntity.toDomain();
    }

    @Override
    public void save(Auction auction) {
        AuctionEntity auctionEntity = AuctionEntity.fromDomain(auction);

        this.entityManager.merge(auctionEntity);
    }
}