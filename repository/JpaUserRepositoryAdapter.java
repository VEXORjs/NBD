//package pl.lodz.p.auction.infrastructure.adapter.out.database.repository;

import jakarta.persistence.EntityManager;
import pl.lodz.p.auction.domain.model.User;
import pl.lodz.p.auction.infrastructure.adapter.out.database.entity.UserEntity;
import pl.lodz.p.auction.ports.UserRepository;

import java.util.Optional;

public class JpaUserRepositoryAdapter implements UserRepository {

    private final EntityManager entityManager;

    public JpaUserRepositoryAdapter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findById(Long id) {
        UserEntity userEntity = this.entityManager.find(UserEntity.class, id);

        if (userEntity != null) {
            return Optional.of(userEntity.toDomain());
        }

        return Optional.empty();
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);

        this.entityManager.merge(userEntity);
    }
}