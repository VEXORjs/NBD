package repository;

import entity.UserEntity;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class UserEntityRepository {

    private final EntityManager entityManager;

    public UserEntityRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, id));
    }

    public void save(UserEntity entity) {
        entityManager.persist(entity);
    }
}