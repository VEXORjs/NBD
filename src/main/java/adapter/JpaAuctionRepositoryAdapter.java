package adapter;

import entity.AuctionEntity;
import model.Auction;
import repository.AuctionEntityRepository;
import interfaces.AuctionRepository;
import java.util.Optional;

public class JpaAuctionRepositoryAdapter implements AuctionRepository {

    private final AuctionEntityRepository entityRepository;

    public JpaAuctionRepositoryAdapter(AuctionEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public Optional<Auction> findById(Long id) {
        return entityRepository.findById(id).map(AuctionEntity::toDomain);
    }

    @Override
    public void save(Auction auction) {
        AuctionEntity entity = AuctionEntity.fromDomain(auction);
        entityRepository.save(entity);
    }
}