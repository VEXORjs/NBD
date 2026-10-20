package repository;

import entity.AuctionEntity;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class AuctionEntityRepository {

    private final EntityManager entityManager;

    public AuctionEntityRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<AuctionEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(AuctionEntity.class, id));
    }

    public void save(AuctionEntity entity) {
        entityManager.persist(entity);
    }
}