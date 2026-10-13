package repository;

import jakarta.persistence.EntityManager;
import model.Auction;
import adapter.AuctionEntity;
import java.util.Optional;

public class JpaAuctionRepositoryAdapter implements AuctionRepository {

    private final EntityManager entityManager;

    public JpaAuctionRepositoryAdapter(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Auction> findById(Long id) {
        AuctionEntity auctionEntity = this.entityManager.find(AuctionEntity.class, id);

        if (auctionEntity != null) {
            return Optional.of(auctionEntity.toDomain());
        }
        return Optional.empty();
    }

    @Override
    public void save(Auction auction) {
        AuctionEntity auctionEntity = AuctionEntity.fromDomain(auction);
        this.entityManager.merge(auctionEntity);
    }
}